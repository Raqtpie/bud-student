package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.pojo.entity.StudentPointRecord;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.pojo.vo.StudentPointVo;
import top.turingteam.budstudent.service.StudentInfoService;
import top.turingteam.budstudent.service.StudentPointService;
import top.turingteam.budstudent.support.LoginUserProvider;

import java.util.List;

/**
 * 任务接口
 * @author Raqtpie
 */
@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
@Tag(name = "积分模块")
public class PointController {
    private final StudentInfoService studentInfoService;

    private final StudentPointService studentPointService;

    private final LoginUserProvider loginUserProvider;

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getPoint")
    @Operation(summary = "查询当前拥有积分")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getPoint() {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("get point success").data(studentPointService.getPoint(loginUserProvider.getStudentLoginUser().getId())).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getTotalPoint")
    @Operation(summary = "查询总积分")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getTotalPoint() {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("get total point success").data(studentPointService.getTotalPoint(loginUserProvider.getStudentLoginUser().getId())).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getPointRecord")
    @Operation(summary = "查询积分记录")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getPointRecord(Integer current, Integer size) {
        List<StudentPointRecord> pointRecord = studentPointService.getPointRecord(loginUserProvider.getStudentLoginUser().getId(), current, size);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("get point record success").data(pointRecord).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getSchoolPointRank")
    @Operation(summary = "查询本校积分排行榜")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getSchoolPointRank() {
        List<StudentPointVo> schoolRank = studentPointService.getSchoolRank(studentInfoService.getById(loginUserProvider.getStudentLoginUser().getId()).getSchoolCode());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("get school point rank success").data(schoolRank).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getPointRank")
    @Operation(summary = "查询积分排行榜")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getPointRank() {
        List<StudentPointVo> schoolRank = studentPointService.getRank();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("get school point rank success").data(schoolRank).build();
    }
}
