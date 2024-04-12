package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 返校前每日任务表
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_task_online")
public class OnlineTask {
    /**
     * 任务id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "任务id")
    @NotNull(message = "任务id不能为空")
    private Integer id;

    /**
     * 学校代码
     */
    @TableField("school_code")
    @Schema(description = "学校代码")
    @NotNull(message = "学校代码不能为空")
    private String schoolCode;

    /**
     * 创建者id
     */
    @TableField("creator_id")
    @Schema(description = "创建者id")
    @NotNull(message = "创建者id不能为空")
    private Long creatorId;

    /**
     * 任务主题
     */
    @Schema(description = "任务主题")
    @NotBlank(message = "任务主题不能为空")
    private String topic;

    /**
     * 任务描述
     */
    @Schema(description = "任务描述")
    @NotBlank(message = "任务描述不能为空")
    private String description;

    /**
     * 物品id
     */
    @TableField("obj_id")
    @Schema(description = "物品id")
    private Integer objId;

    /**
     * 任务奖励
     */
    @TableField("point_award")
    @Schema(description = "完成任务可获得的积分")
    @NotNull(message = "任务奖励不能为空")
    private Integer award;

    /**
     * 任务对应日期
     */
    @TableField("date")
    @Schema(description = "任务对应日期")
    @NotNull(message = "任务对应日期不能为空")
    private Date date;
}
