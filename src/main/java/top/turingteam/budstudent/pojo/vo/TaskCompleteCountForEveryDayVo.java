package top.turingteam.budstudent.pojo.vo;

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
public class TaskCompleteCountForEveryDayVo {
    /**
     * 日期
     */
    private String date;

    /**
     * 每日任务完成次数
     */
    private Integer onlineTaskCompleteCount;

    /**
     * 剧情任务完成次数
     */
    private Integer taskCompleteCount;
}
