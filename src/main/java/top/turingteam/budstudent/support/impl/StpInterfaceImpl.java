package top.turingteam.budstudent.support.impl;

import cn.dev33.satoken.stp.StpInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.entity.MerchantUser;
import top.turingteam.budstudent.pojo.entity.StudentUser;
import top.turingteam.budstudent.support.LoginUserProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 *
 * @author howe
 */
@RequiredArgsConstructor
@Component
public class StpInterfaceImpl implements StpInterface {

    private final LoginUserProvider loginUserProvider;

    /**
     * 返回一个账号所拥有的权限码集合
     * (只做角色鉴权，这里不做权限)
     *
     * @param loginUserIdByString UserType.XXX_STR + id
     * @param loginType           loginType
     * @return 该账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginUserIdByString, String loginType) {
        return new ArrayList<>();
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     *
     * @param loginUserIdByString id
     * @param loginType           loginType
     * @return 该账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginUserIdByString, String loginType) {
        ArrayList<String> list = new ArrayList<>();
        StudentUser studentLoginUser = loginUserProvider.getStudentLoginUser();
        if (studentLoginUser != null) {
            list.add(UserType.STUDENT_STR);
        }
        MerchantUser merchantLoginUser = loginUserProvider.getMerchantLoginUser();
        if (merchantLoginUser != null) {
            list.add(UserType.MERCHANT_STR);
        }
        AdminUser adminLoginUser = loginUserProvider.getAdminLoginUser();
        if (adminLoginUser != null) {
            if (adminLoginUser.isRole()) {
                list.add(UserType.SUPER_ADMIN_STR);
            } else {
                // 默认0为学校管理员
                list.add(UserType.ADMIN_STR);
            }
        }
        return list;
    }
}