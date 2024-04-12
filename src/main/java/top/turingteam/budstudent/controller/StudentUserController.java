package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.pojo.entity.*;
import top.turingteam.budstudent.pojo.vo.StudentInfoVo;
import top.turingteam.budstudent.pojo.vo.StudentUserVo;
import top.turingteam.budstudent.pojo.vo.StudentUserPasswordVo;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.StudentInfoService;
import top.turingteam.budstudent.service.StudentUserService;
import top.turingteam.budstudent.support.LoginUserProvider;
import top.turingteam.budstudent.util.CodeUtil;
import top.turingteam.budstudent.util.EncryptionUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author Raqtpie
 */
@RestController
@RequestMapping("/student/user")
@RequiredArgsConstructor
@Tag(name = "学生用户模块")
public class StudentUserController {

    private final StudentUserService studentUserService;

    private final StudentInfoService studentInfoService;

    private final LoginUserProvider loginUserProvider;

    private final StringRedisTemplate stringRedisTemplate;

    private Result loginAndReturn(StudentUser loginUser) {
        Long id = loginUser.getId();
        if (StrUtil.isNotBlank(StpUtil.getTokenValueByLoginId(id))){
            //如果token不为空就先logout
            StpUtil.logout(id);
        }
        StpUtil.login(id);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("login success").data(tokenInfo.getTokenValue()).build();
    }

    @PostMapping("/login")
    @Operation(summary = "学生登录接口")
    public Result login(HttpServletRequest request, @RequestBody StudentUser studentUser) {
        StudentUser loginUser = studentUserService.getStudentLoginUserByIdCardOrPhone(request, studentUser);
        return loginAndReturn(loginUser);
    }

    @PostMapping("/checkNotice/{idCard}")
    @Operation(summary = "学生账号激活前检查录取通知书接口")
    @Parameters({
            @Parameter(name = "idCard", description = "身份证", in = ParameterIn.PATH, required = true),
            @Parameter(name = "notice", description = "录取通知书", required = true)
    })
    public Result activeCheckNotice(@PathVariable @NotNull(message = "身份证不能为空") String idCard, @RequestParam("notice") MultipartFile notice) {
        Boolean result = studentUserService.checkNotice(idCard, notice);
        return Result.builder().code(ResultCode.SUCCESS.code).data(result).msg("action check notice success").build();
    }

