package top.turingteam.budstudent.pojo.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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
public class LowTaskCompleteRateStudentVo {
    /**
     * 学生id
     */
    @ExcelIgnore
    private Long id;

    /**
     * 学生姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 学院
     */
    @ExcelProperty("学院")
    private String collegeName;

    /**
     * 专业
     */
    @ExcelProperty("专业")
    private String majorName;

    /**
     * 班级
     */
    @ExcelProperty("班级")
    private String className;

    /**
     * 任务完成次数
     */
    @ExcelProperty("任务完成次数")
    private Integer completeCount;

    /**
     * 任务完成率
     */
    @ExcelProperty("任务完成率")
    private Long completeRate;
}
