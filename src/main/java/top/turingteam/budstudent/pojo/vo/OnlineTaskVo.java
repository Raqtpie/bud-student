package top.turingteam.budstudent.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OnlineTaskVo {
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
