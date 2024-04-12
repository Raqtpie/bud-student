package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_task_complete")
public class TaskCompleteRecord {
    /**
     * 任务完成记录id
     */
    @TableId(type = IdType.AUTO)
    @Hidden
    private Integer id;

    /**
     * 学校代码
     */
    @TableField("school_code")
    @Hidden
    private String schoolCode;

    /**
     * 任务id(每日任务)
     */
    @TableField("task_online_id")
    @Schema(description = "任务id(每日任务)")
    private Integer onlineTaskId;

    /**
     * 任务id
     */
    @TableField("task_id")
    @Schema(description = "任务id")
    private Integer taskId;

    /**
     * 学生id
     */
    @TableField("stu_id")
    @Hidden
    private Long studentId;

    /**
     * 提交图片URL
     */
    @TableField("img_url")
    @Hidden
    private String imgUrl;

    /**
     * 提交地点，经纬度；非拍照任务无需提交地点
     */
    @Schema(description = "提交地点，经纬度；每日任务无需提交地点")
    private Point location;

    /**
     * 提交时间
     */
    @TableField("complete_time")
    @Hidden
    private Date completeTime;

    /**
     * 状态
     * -1 审核失败，需要人工审核
     * 0  未审核
     * 1  审核通过
     * 2  审核未通过
     * 3  人脸识别成功，还需要手动完成打卡
     */
    @Hidden
    private Integer status;
}
