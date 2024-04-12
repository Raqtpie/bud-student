package top.turingteam.budstudent.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import com.tencentcloudapi.iai.v20180301.models.CompareFaceRequest;
import com.tencentcloudapi.iai.v20180301.models.CompareFaceResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.FileContentType;
import top.turingteam.budstudent.common.constant.WeChatResponseCode;
import top.turingteam.budstudent.exception.AuthorizationException;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.exception.NotFoundException;
import top.turingteam.budstudent.mapper.*;
import top.turingteam.budstudent.mapping.StudentUserMapping;
import top.turingteam.budstudent.pojo.bo.WeChatResponse;
import top.turingteam.budstudent.pojo.dto.StudentUserDto;
import top.turingteam.budstudent.pojo.vo.StudentUserVo;
import top.turingteam.budstudent.pojo.entity.StudentInfo;
import top.turingteam.budstudent.pojo.entity.StudentPoint;
import top.turingteam.budstudent.pojo.entity.StudentUser;
import top.turingteam.budstudent.pojo.vo.StudentLoginRecord;
import top.turingteam.budstudent.service.StudentUserService;
import top.turingteam.budstudent.support.MinioHelper;
import top.turingteam.budstudent.util.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static top.turingteam.budstudent.common.constant.MqCommon.*;

