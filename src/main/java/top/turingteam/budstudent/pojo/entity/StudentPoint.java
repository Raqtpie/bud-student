package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 学生积分表
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_stu_point")
public class StudentPoint {
    /**
     * 学生id
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 学校代码
     */
    @TableField("school_code")
    private String schoolCode;

    /**
     * 任务完成次数
     */
    @TableField("task_completed_count")
    private Integer taskCompleteCount;

    /**
     * 最后一次完成任务的时间
     */
    @TableField("last_completed_task_time")
    private Long lastTaskCompleteTime;

    /**
     * 总获取积分
     */
    @TableField("points_total")
    private Integer pointsTotal;

    /**
     * 当前积分
     */
    @TableField("points_now")
    private Integer pointsNow;
}
