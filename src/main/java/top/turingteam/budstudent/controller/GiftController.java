package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.vo.GiftAddVo;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.GiftService;
import top.turingteam.budstudent.service.StudentInfoService;
import top.turingteam.budstudent.service.UserGiftService;
import top.turingteam.budstudent.support.LoginUserProvider;

/**
 * @author howe
 */
@RestController
@RequestMapping("/gift")
@Tag(name = "礼品管理模块")
@RequiredArgsConstructor
public class GiftController {
    private final GiftService giftService;
    private final LoginUserProvider loginUserProvider;
    private final StudentInfoService studentInfoService;
    private final UserGiftService userGiftService;

    @SaCheckRole(UserType.ADMIN_STR)
    @PutMapping
    @Operation(summary = "添加礼品", description = "学校管理员添加礼品")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "giftPhoto", description = "礼品图片", in = ParameterIn.QUERY, schema = @Schema(type = "file"), required = true)
    })
    public Result add(@Validated @RequestPart("giftAddVo") GiftAddVo giftAddVo, @RequestParam("giftPhoto") MultipartFile file) {
        giftService.addGift(giftAddVo, file);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("添加成功").build();
    }

    @SaCheckRole(value = {UserType.ADMIN_STR, UserType.STUDENT_STR}, mode = SaMode.OR)
    @GetMapping
    @Operation(summary = "获取本校礼品列表", description = "将会获取到本校的所有礼品列表 (权限：学生、学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result list() {
        AdminUser adminLoginUser = loginUserProvider.getAdminLoginUser();
        if (adminLoginUser != null) {
            return Result.builder().code(ResultCode.SUCCESS.code)
                    .data(giftService.getGiftList(adminLoginUser.getSchool()))
                    .msg("获取成功").build();
        }
        String schoolCode = studentInfoService.getById(Long.parseLong((String) StpUtil.getLoginId())).getSchoolCode();
        return Result.builder().code(ResultCode.SUCCESS.code)
                .data(giftService.getGiftList(schoolCode))
                .msg("获取成功").build();
    }

    @SaCheckRole(value = {UserType.ADMIN_STR, UserType.STUDENT_STR}, mode = SaMode.OR)
    @GetMapping("/{id}")
    @Operation(summary = "查看礼品", description = "查看某一个礼品 (权限：学生、学校管理员)")
    @Parameters({
            @Parameter(name = "id", description = "礼品id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true),
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    })
    public Result get(@PathVariable Integer id) {
        return Result.builder().code(ResultCode.SUCCESS.code)
                .data(giftService.getById(id))
                .msg("获取成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @DeleteMapping("/{id}")
    @Operation(summary = "删除礼品", description = "学校管理员删除礼品，需要是本校的")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result delete(@PathVariable Integer id) {
        giftService.removeGift(id);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/exchange/{giftId}")
    @Operation(summary = "学生兑换礼品", description = "学生本校兑换礼品")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result exchange(@PathVariable Integer giftId) {
        giftService.exchangeGift(giftId, studentInfoService.getById(Long.parseLong((String) StpUtil.getLoginId())));
        return Result.builder().code(ResultCode.SUCCESS.code).msg("兑换成功").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/my/list")
    @Operation(summary = "学生获取自己的礼品列表", description = "学生获取自己的礼品列表")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result studentList() {
        return Result.builder().code(ResultCode.SUCCESS.code)
                .data(userGiftService.MyGiftList(studentInfoService.getById(Long.parseLong((String) StpUtil.getLoginId()))))
                .msg("获取成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @GetMapping("/school/list")
    @Operation(summary = "学校管理员获取礼品兑换列表", description = "将会得到所有学生的礼品兑换订单")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result adminList() {
        return Result.builder().code(ResultCode.SUCCESS.code)
                .data(userGiftService.SchoolGiftExchangeList(loginUserProvider.getAdminLoginUser().getSchool()))
                .msg("获取成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PutMapping("/{id}")
    @Operation(summary = "核销礼品", description = "学生兑换礼品后，到指定地点领取，学校管理员核销礼品")
    @Parameters({
            @Parameter(name = "id", description = "兑换订单id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true),
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    })
    public Result verify(@PathVariable Integer id) {
        giftService.verifyGift(id);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("核销成功").build();
    }


}
