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
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.exception.AuthorizationException;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.entity.OnlineTask;
import top.turingteam.budstudent.pojo.entity.StudentInfo;
import top.turingteam.budstudent.pojo.entity.StudentUser;
import top.turingteam.budstudent.pojo.vo.LowTaskCompleteRateStudentVo;
import top.turingteam.budstudent.pojo.vo.OnlineTaskVo;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.OnlineTaskService;
import top.turingteam.budstudent.service.StudentInfoService;
import top.turingteam.budstudent.support.LoginUserProvider;

import java.util.List;
import java.util.Objects;

/**
 * 任务接口
 *
 * @author Raqtpie
 */
@RestController
@RequestMapping("/onlineTask")
@RequiredArgsConstructor
@Tag(name = "每日任务模块")
public class OnlineTaskController {
    private final OnlineTaskService onlineTaskService;

    private final LoginUserProvider loginUserProvider;

    private final StudentInfoService studentInfoService;

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getOnlineTaskToday")
    @Operation(summary = "获取当天的每日任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getOnlineTaskToday() {
        String schoolCode = studentInfoService.getById(Long.parseLong((String) StpUtil.getTokenInfo().getLoginId())).getSchoolCode();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(onlineTaskService.getOnlineTaskToday(schoolCode)).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getOnlineTaskNotYetDone")
    @Operation(summary = "获取未完成的每日任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getOnlineTaskNotYetDone() {
        StudentUser studentLoginUser = loginUserProvider.getStudentLoginUser();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(onlineTaskService.getOnlineTaskNotYetDone(studentLoginUser)).build();
    }

    @PostMapping("/submitOnlineTask/{onlineTaskId}")
    @Operation(summary = "提交每日任务")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "onlineTaskId", description = "任务id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true),
            @Parameter(name = "photo", description = "图片", in = ParameterIn.QUERY, schema = @Schema(type = "file"), required = true)
    })
    public Result submitOnlineTask(@PathVariable @NotNull(message = "onlineTaskId不能为空") Integer onlineTaskId, @RequestParam("photo") MultipartFile file) {
        StudentUser studentLoginUser = loginUserProvider.getStudentLoginUser();
        onlineTaskService.submitOnlineTask(onlineTaskId, studentLoginUser, file);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("提交成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("addOnlineTask")
    @Operation(summary = "添加每日任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result addOnlineTask(@Validated @RequestBody OnlineTaskVo onlineTaskVo) {
        OnlineTask onlineTask = new OnlineTask();
        BeanUtils.copyProperties(onlineTaskVo, onlineTask);
        AdminUser user = loginUserProvider.getAdminLoginUser();
        onlineTaskService.addOnlineTask(onlineTask, user);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("添加成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PutMapping("updateOnlineTask")
    @Operation(summary = "更新每日任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result updateOnlineTask(@Validated @RequestBody OnlineTask onlineTask) {
        if (!Objects.equals(loginUserProvider.getAdminLoginUser().getSchool(), onlineTask.getSchoolCode())) {
            throw new AuthorizationException("学校不一致");
        }
        OnlineTask task = onlineTaskService.getById(onlineTask.getId());
        if (task == null) {
            throw new CustomException("任务不存在");
        } else if (!Objects.equals(task.getCreatorId(), onlineTask.getCreatorId())) {
            throw new CustomException("创建者ID参数有误！");
        }
        onlineTaskService.updateById(onlineTask);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("更新成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("deleteOnlineTask/{onlineTaskId}")
    @Operation(summary = "删除每日任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result deleteOnlineTask(@PathVariable @NotNull(message = "id不能为空") Integer onlineTaskId) {
        onlineTaskService.deleteOnlineTask(onlineTaskId, loginUserProvider.getAdminLoginUser());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build();
    }

    @SaCheckRole(value = {UserType.ADMIN_STR, UserType.STUDENT_STR}, mode = SaMode.OR)
    @GetMapping("/getOnlineTask")
    @Operation(summary = "获取本校所有每日任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getOnlineTask() {
        AdminUser adminLoginUser = loginUserProvider.getAdminLoginUser();
        if (adminLoginUser != null) {
            return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(onlineTaskService.getTaskBeforeToday(adminLoginUser.getSchool())).build();
        }
        StudentInfo studentInfo = studentInfoService.getById(Long.parseLong((String) StpUtil.getLoginId()));
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(onlineTaskService.getTaskBeforeToday(studentInfo.getSchoolCode())).build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @GetMapping("/getLowerCompleteRateStudentList")
    @Operation(summary = "获取低任务完成率学生列表", description = "返回列表JSON数据")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getLowerCompleteRateStudentList() {
        String schoolCode = loginUserProvider.getAdminLoginUser().getSchool();
        List<LowTaskCompleteRateStudentVo> list = onlineTaskService.getLowerCompleteRateStudentList(schoolCode);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(list).build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @GetMapping("/getLowerCompleteRateStudentListXlsx")
    @Operation(summary = "获取低任务完成率学生列表", description = "后台生成xlsx后上传至对象存储，该接口返回下载链接")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getLowerCompleteRateStudentListXlsx() {
        String schoolCode = loginUserProvider.getAdminLoginUser().getSchool();
        String url = onlineTaskService.getLowerCompleteRateStudentListXlsx(schoolCode);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(url).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getDoneStatus/{taskId}")
    @Operation(summary = "获取任务完成状态")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "taskId", description = "任务id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true)

    })
    public Result getDoneStatus(@PathVariable Integer taskId) {
        Long studentId = Long.parseLong((String) StpUtil.getTokenInfo().getLoginId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(onlineTaskService.getDoneStatus(taskId, studentId)).build();
    }
}
