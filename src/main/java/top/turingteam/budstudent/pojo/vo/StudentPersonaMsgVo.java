package top.turingteam.budstudent.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPersonaMsgVo {
    @Schema(description = "学生个人形象id")
    @NotNull(message = "id不能为空")
    private Integer id;

    @Schema(description = "学生个人形象名称")
    @NotBlank(message = "名字不能为空")
    private String name;

    @Schema(description = "学生个人形象性别")
    @NotBlank(message = "性别不能为空")
    private String gender;

    @Schema(description = "形象图片url")
    private String url;

    @Schema(description = "兑换所需积分")
    private Integer requirePoint;

    @Schema(description = "是否拥有")
    private Boolean ownFlag;

    @Schema(description = "是否使用")
    private Boolean useFlag;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
}
