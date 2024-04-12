package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 商家用户表
 *
 * @author howe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName(value = "tb_school")
public class School implements Serializable {
    /**
     * 院校代码
     */
    @TableId
    @Schema(description = "学校代码")
    @NotNull(message = "学校代码不能为空")
    private String schoolCode;

    /**
     * 学校名称
     */
    @Schema(description = "学校名称")
    @NotBlank(message = "学校名称不能为空")
    private String name;

    /**
     * 学校地址
     */
    @Schema(description = "学校地址")
    @NotBlank(message = "学校地址不能为空")
    private String address;

    /**
     * 学校邮编
     */
    @Schema(description = "学校邮编")
    @NotBlank(message = "学校邮编不能为空")
    private String postalCode;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @NotBlank(message = "联系电话不能为空")
    private String callNumber;

    /**
     * 学校简介
     */
    @Schema(description = "学校简介")
    @NotBlank(message = "学校简介不能为空")
    private String introduction;

    /**
     * 官网地址
     */
    @Schema(description = "官网地址")
    @NotBlank(message = "官网地址不能为空")
    private String websiteUrl;
}