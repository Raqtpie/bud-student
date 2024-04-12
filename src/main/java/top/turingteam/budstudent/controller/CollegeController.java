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
import top.turingteam.budstudent.mapper.College2majorMapper;
import top.turingteam.budstudent.mapping.CollegeMapping;
import top.turingteam.budstudent.pojo.entity.College;
import top.turingteam.budstudent.pojo.entity.College2major;
import top.turingteam.budstudent.pojo.entity.Major;
import top.turingteam.budstudent.pojo.vo.CollegeVo;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.CollegeService;
import top.turingteam.budstudent.service.MajorService;
import top.turingteam.budstudent.support.LoginUserProvider;

/**
 * @author howe
 */
@RestController
@RequestMapping("/admin/college")
@RequiredArgsConstructor
@Tag(name = "学校学院管理模块")
public class CollegeController {
    private final CollegeService collegeService;

    private final CollegeMapping collegeMapping;

    private final LoginUserProvider loginUserProvider;

    private final College2majorMapper college2majorMapper;

    private final MajorService majorService;

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping()
    @Operation(summary = "添加学院", description = "可以添加学院，请注意学院代码不能重复，需要学校管理员权限 (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result add(@Validated @RequestBody CollegeVo collegesVo) {
        College college = collegeMapping.toCollege(collegesVo);
        college.setSchoolCode(loginUserProvider.getAdminLoginUser().getSchool());
        LambdaQueryWrapper<College> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(College::getSchoolCode, college.getSchoolCode());
        queryWrapper.eq(College::getCollageCode, college.getCollageCode());
        if (collegeService.getOne(queryWrapper) != null) {
            return Result.builder().code(ResultCode.BAD_REQUEST.code).msg("学院代码已存在").build();
        }
        boolean save = collegeService.save(college);
        return save ? Result.builder().code(200).msg("添加成功").build()
                : Result.builder().code(400).msg("添加失败").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @DeleteMapping("/{collegeCode}")
    @Operation(summary = "删除学院", description = "输入需要删除的学院代码 (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result delete(@PathVariable String collegeCode) {
        collegeService.deleteCollege(collegeCode);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PutMapping()
    @Operation(summary = "修改学院", description = "输入需要修改的学院 (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result update(@Validated @RequestBody CollegeVo collegeVo) {
        collegeService.updateCollege(collegeVo);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("修改学院信息成功").build();
    }

    @SaCheckRole(value = {UserType.ADMIN_STR}, mode = SaMode.OR)
    @GetMapping()
    @Operation(summary = "获取学院列表", description = "获取学校的学院列表 (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result list() {
        LambdaQueryWrapper<College> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(College::getSchoolCode, loginUserProvider.getAdminLoginUser().getSchool());
        return Result.builder().code(ResultCode.SUCCESS.code).data(collegeService.list(queryWrapper)).build();
    }

    @SaCheckRole(value = {UserType.ADMIN_STR}, mode = SaMode.OR)
    @GetMapping("/{collageCode}")
    @Operation(summary = "获取学院", description = "获取单个学校学院 (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result get(@PathVariable String collageCode) {
        LambdaQueryWrapper<College> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(College::getSchoolCode, loginUserProvider.getAdminLoginUser().getSchool())
                .eq(College::getCollageCode, collageCode);
        return Result.builder().code(ResultCode.SUCCESS.code).data(collegeService.list(queryWrapper)).build();
    }

    /**
     * 学校管理员为学院添加专业
     */
    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("/major")
    @Operation(summary = "为学院添加专业", description = "为学院添加专业 (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result addMajor(@Validated @RequestBody College2major college2major) {
        if (collegeService.getOne(new LambdaQueryWrapper<College>().eq(College::getCollageCode, college2major.getCollegeCode())) == null) {
            return Result.builder().code(ResultCode.BAD_REQUEST.code).msg("学院不存在").build();
        }
        if (majorService.getOne(new LambdaQueryWrapper<Major>().eq(Major::getMajorCode, college2major.getMajorCode())) == null) {
            return Result.builder().code(ResultCode.BAD_REQUEST.code).msg("专业不存在").build();
        }
        boolean save = college2majorMapper.insert(college2major) > 0;
        return save ? Result.builder().code(ResultCode.SUCCESS.code).msg("添加成功").build()
                : Result.builder().code(ResultCode.BAD_REQUEST.code).msg("添加失败").build();
    }

    /**
     * 学校管理员为学院删除专业
     */
    @SaCheckRole(UserType.ADMIN_STR)
    @DeleteMapping("/major")
    @Operation(summary = "为学院删除专业", description = "为学院删除专业 (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result deleteMajor(@Validated @RequestBody College2major college2major) {
        boolean remove = college2majorMapper.delete(new LambdaQueryWrapper<College2major>()
                .eq(College2major::getCollegeCode, college2major.getCollegeCode())
                .eq(College2major::getMajorCode, college2major.getMajorCode())) > 0;
        return remove ? Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build()
                : Result.builder().code(ResultCode.BAD_REQUEST.code).msg("删除失败").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @GetMapping("/getCollegeInfoBtCollegeName/{collegeName}")
    @Operation(summary = "根据学院名称获取学院信息", description = "根据学院名称获取学院信息 (权限：学校管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getCollegeInfoByCollegeName(@PathVariable String collegeName) {
        LambdaQueryWrapper<College> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(College::getSchoolCode, loginUserProvider.getAdminLoginUser().getSchool());
        queryWrapper.like(College::getName, collegeName);
        return Result.builder().code(ResultCode.SUCCESS.code).data(collegeService.list(queryWrapper)).build();
    }
}