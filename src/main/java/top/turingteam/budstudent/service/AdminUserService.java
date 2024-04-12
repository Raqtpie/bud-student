package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import top.turingteam.budstudent.pojo.dto.AdminUserDto;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.vo.AdminLoginVo;
import top.turingteam.budstudent.pojo.vo.AdminRegisterVo;
import top.turingteam.budstudent.pojo.vo.AdminRetrieveVo;

/**
 * @author howe
 */
public interface AdminUserService extends IService<AdminUser> {
    /**
     * 根据用户名或手机号获取管理员登录用户
     * @param request 请求
     * @param adminUser 管理员登录用户
     * @return 管理员登录用户
     */
    AdminUserDto getAdminLoginUserByUserNameOrPhone(HttpServletRequest request, AdminLoginVo adminUser);

    /**
     * 注册管理员用户
     * @param request 请求
     * @param adminUser 管理员用户
     * @return 管理员用户
     */
    AdminUserDto registerAdminUser(HttpServletRequest request, AdminRegisterVo adminUser);

    /**
     * 获取修改密码手机验证码
     * @param request 请求
     * @param phone 管理员用户
     */
    void getRetrievePhoneCode(HttpServletRequest request, String phone);


    /**
     * 修改管理员用户密码
     * @param request 请求
     * @param adminUserVO 管理员用户
     * @param code 验证码
     */
    void updateAdminUserPassword(HttpServletRequest request, AdminRetrieveVo adminUserVO, String code);

    /**
     * 查看是否是超级管理员
     * @param id 管理员id
     * @return true-是超级管理员
     */
    boolean isSuperAdmin(Integer id);
}