package top.turingteam.budstudent.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import top.turingteam.budstudent.pojo.entity.Point;

import java.util.Date;

/**
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TaskVo {
    /**
     * 任务类型
     * 0 主线
     * 1 支线：拍照
     * 2 支线：选择
     */
    @NotNull(message = "必须填写类型")
    @Schema(description = "任务类型")
    private Integer taskType;

    /**
     * 主题/标题
     */
    @NotBlank(message = "主题不能为空")
    @Schema(description = "主题")
    private String topic;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    @Schema(description = "描述，格式为Json，如果为纯描述，则只包含字段content，选择题包含五个字段content, A, B, C, D")
    private String description;

    /**
     * 需要的物品id
     * 如果不是拍照任务为空
     */
    @Schema(description = "物品ID")
    private Integer objId;

    /**
     * 答案(选择题)
     * 如果不是选择题为空
     */
    @Schema(description = "选择题答案，如果不是选择题为空")
    private String answer;

    /**
     * 位置点
     */
    @Schema(description = "位置，如果不是实地打卡任务为空")
    private Point location;

    /**
     * 半径
     * 如果不是实地打卡任务为空
     */
    @Schema(description = "半径，如果不是实地打卡任务为空")
    private Integer radius;

    /**
     * 积分奖励
     */
    @NotNull(message = "积分奖励值不得为空")
    @Schema(description = "积分奖励")
    private Integer pointAward;

    /**
     * 对应的主线任务
     * 如果本身就是主线任务，则为空
     */
    @Schema(description = "对应的主线任务，如果本身就是主线任务，则为空")
    private Integer parentTaskId;

    /**
     * 前置任务
     * 如果没有前置任务，则为空
     */
    @Schema(description = "前置任务，如果没有前置任务，则为空")
    private Integer preTaskId;

    /**
     * 任务开始时间
     */
    @NotNull(message = "开始时间不得为空")
    @Schema(description = "开始时间")
    private Date startTime;

    /**
     * 任务结束时间
     */
    @NotNull(message = "结束时间不得为空")
    @Schema(description = "结束时间")
    private Date endTime;
}
