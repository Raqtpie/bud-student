package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.TaskObjectService;

/**
 * @author Raqtpie
 */
@RestController
@RequestMapping("/taskObject")
@RequiredArgsConstructor
@Tag(name = "任务物品模块")
public class TaskObjectController {
    private final TaskObjectService taskObjectService;

    @SaCheckRole(value = {UserType.ADMIN_STR, UserType.SUPER_ADMIN_STR}, mode = SaMode.OR)
    @GetMapping("/getAllTaskObject")
    @Operation(summary = "获取所有任务物品")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, required = true)
    public Result getAllTaskObject() {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(taskObjectService.list()).build();
    }
}
