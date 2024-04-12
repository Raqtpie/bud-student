package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
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
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapping.TagMapping;
import top.turingteam.budstudent.pojo.dto.TagDto;
import top.turingteam.budstudent.pojo.entity.TagEntity;
import top.turingteam.budstudent.pojo.entity.TagLevel;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.pojo.vo.TagVo;
import top.turingteam.budstudent.service.TagService;

import java.util.List;

/**
 * 广告标签管理
 *
 * @author howe
 */
@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@Tag(name = "广告标签管理模块")
public class TagController {
    private final TagService tagService;

    private final TagMapping tagMapping;


    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @PostMapping
    @Operation(summary = "添加广告标签", description = "添加广告标签")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result add(@Validated @RequestBody TagVo tagVo) {
        TagEntity tagEntity = tagMapping.toTag(tagVo);
        LambdaQueryWrapper<TagEntity> eq = new LambdaQueryWrapper<TagEntity>()
                .eq(TagEntity::getTopic, tagEntity.getTopic());
        TagEntity one = tagService.getOne(eq);
        if (one != null) {
            throw new CustomException("标签已存在");
        }
        tagService.save(tagEntity);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("添加成功").build();
    }

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @DeleteMapping("/{id}")
    @Operation(summary = "删除广告标签", description = "删除广告标签(超级管理员)")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result delete(@PathVariable Integer id) {
        tagService.removeById(id);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build();
    }

    @GetMapping()
    @Operation(summary = "获取广告标签列表", description = "获取广告标签列表")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result list() {
        List<TagEntity> tagEntityList = tagService.list();
        List<TagDto> tagDto = tagMapping.toTagDto(tagEntityList);
        return Result.builder().code(ResultCode.SUCCESS.code).data(tagDto).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取广告标签详情", description = "获取广告标签详情")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result get(@PathVariable Integer id) {
        TagEntity tagEntity = tagService.getById(id);
        TagDto tagDto = tagMapping.toTagDto(tagEntity);
        return Result.builder().code(ResultCode.SUCCESS.code).data(tagDto).build();
    }

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @PutMapping("/level")
    @Operation(summary = "更新广告标签等级", description = "更新广告标签等级")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result updateLevel(@Validated @RequestBody TagLevel tagLevel) {
        tagService.updateTagLevel(tagLevel);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("更新成功").build();
    }

    @GetMapping("/level")
    @Operation(summary = "获取广告标签等级价格列表", description = "获取广告标签等级价格列表")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result listLevel() {
        List<TagLevel> tagLevelList = tagService.listAllTagLevel();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("获取成功").data(tagLevelList).build();
    }
}
