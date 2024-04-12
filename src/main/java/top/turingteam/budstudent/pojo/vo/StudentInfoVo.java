package top.turingteam.budstudent.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StudentInfoVo {
    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别
     */
    private String gender;

    /**
     * 学校
     */
    private String school;

    /**
     * 学院
     */
    private String collegeName;

    /**
     * 专业
     */
    private String major;

    /**
     * 学生班级
     */
    private String className;

    /**
     * 用户个性签名
     */
    private String signature;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 家庭住址
     */
    private String address;
}
