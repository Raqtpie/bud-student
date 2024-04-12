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
import top.turingteam.budstudent.mapper.AdminLoginRecordMapper;
import top.turingteam.budstudent.mapper.AdminUserMapper;
import top.turingteam.budstudent.mapping.AdminUserMapping;
import top.turingteam.budstudent.pojo.dto.AdminUserDto;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.vo.AdminLoginRecord;
import top.turingteam.budstudent.pojo.vo.AdminLoginVo;
import top.turingteam.budstudent.pojo.vo.AdminRegisterVo;
import top.turingteam.budstudent.pojo.vo.AdminRetrieveVo;
import top.turingteam.budstudent.service.AdminUserService;
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
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {
    private final AdminUserMapper adminUserMapper;

    private final AdminLoginRecordMapper adminLoginRecordMapper;

    private final StringRedisTemplate redisTemplate;

    private final AdminUserMapping adminUserMapping;


    /**
     * 根据用户名或手机号获取管理员登录用户
     *
     * @param request   请求
     * @param adminUser 管理员登录用户
     * @return 管理员登录用户
     */
    @Override
    public AdminUserDto getAdminLoginUserByUserNameOrPhone(HttpServletRequest request, AdminLoginVo adminUser) {
        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUser::getUsername, adminUser.getUsername()).or().eq(AdminUser::getPhone, adminUser.getUsername());
        AdminUser loginUser = adminUserMapper.selectOne(wrapper);
        if (loginUser == null) {
            throw new CustomException("用户输入错误或不存在");
        }
        String ip = IPUtil.getIpAddress(request);
        String city = IPUtil.getCity(ip);
        AdminLoginRecord adminLoginRecord = new AdminLoginRecord(null, loginUser.getId(), ip, city, false);
        if (loginUser.isDeleteFlag()) {
            adminLoginRecordMapper.insert(adminLoginRecord);
            throw new CustomException("用户已被删除");
        }
        String isSuperAdmin = adminUser.getIsSuperAdmin();
        // isSuperAdmin不是这UserType.ADMIN_STR 或 UserType.SUPER_ADMIN_STR ， 就是非法的，抛出异常
        if (!UserType.ADMIN_STR.equals(isSuperAdmin) && !UserType.SUPER_ADMIN_STR.equals(isSuperAdmin)) {
            adminLoginRecordMapper.insert(adminLoginRecord);
            throw new CustomException("用户权限标识错误");
        }
        if (UserType.SUPER_ADMIN_STR.equals(isSuperAdmin) && !loginUser.isRole()) {
            adminLoginRecordMapper.insert(adminLoginRecord);
            throw new CustomException("用户没有该权限");
        }
        if (UserType.ADMIN_STR.equals(isSuperAdmin) && loginUser.isRole()) {
            adminLoginRecordMapper.insert(adminLoginRecord);
            throw new CustomException("用户没有该权限");
        }
        boolean authenticate = EncryptionUtil.authenticate(adminUser.getPassword(), loginUser.getPassword());
        if (!authenticate) {
            adminLoginRecordMapper.insert(adminLoginRecord);
            throw new CustomException("密码错误");
        }
        adminLoginRecord.setStatus(true);
        adminLoginRecordMapper.insert(adminLoginRecord);
        return adminUserMapping.toAdminUserDTO(loginUser);
    }

    /**
     * 注册管理员用户
     *
     * @param request     请求
     * @param adminUserVO 管理员用户
     * @return 管理员用户
     */
    @Override
    public AdminUserDto registerAdminUser(HttpServletRequest request, AdminRegisterVo adminUserVO) {
        AdminUser adminUser = adminUserMapping.toAdminUser(adminUserVO);
        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<>();
        if (!Validator.isMobile(adminUser.getPhone())) {
            throw new CustomException("手机号格式错误");
        }
        // 查询该手机号是否已注册
        wrapper.eq(AdminUser::getPhone, adminUser.getPhone());
        AdminUser loginUser = adminUserMapper.selectOne(wrapper);
        if (loginUser != null) {
            throw new CustomException("该手机号已注册");
        }
        // 查询该用户名是否已注册
        wrapper.clear();
        wrapper.eq(AdminUser::getUsername, adminUser.getUsername());
        loginUser = adminUserMapper.selectOne(wrapper);
        if (loginUser != null) {
            throw new CustomException("该用户名已注册");
        }
        wrapper.clear();
        adminUser.setPassword(EncryptionUtil.getEncrypted(adminUser.getPassword()));
        adminUser.setId(IDUtil.nextId());
        int insert = adminUserMapper.insert(adminUser);
        if (insert == 0) {
            throw new CustomException("注册失败,请重试");
        }
        return adminUserMapping.toAdminUserDTO(adminUser);
    }

    /**
     * 获取修改密码手机验证码
     *
     * @param request 请求
     * @param phone   管理员用户
     */
    @Override
    public void getRetrievePhoneCode(HttpServletRequest request, String phone) {
        if (!Validator.isMobile(phone)) {
            throw new CustomException("手机号格式错误");
        }
        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUser::getPhone, phone);
        AdminUser loginUser = adminUserMapper.selectOne(wrapper);
        if (loginUser == null) {
            throw new CustomException("该手机号管理员用户不存在，请注册");
        }
        //  获得6位验证码 后续再换成短信验证码
        String sixBitCode = CodeUtil.getSixBitCode();
        System.out.println("修改密码手机号：" + phone + "验证码：" + sixBitCode);
        // 存储到redis
        String phoneNumber = UserType.ADMIN_STR + OperateType.RETRIEVE_STR + phone;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(phoneNumber))) {
            redisTemplate.opsForValue().setIfPresent(phoneNumber, sixBitCode, 3, TimeUnit.MINUTES);
        } else {
            redisTemplate.opsForValue().set(phoneNumber, sixBitCode, 3, TimeUnit.MINUTES);
        }
    }

    /**
     * 修改管理员用户密码
     *
     * @param request     请求
     * @param adminUserVO 管理员用户
     * @param code        验证码
     */
    @Override
    public void updateAdminUserPassword(HttpServletRequest request, AdminRetrieveVo adminUserVO, String code) {
        AdminUser adminUser = adminUserMapping.toAdminUser(adminUserVO);
        if (!Validator.isMobile(adminUser.getPhone())) {
            throw new CustomException("手机号格式错误");
        }
        if (StrUtil.isBlank(code)) {
            throw new CustomException("验证码不能为空");
        }
        String phoneNumber = UserType.ADMIN_STR + OperateType.RETRIEVE_STR + adminUser.getPhone();
        String storedValue = redisTemplate.opsForValue().get(phoneNumber);
        if (StrUtil.isBlank(storedValue)) {
            throw new CustomException("验证码已过期");
        }
        if (!storedValue.equals(code)) {
            throw new CustomException("验证码错误");
        }
        adminUser.setPassword(EncryptionUtil.getEncrypted(adminUser.getPassword()));
        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUser::getPhone, adminUser.getPhone());
        int update = adminUserMapper.update(adminUser, wrapper);
        if (update == 0) {
            throw new CustomException("修改密码失败");
        }
    }

    /**
     * 查看是否是超级管理员
     *
     * @param id 管理员id
     * @return true-是超级管理员
     */
    @Override
    public boolean isSuperAdmin(Integer id) {
        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUser::getId, id);
        AdminUser adminUser = adminUserMapper.selectOne(wrapper);
        return adminUser != null && adminUser.isRole();
    }
}
