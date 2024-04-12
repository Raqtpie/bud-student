package top.turingteam.budstudent.pojo.dto;

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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TaskDto {
    /**
     * id
     */
    private Integer id;

    /**
     * 创建者id
     */
    private Long creatorId;

    /**
     * 学校代码
     */
    private String schoolCode;

    /**
     * 任务id(基于SchoolCode自增)
     */
    private Integer taskSubId;

    /**
     * 任务类型
     * 0 主线
     * 1 支线：拍照
     * 2 支线：选择
     */
    private Integer taskType;

    /**
     * 主题/标题
     */
    private String topic;

    /**
     * 描述
     */
    private String description;

    /**
     * 需要的物品id
     * 如果不是拍照任务为空
     */
    private Integer objId;

    /**
     * 答案(选择题)
     * 如果不是选择题为空
     */
    private String answer;

    /**
     * 位置点
     */
    private Point location;

    /**
     * 半径
     * 如果不是实地打卡任务为空
     */
    private Integer radius;

    /**
     * 积分奖励
     */
    private Integer pointAward;

    /**
     * 对应的主线任务
     * 如果本身就是主线任务，则为空
     */
    private Integer parentTaskId;

    /**
     * 前置任务
     * 如果没有前置任务，则为空
     */
    private Integer preTaskId;

    /**
     * 前置任务是否完成
     */
    private Boolean preTaskIsDone;

    /**
     * 任务开始时间
     */
    private Date startTime;

    /**
     * 任务结束时间
     */
    private Date endTime;
}
