package top.turingteam.budstudent.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.pojo.dto.AdminUserDto;
import top.turingteam.budstudent.pojo.vo.AdminLoginVo;
import top.turingteam.budstudent.pojo.vo.AdminRegisterVo;
import top.turingteam.budstudent.pojo.vo.AdminRetrieveVo;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.AdminUserService;
import top.turingteam.budstudent.support.LoginUserProvider;

/**
 * @author howe
 */
@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
@Tag(name = "管理员用户模块")
public class AdminUserController {
    private final AdminUserService adminUserService;

    private final LoginUserProvider loginUserProvider;

    @PostMapping("/login")
    @Operation(summary = "管理员登录接口")
    public Result login(HttpServletRequest request, @Validated @RequestBody AdminLoginVo adminUserVo) {
        AdminUserDto loginUser = adminUserService.getAdminLoginUserByUserNameOrPhone(request, adminUserVo);
        if (StrUtil.isNotBlank(StpUtil.getTokenValueByLoginId(loginUser.getId()))) {
            StpUtil.logout(loginUser.getId());
        }
        StpUtil.login(loginUser.getId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("登录成功").data(StpUtil.getTokenInfo().getTokenValue()).build();
    }

    @PostMapping("/logout")
    @Operation(summary = "管理员登出接口")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result logout() {
        StpUtil.logout(loginUserProvider.getAdminLoginUser().getId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("退出登录成功").build();
    }

    @PostMapping("/register")
    @Operation(summary = "管理员注册接口 (权限：超级管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result register(HttpServletRequest request, @Validated @RequestBody AdminRegisterVo adminUser) {
        AdminUserDto registeredUser = adminUserService.registerAdminUser(request, adminUser);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("注册成功").data(registeredUser).build();
    }


    @PostMapping("/retrieve/code")
    @Operation(summary = "修改密码时获取验证码的接口", description = "获取修改密码时验证码用的接口，只需要填手机号，不需要密码")
    public Result retrieveCode(HttpServletRequest request, @RequestParam String phone) {
        adminUserService.getRetrievePhoneCode(request, phone);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("发送验证码成功").build();
    }

    @PostMapping("/retrieve")
    @Operation(summary = "修改密码接口", description = "修改密码用的接口")
    public Result retrieve(HttpServletRequest request, @Validated @RequestBody AdminRetrieveVo adminUserVO) {
        adminUserService.updateAdminUserPassword(request, adminUserVO, adminUserVO.getCode());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("修改密码成功").build();
    }

}
