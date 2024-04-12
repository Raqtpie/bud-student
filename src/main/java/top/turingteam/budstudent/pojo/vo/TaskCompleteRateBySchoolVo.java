package top.turingteam.budstudent.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TaskCompleteRateBySchoolVo {
    /**
     * 学校
     */
    private String schoolName;

    /**
     * 任务完成次数
     */
    private Integer completeCount;

    /**
     * 任务完成率
     */
    private Long completeRate;
}
