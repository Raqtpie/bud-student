package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
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
import top.turingteam.budstudent.pojo.dto.AdvertisementDto;
import top.turingteam.budstudent.pojo.dto.AdvertisementPushDto;
import top.turingteam.budstudent.pojo.vo.AdvertisementAddVo;
import top.turingteam.budstudent.pojo.vo.AdvertisementAuditVo;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.Ad2tagService;
import top.turingteam.budstudent.service.AdvertisementService;

import java.util.List;

/**
 * 广告管理模块
 *
 * @author howe
 */
@RestController
@RequestMapping("/advertisement")
@RequiredArgsConstructor
@Tag(name = "广告管理模块")
public class AdvertisementController {
    public final AdvertisementService advertisementService;

    public final Ad2tagService ad2tagService;

    @GetMapping("")
    @Operation(summary = "查看广告列表", description = "如果是用户，会得到自己申请的广告列表。如果是超级管理员，会得到所有广告列表")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result list() {
        List<AdvertisementDto> list = advertisementService.getList();
        return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(list).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "查看广告详情", description = "查看广告详情")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result detail(@Parameter(description = "广告id", required = true) @PathVariable Integer id) {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(advertisementService.detail(id)).build();
    }

    @PostMapping("")
    @Operation(summary = "用户申请广告", description = "申请广告的接口。如果是商家申请的，后续超级管理员审核通过后会生成订单")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "coverPhoto", description = "广告封面", in = ParameterIn.QUERY, schema = @Schema(type = "file"), required = true),
            @Parameter(name = "contentPhoto", description = "广告内容图片", in = ParameterIn.QUERY, schema = @Schema(type = "file"), required = true)
    })
    public Result add(@Validated @RequestPart("advertisementAddVo") AdvertisementAddVo advertisementAddVo,
                      @RequestParam("coverPhoto") MultipartFile coverPhoto, @RequestParam("contentPhoto") MultipartFile contentPhoto) {
        advertisementService.AddAdvertisement(advertisementAddVo, coverPhoto, contentPhoto);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("申请成功").build();
    }

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @DeleteMapping("/{id}")
    @Operation(summary = "删除广告", description = "超级管理员删除广告的接口，普通用户无法删除")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result delete(@Parameter(description = "广告id", required = true) @PathVariable Integer id) {
        advertisementService.deleteAdvertisement(id);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("删除成功").build();
    }

    @SaCheckRole(UserType.SUPER_ADMIN_STR)
    @PutMapping("/audit")
    @Operation(summary = "审核广告", description = "超级管理员审核广告的接口，审核通过后会生成订单")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result audit(@Validated @RequestBody AdvertisementAuditVo auditVo) {
        advertisementService.auditAdvertisement(auditVo.getId(), auditVo.isAuditStatus());
        return Result.builder().code(ResultCode.SUCCESS.code).msg("审核成功").build();
    }

    @PutMapping()
    @Operation(summary = "发布广告", description = "用户发布广告的接口，需要审核通过，如果是商家必须先支付订单")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result releaseAd(@Parameter(description = "广告id", required = true) @RequestParam Integer adId) {
        advertisementService.releaseAd(adId);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("发布成功").build();
    }


    /**
     * 推送广告
     */
    @SaCheckRole(UserType.STUDENT_STR)
    @GetMapping("/push")
    @Operation(summary = "广告推送", description = "学生用户获取推送广告的接口")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result push() {
        List<AdvertisementPushDto> list = advertisementService.pushAd();
        return Result.builder().code(ResultCode.SUCCESS.code).data(list).msg("推送广告成功").build();
    }

    /**
     * 用户查看广告
     */
    @GetMapping("/count/{adId}")
    @Operation(summary = "用户广告点击统计", description = "用户点击广告时调用，将会统计对应的标签点击次数信息")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result count(@Parameter(description = "广告id", required = true) @PathVariable Integer adId) {
        ad2tagService.tagsCountAddOne(adId);
        return Result.builder().code(ResultCode.SUCCESS.code).msg("统计标签成功").build();
    }

    /**
     * 根据标签id返回广告
     */
    @GetMapping("/tag/{tagId}")
    @Operation(summary = "根据标签id广告推送", description = "根据标签id返回广告")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result tagPush(@Parameter(description = "标签id", required = true) @PathVariable Integer tagId) {
        List<AdvertisementPushDto> list = advertisementService.adByTagId(tagId);
        return Result.builder().code(ResultCode.SUCCESS.code).data(list).msg("推送广告成功").build();
    }

    /**
     * 根据广告状态返回广告列表
     */
    @GetMapping("/status/{status}")
    @Operation(summary = "根据广告状态返回广告列表", description = "根据广告状态返回广告列表")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result statusList(@Parameter(description = "广告状态：进行中（待审核+待发布），发布中，审核未通过，已过期", required = true) @PathVariable String status) {
        List<AdvertisementDto> list = advertisementService.getListByStatus(status);
        return Result.builder().code(ResultCode.SUCCESS.code).data(list).msg("查询成功").build();
    }
}