/**
 * @author Raqtpie
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class StudentUserServiceImpl extends ServiceImpl<StudentUserMapper, StudentUser> implements StudentUserService {
    private final StudentUserMapper studentUserMapper;

    private final StudentInfoMapper studentInfoMapper;

    private final StudentPointMapper studentPointMapper;

    private final StudentLoginRecordMapper studentLoginRecordMapper;

    private final StudentUserMapping studentUserMapping;

    private final StudentPersonaMapper studentPersonaMapper;

    private final StringRedisTemplate stringRedisTemplate;

    private final RestTemplate restTemplate;

    private final RabbitTemplate rabbitTemplate;

    private final MinioHelper minioHelper;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.grant_type}")
    private String grantType;

    @Value("${tencent.secretId}")
    private String secretId;

    @Value("${tencent.secretKey}")
    private String secretKey;

    private static final Integer PASS_SCORE = 75;

    @Override
    public StudentUser getStudentLoginUserByIdCardOrPhone(HttpServletRequest request, StudentUser studentUser) {
        String ip = IPUtil.getIpAddress(request);
        String city = IPUtil.getCity(ip);
        LambdaQueryWrapper<StudentUser> wrapper = new LambdaQueryWrapper<>();
        if (studentUser.getIdCard() == null && studentUser.getPhone() == null) {
            throw new CustomException("用户名不能为空");
        }
        if (studentUser.getIdCard() != null && !studentUser.getIdCard().isEmpty()) {
            wrapper.eq(StudentUser::getIdCard, EncryptionUtil.getEncrypted(studentUser.getIdCard()));
        } else {
            wrapper.eq(StudentUser::getPhone, EncryptionUtil.encryptPhone(studentUser.getPhone()));
        }
        StudentUser loginUser = studentUserMapper.selectOne(wrapper);
        if (loginUser == null) {
            throw new NotFoundException("用户名错误或用户不存在");
        }
        StudentLoginRecord studentLoginRecord = new StudentLoginRecord(null, loginUser.getId(), ip, city, false);
        if (!loginUser.getActivationStatus() || loginUser.getBanExpireStatus()) {
            studentLoginRecordMapper.insert(studentLoginRecord);
            throw new AuthorizationException("账号未激活或已被封禁");
        }
        boolean authenticate = EncryptionUtil.authenticate(studentUser.getPassword(), loginUser.getPassword());
        if (!authenticate) {
            studentLoginRecordMapper.insert(studentLoginRecord);
            throw new CustomException("密码错误");
        }
        studentLoginRecord.setStatus(true);
        studentLoginRecordMapper.insert(studentLoginRecord);
        return loginUser;
    }

    @Override
    public StudentUser loginByWeChat(HttpServletRequest request, String code) {
        String openid = getOpenid(code);
        LambdaQueryWrapper<StudentUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentUser::getWxId, openid);
        StudentUser loginUser = studentUserMapper.selectOne(wrapper);
        if (loginUser == null) {
            throw new NotFoundException("用户未绑定微信或不存在该用户");
        }
        String ip = IPUtil.getIpAddress(request);
        String city = IPUtil.getCity(ip);
        StudentLoginRecord studentLoginRecord = new StudentLoginRecord(null, loginUser.getId(), ip, city, true);
        studentLoginRecordMapper.insert(studentLoginRecord);
        return loginUser;
    }

    @Override
    public void bindUserToWeChat(String jsCode, Long studentId) {
        String openid = getOpenid(jsCode);
        StudentUser loginUser = studentUserMapper.selectById(studentId);
        if (loginUser == null) {
            throw new NotFoundException("用户未注册");
        } else if(loginUser.getWxId() != null) {
            throw new CustomException("该用户已绑定微信");
        } else if (!loginUser.getActivationStatus() || loginUser.getBanExpireStatus()) {
            throw new AuthorizationException("账号未激活或已被封禁");
        }
        LambdaQueryWrapper<StudentUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentUser::getWxId, openid);
        if (studentUserMapper.selectOne(wrapper) != null) {
            throw new CustomException("该微信已绑定其他用户");
        }
        loginUser.setWxId(openid);
        studentUserMapper.updateById(loginUser);
    }

    @Override
    public void unbindUserToWeChat(String jsCode, Long id) {
        String openid = getOpenid(jsCode);
        StudentUser loginUser = studentUserMapper.selectById(id);
        if (loginUser == null) {
            throw new NotFoundException("用户未注册");
        } else if (!loginUser.getWxId().equals(openid)) {
            throw new CustomException("该微信未绑定该用户");
        }
        loginUser.setWxId(null);
        studentUserMapper.updateById(loginUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StudentUser register(StudentUserVo studentUserVo, String schoolCode) {
        log.info("学生注册，学生姓名：{}", studentUserVo.getName());
        CheckParamUtil.checkAll(studentUserVo.getIdCard(), studentUserVo.getPhone(), studentUserVo.getName());

        LambdaQueryWrapper<StudentUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentUser::getIdCard, studentUserVo.getIdCard());
        if (studentUserMapper.selectOne(wrapper) != null) {
            throw new CustomException("该身份证已注册");
        }
        wrapper.clear();
        wrapper.eq(StudentUser::getPhone, EncryptionUtil.encryptPhone(studentUserVo.getPhone()));
        if (studentUserMapper.selectOne(wrapper) != null) {
            throw new CustomException("该手机号已注册");
        }
        Long id = IDUtil.nextId();
        StudentUser studentUser = studentUserMapping.toStudentUser(studentUserVo);
        studentUser.setId(id);
        // 默认密码为身份证后六位
        studentUser.setPassword(EncryptionUtil.getEncrypted(studentUserVo.getIdCard().substring(12)));
        studentUser.setIdCard(EncryptionUtil.getEncrypted(studentUserVo.getIdCard()));
        studentUser.setPhone(EncryptionUtil.encryptPhone(studentUserVo.getPhone()));
        StudentInfo studentInfo = studentUserMapping.toStudentInfo(studentUserVo);
        studentInfo.setIdCard(EncryptionUtil.getEncrypted(studentUserVo.getIdCard()));
        studentInfo.setSchoolCode(schoolCode);
        studentInfo.setGender(studentUserVo.getIdCard().charAt(16) % 2 == 0 ? "女" : "男");
        studentInfo.setAvatarUrl(studentUserVo.getIdCard().charAt(16) % 2 == 0 ? "https://turingminio.raqtpie.xyz:49000/bud-student/avatar_woman.png" : "https://turingminio.raqtpie.xyz:49000/bud-student/avatar_man.png");
        studentInfo.setId(id);
        studentUserMapper.insert(studentUser);
        studentInfoMapper.insert(studentInfo);
        studentPointMapper.insert(new StudentPoint(id, EncryptionUtil.getEncrypted(studentUserVo.getIdCard()), schoolCode ,0, null, 0 ,0));
        studentPersonaMapper.insertNowPersona(id, studentUserVo.getIdCard().charAt(16) % 2 == 0 ? 2 : 1);
        log.info("学生注册成功，学生姓名：{}", studentUserVo.getName());
        return studentUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerByXlsx(MultipartFile file, String schoolCode) {
        String contentType = file.getContentType();
        if (contentType == null) {
            throw new CustomException("文件类型不合法");
        } else if (!contentType.equals(FileContentType.XLS_TYPE) && !contentType.equals(FileContentType.XLSX_TYPE)) {
            throw new CustomException("文件必须为xls或xlsx格式");
        }
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, StudentUserVo.class, new PageReadListener<StudentUserVo>(dataList -> {
                for (int i = 0; i < dataList.size(); i++) {
                    StudentUserVo studentUserVo = dataList.get(i);
                    StudentUserDto studentUserDto;
                    if (i == dataList.size() - 1) {
                        studentUserDto = new StudentUserDto(studentUserVo, schoolCode, true);
                    } else {
                        studentUserDto = new StudentUserDto(studentUserVo, schoolCode, false);
                    }
                    rabbitTemplate.convertAndSend(QUEUE_NAME, JSONUtil.toJsonStr(studentUserDto));
                }
            })).sheet().doRead();
        } catch (IOException e) {
            throw new CustomException("文件读取失败");
        }
    }

    @Override
    public void updatePassword(Long id, String newPassword) {
        StudentUser studentUser = studentUserMapper.selectById(id);
        studentUser.setPassword(EncryptionUtil.getEncrypted(newPassword));
        studentUserMapper.updateById(studentUser);
    }

    @Override
    public void updatePhone(Long id, String phone, String code) {
        String oldPhone = studentUserMapper.selectById(id).getPhone();
        String key = "code:" + EncryptionUtil.decryptPhone(oldPhone);
        String codeInRedis = stringRedisTemplate.opsForValue().get(key);
        stringRedisTemplate.delete(key);
        if (codeInRedis == null || !codeInRedis.equals(code)) {
            throw new CustomException("验证码错误或已过期");
        }
        StudentUser studentUser = studentUserMapper.selectById(id);
        studentUser.setPhone(EncryptionUtil.encryptPhone(phone));
        studentUserMapper.updateById(studentUser);
    }

    @Override
    public void active(String idCard, MultipartFile photo) {
        String photoContentType = photo.getContentType();
        if (photoContentType == null || !photoContentType.startsWith(FileContentType.IMAGE_TYPE)) {
            throw new CustomException("照片格式不合法");
        }
        StudentUser studentUser = checkActiveAndReturnStudentUser(idCard);
        StudentInfo studentInfo = getStudentInfoByIdCard(idCard);
        String url = minioHelper.uploadFile(photo, "face_" + studentUser.getId() + photoContentType.substring(photoContentType.lastIndexOf("/")));
        if (getFaceScore(studentInfo.getPhotoUrl(), url) > PASS_SCORE) {
            studentUser.setActivationStatus(true);
            studentUserMapper.updateById(studentUser);
        } else {
            throw new CustomException("人脸不匹配");
        }
    }

    @Override
    public Boolean checkNotice(String idCard, MultipartFile notice) {
        String contentType = notice.getContentType();
        if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
            throw new CustomException("通知格式不合法");
        }
        StudentInfo studentInfo = getStudentInfoByIdCard(idCard);
        if (studentInfo == null) {
            throw new NotFoundException("用户不存在");
        }
        // OCR识别，识别通知中是否包含数据库存的通知书编号
        boolean ocrFlag = OcrUtil.exitString(studentInfo.getNoticeNumber(), notice);
        if (!ocrFlag) {
            throw new CustomException("通知书编号不匹配");
        }
        stringRedisTemplate.opsForValue().set("notice:" + EncryptionUtil.getEncrypted(idCard), "true", 5, TimeUnit.MINUTES);
        return true;
    }

    private StudentUser checkActiveAndReturnStudentUser(String idCard) {
        String noticeResult = stringRedisTemplate.opsForValue().get("notice:" + EncryptionUtil.getEncrypted(idCard));
        if (noticeResult == null) {
            throw new CustomException("请先上传通知书");
        }
        LambdaQueryWrapper<StudentUser> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(StudentUser::getIdCard, EncryptionUtil.getEncrypted(idCard));
        StudentUser studentUser = studentUserMapper.selectOne(userWrapper);
        if (studentUser == null) {
            throw new NotFoundException("用户不存在");
        }
        if (studentUser.getActivationStatus()) {
            throw new CustomException("用户已激活");
        }
        return studentUser;
    }

    private StudentInfo getStudentInfoByIdCard(String idCard) {
        LambdaQueryWrapper<StudentInfo> infoWrapper = new LambdaQueryWrapper<>();
        infoWrapper.eq(StudentInfo::getIdCard, EncryptionUtil.getEncrypted(idCard));
        return studentInfoMapper.selectOne(infoWrapper);
    }

    private Float getFaceScore(String urlA, String urlB) {
        try{
            Credential cred = new Credential(secretId, secretKey);
            IaiClient client = getIaiClient(cred);
            CompareFaceRequest req = new CompareFaceRequest();
            req.setUrlA(urlA);
            req.setUrlB(urlB);
            CompareFaceResponse resp = client.CompareFace(req);
            return resp.getScore();
        } catch (TencentCloudSDKException e) {
            throw new CustomException("人脸识别失败");
        }
    }

    @NotNull
    private static IaiClient getIaiClient(Credential cred) {
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iai.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new IaiClient(cred, "", clientProfile);
    }

    private String getOpenid(String jsCode) {
        String apiUrl = "https://api.weixin.qq.com/sns/jscode2session" +
                "?appid=" + appid +
                "&secret=" + secret +
                "&js_code=" + jsCode +
                "&grant_type=" + grantType;
        String responseStr = restTemplate.getForObject(apiUrl, String.class);
        if (responseStr == null) {
            throw new CustomException("微信服务器无法响应，请稍后再试。");
        }
        WeChatResponse response = JSONUtil.toBean(responseStr, WeChatResponse.class);
        String openid;
        if (response != null) {
            if (response.getErrcode() == WeChatResponseCode.INVALID_CODE || response.getErrcode() == WeChatResponseCode.USED_CODE) {
                throw new CustomException("jsCode无效，请稍后再试。");
            }
            openid = response.getOpenid();
            if (openid == null) {
                throw new CustomException("无法获取用户ID，请稍后再试。");
            }
        } else {
            throw new CustomException("无法获取用户ID，请稍后再试。");
        }
        return openid;
    }
}
