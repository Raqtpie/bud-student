package top.turingteam.budstudent.support.impl;

import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.entity.MerchantUser;
import top.turingteam.budstudent.pojo.entity.StudentUser;
import top.turingteam.budstudent.service.AdminUserService;
import top.turingteam.budstudent.service.MerchantUserService;
import top.turingteam.budstudent.service.StudentUserService;
import top.turingteam.budstudent.support.LoginUserProvider;

/**
 * @author Raqtpie
 */
@RequiredArgsConstructor
@Component
public class LoginUserProviderImpl implements LoginUserProvider {

    private final StudentUserService studentUserService;

    private final AdminUserService adminUserService;

    private final MerchantUserService merchantUserService;


    @Override
    public StudentUser getStudentLoginUser() {
        String loginId = (String) StpUtil.getTokenInfo().getLoginId();
        return loginId == null ? null : studentUserService.getById(Long.valueOf(loginId));
    }

    @Override
    public AdminUser getAdminLoginUser() {
        String loginId = (String) StpUtil.getTokenInfo().getLoginId();
        return loginId == null ? null : adminUserService.getById(Long.valueOf(loginId));
    }

    @Override
    public MerchantUser getMerchantLoginUser() {
        String loginId = (String) StpUtil.getTokenInfo().getLoginId();
        return loginId == null ? null : merchantUserService.getById(Long.valueOf(loginId));
    }
}
