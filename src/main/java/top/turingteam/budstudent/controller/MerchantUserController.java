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
import top.turingteam.budstudent.pojo.dto.MerchantUserDto;
import top.turingteam.budstudent.pojo.vo.MerchantRetrieveVo;
import top.turingteam.budstudent.pojo.vo.MerchantUserVo;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.MerchantUserService;
import top.turingteam.budstudent.support.LoginUserProvider;

/**
 * @author howe
 */
@RestController
@RequestMapping("/merchant/user")
@RequiredArgsConstructor
@Tag(name = "商家用户模块")
public class MerchantUserController {
    private final MerchantUserService merchantUserService;

    private final LoginUserProvider loginUserProvider;

    @PostMapping("/login")
    @Operation(summary = "商家登录接口")
    public Result login(HttpServletRequest request, @Validated @RequestBody MerchantUserVo merchantUser) {
        MerchantUserDto loginUser = merchantUserService.getMerchantLoginUserByPhone(request, merchantUser);
        Long loginUserId = loginUser.getId();
        if (StrUtil.isNotBlank(StpUtil.getTokenValueByLoginId(loginUserId))) {
            StpUtil.logout(loginUserId);
        }
        StpUtil.login(loginUserId);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("登录成功").data(StpUtil.getTokenInfo().getTokenValue()).build();
    }


    @PostMapping("/logout")
    @Operation(summary = "商家登出接口")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result logout() {
        StpUtil.logout(loginUserProvider.getMerchantLoginUser().getId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("退出登录成功").build();
    }


    @PostMapping("/register")
    @Operation(summary = "商家注册接口")
    public Result register(HttpServletRequest request, @Validated @RequestBody MerchantRetrieveVo merchantRetrieveVo) {
        MerchantUserDto registeredUser = merchantUserService.registerMerchantUser(request, merchantRetrieveVo);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("注册成功").data(registeredUser).build();
    }


    @PostMapping("/retrieve/code")
    @Operation(summary = "获取修改密码的验证码接口", description = "获取修改密码验证码用的接口，只需要填手机号，不需要密码")
    public Result retrieveCode(HttpServletRequest request, @RequestParam String phone) {
        merchantUserService.getRetrievePhoneCode(request, phone);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("发送验证码成功").build();
    }

    @PostMapping("/register/code")
    @Operation(summary = "注册账号时获取验证码的接口", description = "获取注册账号密码时验证码用的接口，只需要填手机号，不需要密码")
    public Result registerCode(HttpServletRequest request, @RequestParam String phone) {
        merchantUserService.getRegisterPhoneCode(request, phone);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("发送验证码成功").build();
    }

    @PostMapping("/retrieve")
    @Operation(summary = "修改密码接口", description = "修改密码用的接口")
    public Result retrieve(HttpServletRequest request, @Validated @RequestBody MerchantRetrieveVo merchantRetrieveVo) {
        merchantUserService.retrieveMerchantUser(request, merchantRetrieveVo);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("修改密码成功").build();
    }

}
