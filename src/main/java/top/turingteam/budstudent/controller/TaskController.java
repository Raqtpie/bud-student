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
import top.turingteam.budstudent.pojo.entity.Point;
import top.turingteam.budstudent.pojo.entity.StudentUser;
import top.turingteam.budstudent.pojo.entity.Task;
import top.turingteam.budstudent.pojo.vo.LowTaskCompleteRateStudentVo;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.pojo.vo.TaskVo;
import top.turingteam.budstudent.service.StudentInfoService;
import top.turingteam.budstudent.service.TaskService;
import top.turingteam.budstudent.support.LoginUserProvider;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

/**
 * @author Raqtpie
 */
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "主/支线任务模块")
public class TaskController {
    private final TaskService taskService;

    private final StudentInfoService studentInfoService;

    private final LoginUserProvider loginUserProvider;

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("/addTask")
    @Operation(summary = "添加任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result addTask(@Validated @RequestBody TaskVo taskVo) {
        Task task = new Task();
        BeanUtils.copyProperties(taskVo, task);
        AdminUser user = loginUserProvider.getAdminLoginUser();
        taskService.addTask(task, user);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("添加成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("/updateTask")
    @Operation(summary = "更新任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result updateTask(@Validated @RequestBody Task task) {
        if (!Objects.equals(loginUserProvider.getAdminLoginUser().getSchool(), task.getSchoolCode())) {
            throw new AuthorizationException("学校不一致");
        }
        Task t = taskService.getById(task.getId());
        if (t == null) {
            throw new CustomException("任务不存在");
        } else if (!Objects.equals(task.getCreatorId(), t.getCreatorId())) {
            throw new CustomException("创建者ID参数有误！");
        }
        taskService.updateTask(task);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("更新成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @PostMapping("/deleteTask/{taskId}")
    @Operation(summary = "删除任务")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "taskId", description = "任务id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true)
    })
    public Result deleteTask(@PathVariable @NotNull(message = "任务id不能为空") Integer taskId) {
        taskService.deleteTask(taskId);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PostMapping("/getTask")
    @Operation(summary = "获取所有已经开始且未完成的任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getTask() {
        StudentUser user = loginUserProvider.getStudentLoginUser();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(taskService.getTask(user.getId())).build();
    }

    @SaCheckRole(value = {UserType.ADMIN_STR, UserType.STUDENT_STR}, mode = SaMode.OR)
    @PostMapping("/getAllTask")
    @Operation(summary = "获取所有任务")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getAllTask() {
        AdminUser user = loginUserProvider.getAdminLoginUser();
        if (user == null) {
            StudentUser studentLoginUser = loginUserProvider.getStudentLoginUser();
            return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(taskService.getAllTask(studentInfoService.getById(studentLoginUser.getId()).getSchoolCode())).build();
        }
        return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(taskService.getAllTask(user.getSchool())).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PostMapping("/submitTask/{taskId}")
    @Operation(summary = "提交任务：拍照")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "taskId", description = "任务id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true),
            @Parameter(name = "photo", description = "图片", in = ParameterIn.QUERY, schema = @Schema(type = "file"))
    })
    public Result submitTask(@PathVariable @NotNull(message = "任务id不能为空") Integer taskId, @RequestParam("photo") MultipartFile file) {
        StudentUser user = loginUserProvider.getStudentLoginUser();
        taskService.submitTask(taskId, file, user.getId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("提交成功").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PostMapping("/submitTask/{taskId}/{answer}")
    @Operation(summary = "提交任务：选择题")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "taskId", description = "任务id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true),
            @Parameter(name = "answer", description = "答案", in = ParameterIn.PATH, schema = @Schema(type = "string"))
    })
    public Result submitTaskByChoose(@PathVariable @NotNull(message = "任务id不能为空") Integer taskId, @PathVariable @Nullable String answer) {
        StudentUser user = loginUserProvider.getStudentLoginUser();
        taskService.submitTask(taskId, answer, user.getId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("提交成功").build();
    }

    @PostMapping("/submitTaskByFace/{studentId}/{taskId}")
    @Operation(summary = "提交任务：CV识别")
    @Parameters({
            @Parameter(name = "studentId", description = "学生id", in = ParameterIn.PATH, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "taskId", description = "任务id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true)
    })
    public Result submitTaskByFace(@PathVariable String studentId, @PathVariable Integer taskId) {
        taskService.submitTask(taskId, Long.valueOf(studentId));
        return Result.builder().code(ResultCode.SUCCESS.code).msg("提交成功").build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @PostMapping("/submitTaskByLocation/{taskId}")
    @Operation(summary = "提交任务：前端完成最后的打卡")
    @Parameters({
            @Parameter(name = "taskId", description = "任务id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true),
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    })
    public Result submitTaskByLocation(@PathVariable @NotNull(message = "任务id不能为空") Integer taskId, @RequestBody Point location) {
        StudentUser user = loginUserProvider.getStudentLoginUser();
        taskService.submitTask(taskId, location, user.getId());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("提交成功").build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @GetMapping("/getLowerCompleteRateStudentList")
    @Operation(summary = "获取低任务完成率学生列表", description = "获取列表JSON数据")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getLowerCompleteRateStudentList() {
        String schoolCode = loginUserProvider.getAdminLoginUser().getSchool();
        List<LowTaskCompleteRateStudentVo> list = taskService.getLowerCompleteRateStudentList(schoolCode);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(list).build();
    }

    @SaCheckRole(UserType.ADMIN_STR)
    @GetMapping("/getLowerCompleteRateStudentListXlsx")
    @Operation(summary = "获取低任务完成率学生列表", description = "后台生成xlsx后上传至对象存储，该接口返回下载链接")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getLowerCompleteRateStudentListXlsx() {
        String schoolCode = loginUserProvider.getAdminLoginUser().getSchool();
        String url = taskService.getLowerCompleteRateStudentListXlsx(schoolCode);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(url).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getCountAndRateByStudentId")
    @Operation(summary = "获取学生任务完成数量和完成率")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result getCountAndRateByStudentId() {
        StudentUser studentLoginUser = loginUserProvider.getStudentLoginUser();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(taskService.getCountAndRateByStudentId(studentLoginUser.getId())).build();
    }

    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/getStatusByLocationTask/{taskId}")
    @Operation(summary = "查看实地打卡任务是否已经完成人脸识别")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "taskId", description = "任务id", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true)
    })
    public Result getStatusByLocationTask(@PathVariable Integer taskId) {
        StudentUser user = loginUserProvider.getStudentLoginUser();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(taskService.getStatusByLocationTask(taskId, user.getId())).build();
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
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(taskService.getDoneStatus(taskId, studentId)).build();
    }
}
