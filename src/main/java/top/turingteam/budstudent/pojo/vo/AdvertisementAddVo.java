package top.turingteam.budstudent.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementAddVo {
    /**
     * 广告类型
     */
    @Schema(description = "广告类型")
    @NotBlank
    private String type;

    /**
     * 标题
     */
    @Schema(description = "标题")
    @NotBlank
    private String topic;

    /**
     * 内容
     */
    @Schema(description = "内容")
    @NotBlank
    private String content;

//    /**
//     * 封面图片
//     */
//    @Schema(description = "封面图片")
//    @NotBlank
//    private String coverUrl;
//
//    /**
//     * 内容图片
//     */
//    @Schema(description = "内容图片")
//    @NotBlank
//    private String contentUrl;

    /**
     * 发布天数
     */
    @Schema(description = "发布天数")
    @NotNull
    private Integer publishDays;

    /**
     * 来自地区(邮政编码)
     */
    @Schema(description = "指定地区(邮政编码)")
    private String forRegion;

    /**
     * 来自学校(学校代码)
     */
    @Schema(description = "指定学校(学校代码)")
    private String forSchool;

    /**
     * 来自学院(学院代码)
     */
    @Schema(description = "指定学院(学院代码)")
    private String forCollege;

    /**
     * 来自性别(男-女)
     */
    @Schema(description = "指定性别(男-女)")
    private String forGender;

    /**
     * 标签id
     */
    @Schema(description = "标签id集合")
    List<Integer> tags;
}