package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 学生信息表
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_stu_info")
public class StudentInfo {
    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 身份证号
     */
    @TableField("id_card")
    @JsonIgnore
    private String idCard;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 录取通知书编号
     */
    @TableField("notice_number")
    @JsonIgnore
    private String noticeNumber;

    /**
     * 高考考生号
     */
    @TableField("exam_number")
    @JsonIgnore
    private String examNumber;

    /**
     * 学校代码
     */
    @TableField("school")
    private String schoolCode;

    /**
     * 学院代码（学校自定义）
     */
    @TableField("college")
    private String collegeCode;

    /**
     * 专业代码
     */
    @TableField("major")
    private String majorCode;

    /**
     * 学生班级
     */
    @TableField("user_class")
    private String className;

    /**
     * 用户个性签名
     */
    private String signature;

    /**
     * 邮政编码
     */
    @TableField("postal_code")
    private String postalCode;

    /**
     * 家庭住址
     */
    @TableField("home_address")
    private String address;

    /**
     * 头像url
     */
    @TableField("avatar_url")
    @JsonIgnore
    private String avatarUrl;

    /**
     * 实人照片url
     */
    @TableField("real_photo_url")
    @JsonIgnore
    private String photoUrl;
}
