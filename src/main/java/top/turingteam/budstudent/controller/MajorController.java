package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.pojo.entity.Major;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.College2majorService;
import top.turingteam.budstudent.service.MajorService;
import top.turingteam.budstudent.support.LoginUserProvider;

import java.util.Collections;
import java.util.List;

/**
 * @author howe
 */

@RestController
@RequestMapping("/admin/major")
@RequiredArgsConstructor
@Tag(name = "学校专业管理模块")
public class MajorController {

    private final MajorService majorService;

    private final College2majorService college2majorService;

    private final LoginUserProvider loginUserProvider;

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @PostMapping()
    @Operation(summary = "添加专业", description = "可以添加专业，请注意专业代码不能重复 (权限：超级管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result add(@Validated @RequestBody Major major) {
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Major::getMajorCode, major.getMajorCode());
        if (majorService.getOne(queryWrapper) != null) {
            return Result.builder().code(ResultCode.ERROR.code).msg("专业代码已存在").build();
        }
        boolean save = majorService.save(major);
        return save ? Result.builder().code(ResultCode.SUCCESS.code).msg("添加成功").build()
                : Result.builder().code(ResultCode.ERROR.code).msg("添加失败").build();
    }

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @DeleteMapping("/{majorCode}")
    @Operation(summary = "删除专业", description = "输入需要删除的专业的专业代码 (权限：超级管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result delete(@PathVariable String majorCode) {
        boolean remove = majorService.remove(new LambdaQueryWrapper<Major>().eq(Major::getMajorCode, majorCode));
        return remove ? Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build()
                : Result.builder().code(ResultCode.ERROR.code).msg("删除失败").build();
    }

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @PutMapping()
    @Operation(summary = "修改专业", description = "输入需要修改的专业信息 (权限：超级管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result update(@Validated @RequestBody Major major) {
        boolean update = majorService.updateById(major);
        return update ? Result.builder().code(ResultCode.SUCCESS.code).msg("更新成功").build()
                : Result.builder().code(ResultCode.ERROR.code).msg("更新失败").build();
    }

    @SaCheckRole(value = {UserType.SUPER_ADMIN_STR, UserType.ADMIN_STR, UserType.STUDENT_STR}, mode = SaMode.OR)
    @GetMapping()
    @Operation(summary = "获取所有专业信息", description = "返回所有专业信息 (权限：无)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result list() {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(majorService.list()).build();
    }

    @SaCheckRole(value = {UserType.SUPER_ADMIN_STR, UserType.ADMIN_STR, UserType.STUDENT_STR}, mode = SaMode.OR)
    @GetMapping("/{majorCode}")
    @Operation(summary = "获取专业信息", description = "输入需要获取专业信息的专业代码 (权限：无)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getMajorCode(@PathVariable("majorCode") String majorCode) {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(majorService.getById(majorCode)).build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("/{collegeCode}")
    @Operation(summary = "查看学院下的专业", description = "查询某学院下的专业，需要学校管理员权限， (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getMajorByCollege(@PathVariable("collegeCode") String collegeCode) {
        Major[] majors = college2majorService.selectMajorByCollegeCode(collegeCode, loginUserProvider.getAdminLoginUser().getSchool());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(majors == null ? Collections.emptyList() : majors).build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("/getMajorInfoByMajorName/{majorName}")
    @Operation(summary = "根据专业名称获取专业信息", description = "根据专业名称获取专业信息，需要学校管理员权限， (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getMajorInfoByMajorName(@PathVariable String majorName) {
        String school = loginUserProvider.getAdminLoginUser().getSchool();
        List<Major> majors = majorService.getMajorInfoByMajorName(majorName, school);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(majors).build();
    }
}
