package top.turingteam.budstudent.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.common.constant.OperateType;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.MerchantLoginRecordMapper;
import top.turingteam.budstudent.mapper.MerchantUserMapper;
import top.turingteam.budstudent.mapping.MerchantUserMapping;
import top.turingteam.budstudent.pojo.dto.MerchantUserDto;
import top.turingteam.budstudent.pojo.entity.MerchantUser;
import top.turingteam.budstudent.pojo.vo.MerchantLoginRecord;
import top.turingteam.budstudent.pojo.vo.MerchantRetrieveVo;
import top.turingteam.budstudent.pojo.vo.MerchantUserVo;
import top.turingteam.budstudent.service.MerchantUserService;
import top.turingteam.budstudent.util.CodeUtil;
import top.turingteam.budstudent.util.EncryptionUtil;
import top.turingteam.budstudent.util.IDUtil;
import top.turingteam.budstudent.util.IPUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author howe
 */
@RequiredArgsConstructor
@Service
public class MerchantUserServiceImpl extends ServiceImpl<MerchantUserMapper, MerchantUser> implements MerchantUserService {
    private final MerchantUserMapper merchantUserMapper;

    private final MerchantLoginRecordMapper merchantLoginRecordMapper;

    private final StringRedisTemplate redisTemplate;

    private final MerchantUserMapping merchantUserMapping;