    @PostMapping("/checkIdCardAndActive/{idCard}")
    @Operation(summary = "学生账号激活接口")
    @Parameters({
            @Parameter(name = "idCard", description = "身份证", in = ParameterIn.PATH, required = true),
            @Parameter(name = "photo", description = "照片", required = true)
    })
    public Result active(@PathVariable @NotNull(message = "身份证不能为空") String idCard, @RequestParam("photo") MultipartFile photo) {
        studentUserService.active(idCard, photo);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("action success").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PutMapping("/updatePassword")
    @Operation(summary = "更新密码接口")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result updatePassword(@RequestBody StudentUserPasswordVo studentUserPasswordVo) {
        String encrypted = EncryptionUtil.getEncrypted(studentUserPasswordVo.getOldPassword());
        if (!loginUserProvider.getStudentLoginUser().getPassword().equals(encrypted)) {
            throw new CustomException("原密码错误");
        }
        studentUserService.updatePassword(loginUserProvider.getStudentLoginUser().getId(), studentUserPasswordVo.getNewPassword());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("update password success").build();
    }

    @PostMapping("/loginByWeChat/{jsCode}")
    @Operation(summary = "学生微信登录接口")
    @Parameter(name = "jsCode", description = "登录时获取的code，可通过wx.login获取", in = ParameterIn.PATH , required = true)
    public Result loginByWeChat(HttpServletRequest request, @PathVariable @NotBlank(message = "jsCode不能为空") String jsCode) {
        StudentUser loginUser = studentUserService.loginByWeChat(request, jsCode);
        return loginAndReturn(loginUser);
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PostMapping("/bindUserToWeChat/{jsCode}")
    @Operation(summary = "学生绑定微信接口")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "jsCode", description = "登录时获取的code，可通过wx.login获取", required = true)
    })
    public Result bindUserToWeChat(@PathVariable @NotBlank(message = "jsCode不能为空") String jsCode) {
        StudentUser studentLoginUser = loginUserProvider.getStudentLoginUser();
        studentUserService.bindUserToWeChat(jsCode, studentLoginUser.getId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("bind user to wechat success").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PostMapping("/unbindUserToWeChat/{jsCode}")
    @Operation(summary = "学生解绑微信接口")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "jsCode", description = "登录时获取的code，可通过wx.login获取", required = true)
    })
    public Result unbindUserToWeChat(@PathVariable @NotBlank(message = "jsCode不能为空") String jsCode) {
        StudentUser studentLoginUser = loginUserProvider.getStudentLoginUser();
        studentUserService.unbindUserToWeChat(jsCode, studentLoginUser.getId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("unbind user to wechat success").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PostMapping("/logout")
    @Operation(summary = "学生登出接口")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result logout() {
        StpUtil.logout(loginUserProvider.getStudentLoginUser().getId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("logout success").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("/register")
    @Operation(summary = "单个学生注册接口")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result register(@Validated @RequestBody StudentUserVo studentUserVo) {
        AdminUser adminLoginUser = loginUserProvider.getAdminLoginUser();
        StudentUser user = studentUserService.register(studentUserVo, adminLoginUser.getSchool());
        if (user == null) {
            throw new CustomException("注册失败");
        }
        return Result.builder().code(ResultCode.SUCCESS.code).msg("register success").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("/registerByXlsx")
    @Operation(summary = "导入学生注册信息接口")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result registerByXlsx(@RequestParam("list") MultipartFile file) {
        AdminUser adminLoginUser = loginUserProvider.getAdminLoginUser();
        studentUserService.registerByXlsx(file, adminLoginUser.getSchool());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("register success").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @GetMapping("/getStudentBySchoolCode/{page}/{size}")
    @Operation(summary = "获取学生列表接口")
    @Parameters({
            @Parameter(name = "schoolCode", description = "学校编码", in = ParameterIn.PATH, required = true),
            @Parameter(name = "page", description = "页码", in = ParameterIn.PATH, required = true),
            @Parameter(name = "size", description = "每页数量", in = ParameterIn.PATH, required = true),
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    })
    public Result getAllStudentBySchoolCode(@PathVariable @NotNull(message = "页码不能为空") Integer page, @PathVariable @NotNull(message = "每页数量不能为空") Integer size) {
        AdminUser adminLoginUser = loginUserProvider.getAdminLoginUser();
        String schoolCode = adminLoginUser.getSchool();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("get all student success").data(studentInfoService.getList(schoolCode, page, size)).build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("/addPhoto/{studentId}")
    @Operation(summary = "添加学生照片接口")
    @Parameters({
            @Parameter(name = "studentId", description = "学生id", in = ParameterIn.PATH, required = true),
            @Parameter(name = "photo", description = "照片",  required = true),
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)

    })
    public Result addPhotoByStudentId(@PathVariable @NotNull(message = "学生id不能为空") Long studentId, @RequestParam("photo") MultipartFile photo) {
        studentInfoService.addPhotoByStudentId(studentId, photo);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("add photo success").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/info")
    @Operation(summary = "获取学生信息接口")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getStudentInfo() {
        Long id = loginUserProvider.getStudentLoginUser().getId();
        StudentInfoVo studentInfoVo = studentInfoService.getInfoById(id);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("get student info success").data(studentInfoVo).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getAvatar")
    @Operation(summary = "获取头像")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getAvatar() {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("get student info success").data(studentInfoService.getById(loginUserProvider.getStudentLoginUser().getId()).getAvatarUrl()).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PostMapping("/updateAvatar")
    @Operation(summary = "更新头像")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result updateAvatar(@RequestParam("avatar") MultipartFile file) {
        studentInfoService.updateAvatar(loginUserProvider.getStudentLoginUser().getId(), file);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("update avatar success").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PutMapping("/updateSignature/{signature}")
    @Operation(summary = "更新个性签名")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result updateSignature(@PathVariable String signature) {
        studentInfoService.updateSignature(loginUserProvider.getStudentLoginUser().getId(), signature);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("update signature success").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getCode/{phone}")
    @Operation(summary = "获取验证码", description = "获取验证码，5分钟内有效，提供的手机号与数据库不一致不会发送验证码")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getCode(@PathVariable @NotBlank(message = "手机号不能为空") String phone) {
        StudentUser studentLoginUser = loginUserProvider.getStudentLoginUser();
        if (!studentLoginUser.getPhone().equals(EncryptionUtil.encryptPhone(phone))) {
            throw new CustomException("手机号不正确");
        }
        String code = CodeUtil.getSixBitCode();
        String key = "code:" + phone;
        stringRedisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
        System.out.println(stringRedisTemplate.opsForValue().get(key));
        //todo 发送验证码
        return Result.builder().code(ResultCode.SUCCESS.code).msg("get success").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PutMapping("/updatePhone/{phone}/{code}")
    @Operation(summary = "更新手机号")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result updatePhone(@PathVariable @NotBlank(message = "手机号不能为空") String phone, @PathVariable @NotBlank(message = "验证码不能为空") String code) {
        studentUserService.updatePhone(loginUserProvider.getStudentLoginUser().getId(), phone, code);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("update phone success").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PutMapping("/updateAddress/{address}")
    @Operation(summary = "更新地址")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result updateAddress(@PathVariable @NotBlank(message = "地址不能为空") String address) {
        studentInfoService.updateAddress(loginUserProvider.getStudentLoginUser().getId(), address);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("update address success").build();
    }
}
