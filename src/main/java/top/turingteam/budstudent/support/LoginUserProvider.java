package top.turingteam.budstudent.support;

import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.entity.MerchantUser;
import top.turingteam.budstudent.pojo.entity.StudentUser;

/**
 * @author Raqtpie
 */
public interface LoginUserProvider {
    /**
     * 获取学生登录用户
     * @return 学生登录用户
     */
    StudentUser getStudentLoginUser();

    /**
     * 获取管理员登录用户
     * @return 管理员登录用户
     */
    AdminUser getAdminLoginUser();

    /**
     * 获取商家登录用户
     * @return 商家登录用户
     */
    MerchantUser getMerchantLoginUser();
}
