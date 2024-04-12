package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.pojo.entity.StudentPersona;
import top.turingteam.budstudent.pojo.vo.StudentPersonaMsgVo;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.pojo.vo.StudentPersonaVo;
import top.turingteam.budstudent.service.StudentInfoService;
import top.turingteam.budstudent.service.StudentPersonaService;
import top.turingteam.budstudent.support.LoginUserProvider;

import java.util.List;

/**
 * @author Raqtpie
 */
@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor
@Tag(name = "学生个人形象模块")
public class StudentPersonaController {
    private final LoginUserProvider loginUserProvider;

    private final StudentPersonaService studentPersonaService;

    private final StudentInfoService studentInfoService;

    /**
     * 添加学生个人形象
     * @return Result
     */
    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @PostMapping("/add")
    @Operation(summary = "添加学生个人形象", description = "需要超级管理员权限")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result addPersona(@RequestPart("studentPersonaVo") @Validated StudentPersonaVo studentPersonaVo, @RequestPart("personaFile") MultipartFile file) {
        studentPersonaService.addPersona(studentPersonaVo, file);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("添加成功").build();
    }

    /**
     * 修改学生个人形象
     */
    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @PutMapping("/update")
    @Operation(summary = "修改学生个人形象", description = "需要超级管理员权限")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result updatePersona(@RequestPart("studentPersona") @Validated StudentPersona studentPersona, @RequestPart("personaFile") MultipartFile file){
        studentPersonaService.updatePersona(studentPersona, file);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("修改成功").build();
    }

    /**
     * 删除学生个人形象
     * @return Result
     */
    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除学生个人形象", description = "需要超级管理员权限")
    @Parameters({
            @Parameter(name = "id", description = "学生个人形象id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true),
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)

    })
    public Result deletePersona(@PathVariable @NotNull(message = "id不能为空") Integer id) {
        studentPersonaService.removeById(id);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getAllByStudentId")
    @Operation(summary = "查询所有学生个人形象", description = "需要学生权限，查询结果仅有性别相同的形象")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getAllPersonaByStudentId() {
        Long studentId = loginUserProvider.getStudentLoginUser().getId();
        List<StudentPersonaMsgVo> allByStudentId = studentPersonaService.getAllByStudentId(studentId);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(allByStudentId).build();
    }

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @GetMapping("/getAll")
    @Operation(summary = "查询所有学生个人形象", description = "需要超级管理员权限")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getAllPersona() {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(studentPersonaService.list()).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getNowPersona")
    @Operation(summary = "查询学生当前个人形象", description = "需要学生权限，结果为学生当前的形象")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getNowPersona() {
        Long studentId = loginUserProvider.getStudentLoginUser().getId();
        StudentPersona persona = studentPersonaService.getStudentPersonaById(studentId);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(persona).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/exchangePersona/{personaId}")
    @Operation(summary = "兑换个人形象", description = "需要学生权限")
    @Parameters({
            @Parameter(name = "personaId", description = "个人形象id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true),
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    })
    public Result exchangePersona(@PathVariable @NotNull(message = "id不能为空") Integer personaId) {
        Long studentId = loginUserProvider.getStudentLoginUser().getId();
        studentPersonaService.exchangePersona(studentId, personaId);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("兑换成功").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/setNowPersona/{personaId}")
    @Operation(summary = "设置当前个人形象", description = "需要学生权限")
    @Parameters({
            @Parameter(name = "personaId", description = "个人形象id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true),
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    })
    public Result setNowPersona(@PathVariable @NotNull(message = "id不能为空") Integer personaId) {
        Long studentId = loginUserProvider.getStudentLoginUser().getId();
        if (!studentPersonaService.getById(personaId).getGender().equals(studentInfoService.getById(studentId).getGender())) {
            return Result.builder().code(ResultCode.BAD_REQUEST.code).msg("性别不符").build();
        }
        studentPersonaService.setNowPersona(studentId, personaId);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("设置成功").build();
    }
}
