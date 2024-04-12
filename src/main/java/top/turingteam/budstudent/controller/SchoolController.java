package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.pojo.entity.School;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.pojo.vo.SchoolDeleteVo;
import top.turingteam.budstudent.service.SchoolService;

import java.util.Arrays;
import java.util.List;

/**
 * 管理员管理学校模块
 *
 * @author howe
 */
@RestController
@RequestMapping("/admin/school")
@RequiredArgsConstructor
@Tag(name = "学校管理模块")
@Slf4j
public class SchoolController {
    private final SchoolService schoolService;

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @PostMapping()
    @Operation(summary = "批量添加学校", description = "可以批量添加学校，请注意学校代码不能重复 (权限：超级管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result add(@Validated @RequestBody School[] school) {
        List<String> idList = Arrays.stream(school).map(School::getSchoolCode).toList();
        List<School> schools = schoolService.listByIds(idList);
        if (!schools.isEmpty()) {
            return Result.builder().code(ResultCode.BAD_REQUEST.code).msg("学校代码不能重复").build();
        }
        boolean save = schoolService.saveBatch(Arrays.asList(school));
        return save ? Result.builder().code(ResultCode.SUCCESS.code).msg("添加成功").build()
                : Result.builder().code(ResultCode.BAD_REQUEST.code).msg("添加失败").build();
    }

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @DeleteMapping()
    @Operation(summary = "删除学校", description = "输入需要删除的学校的学校代码 (权限：超级管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result delete(@Validated @RequestBody SchoolDeleteVo[] schoolCodes) {
        boolean remove = schoolService.removeByIds(Arrays.asList(schoolCodes));
        return remove ? Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build()
                : Result.builder().code(ResultCode.BAD_REQUEST.code).msg("删除失败").build();
    }

    @SaCheckRole(value = {UserType.ADMIN_STR, UserType.SUPER_ADMIN_STR}, mode = SaMode.OR)
    @PutMapping()
    @Operation(summary = "修改学校", description = "输入需要修改的学校信息 (权限：超级管理员，管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result update(@Validated @RequestBody School school) {
        boolean update = schoolService.updateById(school);
        return update ? Result.builder().code(ResultCode.SUCCESS.code).data(school).msg("更新成功").build()
                : Result.builder().code(ResultCode.BAD_REQUEST.code).msg("更新失败").build();
    }

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @GetMapping()
    @Operation(summary = "获取所有学校信息", description = "返回所有学校信息 (权限：超级管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result list() {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(schoolService.list()).build();
    }

    @SaCheckRole(value = {UserType.ADMIN_STR, UserType.SUPER_ADMIN_STR}, mode = SaMode.OR)
    @GetMapping("/{id}")
    @Operation(summary = "获取学校信息", description = "输入需要获取学校信息的学校代码 (权限：无)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getSchoolId(@PathVariable("id") String schoolCode) {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(schoolService.getById(schoolCode)).build();
    }
}