    /**
     * 根据手机号获取商家登录用户
     *
     * @param request        请求
     * @param merchantUserVo 商家登录用户
     * @return 商家登录用户
     */
    @Override
    public MerchantUserDto getMerchantLoginUserByPhone(HttpServletRequest request, MerchantUserVo merchantUserVo) {
        MerchantUser merchantUser = merchantUserMapping.ToMerchantUser(merchantUserVo);
        if (!Validator.isMobile(merchantUser.getPhone())) {
            throw new CustomException("手机号格式错误");
        }
        LambdaQueryWrapper<MerchantUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MerchantUser::getPhone, merchantUser.getPhone());
        MerchantUser loginUser = merchantUserMapper.selectOne(wrapper);
        if (loginUser == null) {
            throw new CustomException("用户名错误或用户不存在");
        }
        String ip = IPUtil.getIpAddress(request);
        String city = IPUtil.getCity(ip);
        MerchantLoginRecord merchantLoginRecord = new MerchantLoginRecord(null, loginUser.getId(), ip, city, false);
        if (loginUser.getBanExpireStatus()) {
            merchantLoginRecordMapper.insert(merchantLoginRecord);
            throw new CustomException("账号被封禁");
        }
        boolean authenticate = EncryptionUtil.authenticate(merchantUser.getPassword(), loginUser.getPassword());
        if (!authenticate) {
            merchantLoginRecordMapper.insert(merchantLoginRecord);
            throw new CustomException("密码错误");
        }
        merchantLoginRecord.setStatus(true);
        merchantLoginRecordMapper.insert(merchantLoginRecord);
        return merchantUserMapping.ToMerchantUserDto(loginUser);
    }

    /**
     * 注册商家用户
     *
     * @param request            请求
     * @param merchantRetrieveVo 商家用户
     * @return 商家用户
     */
    @Override
    public MerchantUserDto registerMerchantUser(HttpServletRequest request, MerchantRetrieveVo merchantRetrieveVo) {
        MerchantUser merchantUser = merchantUserMapping.merchantRetrieveVoToMerchantUser(merchantRetrieveVo);
        if (!Validator.isMobile(merchantUser.getPhone())) {
            throw new CustomException("手机号格式错误");
        }
        if (StrUtil.isBlank(merchantRetrieveVo.getCode())) {
            throw new CustomException("验证码不能为空");
        }
        String phoneNumber = UserType.MERCHANT_STR + OperateType.REGISTER_STR + merchantUser.getPhone();
        String storedValue = redisTemplate.opsForValue().get(phoneNumber);
        if (StrUtil.isBlank(storedValue)) {
            throw new CustomException("验证码已过期");
        }
        if (!storedValue.equals(merchantRetrieveVo.getCode())) {
            throw new CustomException("验证码错误");
        }
        merchantUser.setPassword(EncryptionUtil.getEncrypted(merchantUser.getPassword()));
        merchantUser.setId(IDUtil.nextId());
        int insert = merchantUserMapper.insert(merchantUser);
        if (insert == 0) {
            throw new CustomException("注册失败,请重试");
        }
        return merchantUserMapping.ToMerchantUserDto(merchantUser);
    }

    /**
     * 获取修改密码手机验证码
     *
     * @param request 请求
     * @param phone   商家用户
     */
    @Override
    public void getRetrievePhoneCode(HttpServletRequest request, String phone) {
        if (StrUtil.isBlank(phone)) {
            throw new CustomException("手机号不能为空");
        }
        if (!Validator.isMobile(phone)) {
            throw new CustomException("手机号格式错误");
        }
        LambdaQueryWrapper<MerchantUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MerchantUser::getPhone, phone);
        MerchantUser loginUser = merchantUserMapper.selectOne(wrapper);
        if (loginUser == null) {
            throw new CustomException("该手机号商家用户不存在，请注册");
        }
        //  获得6位验证码 后续再换成短信验证码
        String sixBitCode = CodeUtil.getSixBitCode();
        System.out.println("手机号：" + phone + "验证码：" + sixBitCode);
        // 存储到redis
        String phoneNumber = UserType.MERCHANT_STR + OperateType.RETRIEVE_STR + phone;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(phoneNumber))) {
            redisTemplate.opsForValue().setIfPresent(phoneNumber, sixBitCode, 3, TimeUnit.MINUTES);
        } else {
            redisTemplate.opsForValue().set(phoneNumber, sixBitCode, 3, TimeUnit.MINUTES);
        }
    }

    /**
     * 注册账号时获取验证码的接口
     *
     * @param request 请求
     * @param phone   手机号
     */
    @Override
    public void getRegisterPhoneCode(HttpServletRequest request, String phone) {
        if (StrUtil.isBlank(phone)) {
            throw new CustomException("手机号不能为空");
        }
        if (!Validator.isMobile(phone)) {
            throw new CustomException("手机号格式错误");
        }
        LambdaQueryWrapper<MerchantUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MerchantUser::getPhone, phone);
        MerchantUser loginUser = merchantUserMapper.selectOne(wrapper);
        if (loginUser != null) {
            throw new CustomException("该手机号已注册");
        }
        //  获得6位验证码 后续再换成短信验证码
        String sixBitCode = CodeUtil.getSixBitCode();
        System.out.println("手机号：" + phone + "验证码：" + sixBitCode);
        // 存储到redis
        String phoneNumber = UserType.MERCHANT_STR + OperateType.REGISTER_STR + phone;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(phoneNumber))) {
            redisTemplate.opsForValue().setIfPresent(phoneNumber, sixBitCode, 3, TimeUnit.MINUTES);
        } else {
            redisTemplate.opsForValue().set(phoneNumber, sixBitCode, 3, TimeUnit.MINUTES);
        }
    }

    /**
     * 修改商家用户密码
     *
     * @param request             请求
     * @param merchantRetrieveVoe 商家用户
     */
    @Override
    public void retrieveMerchantUser(HttpServletRequest request, MerchantRetrieveVo merchantRetrieveVoe) {
        MerchantUser merchantUser = merchantUserMapping.merchantRetrieveVoToMerchantUser(merchantRetrieveVoe);
        if (!Validator.isMobile(merchantUser.getPhone())) {
            throw new CustomException("手机号格式错误");
        }
        if (StrUtil.isBlank(merchantRetrieveVoe.getCode())) {
            throw new CustomException("验证码不能为空");
        }
        String phoneNumber = UserType.MERCHANT_STR + OperateType.RETRIEVE_STR + merchantUser.getPhone();
        String storedValue = redisTemplate.opsForValue().get(phoneNumber);
        if (StrUtil.isBlank(storedValue)) {
            throw new CustomException("验证码已过期");
        }
        if (!storedValue.equals(merchantRetrieveVoe.getCode())) {
            throw new CustomException("验证码错误");
        }
        merchantUser.setPassword(EncryptionUtil.getEncrypted(merchantUser.getPassword()));
        LambdaQueryWrapper<MerchantUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MerchantUser::getPhone, merchantUser.getPhone());
        int update = merchantUserMapper.update(merchantUser, wrapper);
        if (update == 0) {
            throw new CustomException("修改密码失败,请重试");
        }
    }
}
