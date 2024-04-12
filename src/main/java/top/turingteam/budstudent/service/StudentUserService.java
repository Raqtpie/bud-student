package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.pojo.vo.StudentUserVo;
import top.turingteam.budstudent.pojo.entity.StudentUser;

/**
 * @author Raqtpie
 */
public interface StudentUserService extends IService<StudentUser> {
    /**
     * 根据身份证号或手机号获取学生登录用户
     *
     * @param request     请求
     * @param studentUser 学生登录用户
     * @return 学生登录用户
     */
    StudentUser getStudentLoginUserByIdCardOrPhone(HttpServletRequest request, StudentUser studentUser);

    /**
     * 微信登录
     *
     * @param request 请求
     * @param code    微信jsCode
     * @return 学生登录用户
     */
    StudentUser loginByWeChat(HttpServletRequest request, String code);

    /**
     * 绑定微信
     * @param jsCode    微信jsCode
     * @param studentId 学生id
     */
    void bindUserToWeChat(String jsCode, Long studentId);

    /**
     * 解绑微信
     * @param jsCode 微信jsCode
     * @param id 学生id
     */
    void unbindUserToWeChat(String jsCode, Long id);

    /**
     * 注册
     *
     * @param studentUserVo 学生用户dto
     * @param schoolCode    学校编码
     * @return 学生登录用户
     */
    StudentUser register(StudentUserVo studentUserVo, String schoolCode);

    /**
     * 注册
     *
     * @param file       excel文件
     * @param schoolCode 学校编码
     */
    void registerByXlsx(MultipartFile file, String schoolCode);

    /**
     * 更新密码
     *
     * @param id          学生用户id
     * @param newPassword 新密码
     */
    void updatePassword(Long id, String newPassword);

    /**
     * 更新手机号
     *
     * @param id    学生用户id
     * @param phone 新手机号
     * @param code  验证码
     */
    void updatePhone(Long id, String phone, String code);

    /**
     * 激活账号
     * @param idCard 身份证号
     * @param photo 照片
     */
    void active(String idCard, MultipartFile photo);

    /**
     * 检查通知书
     * @param idCard 身份证号
     * @param notice 通知
     * @return 是否通过
     */
    Boolean checkNotice(String idCard, MultipartFile notice);
}
