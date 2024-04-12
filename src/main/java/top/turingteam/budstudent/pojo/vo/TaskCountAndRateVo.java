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
public class TaskCountAndRateVo {
    /**
     * 任务完成率
     */
    Long taskCount;

    /**
     * 任务完成率
     */
    Long taskRate;
}
