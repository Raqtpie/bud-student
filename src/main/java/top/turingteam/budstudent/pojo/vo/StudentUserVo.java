package top.turingteam.budstudent.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SuperBuilder
public class StudentUserVo {
    /**
     * 身份证号
     */
    @ExcelProperty("身份证号")
    @NotBlank(message = "身份证号不能为空")
    private String idCard;

    /**
     * 手机号
     */
    @ExcelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 录取通知书编号
     */
    @ExcelProperty("录取通知书编号")
    @NotBlank(message = "录取通知书编号不能为空")
    private String noticeNumber;

    /**
     * 高考考生号
     */
    @ExcelProperty("高考考生号")
    @NotBlank(message = "高考考生号不能为空")
    private String examNumber;

    /**
     * 学院代码
     */
    @ExcelProperty("学院代码")
    @NotNull(message = "学院代码不能为空")
    private String collegeCode;

    /**
     * 专业代码
     */
    @ExcelProperty("专业代码")
    @NotNull(message = "专业代码不能为空")
    private String majorCode;

    /**
     * 班级
     */
    @ExcelProperty("班级")
    @NotBlank(message = "班级不能为空")
    private String className;

    /**
     * 邮政编码
     */
    @ExcelProperty("邮政编码")
    @NotBlank(message = "邮政编码不能为空")
    private String postalCode;

    /**
     * 地址
     */
    @ExcelProperty("地址")
    @NotBlank(message = "地址不能为空")
    private String address;
}
