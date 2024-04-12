package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 学生个人形象
 * @author Raqtie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TableName("tb_stu_persona")
public class StudentPersona {
    @TableId(type = IdType.AUTO)
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

    @Hidden
    @TableField("create_time")
    private Date createTime;

    @Hidden
    @TableField("update_time")
    private Date updateTime;
}
