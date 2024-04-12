package top.turingteam.budstudent.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 学生登录日志表
 * @author Raqtpie
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_stu_login_record")
public class StudentLoginRecord {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    @TableField("student_id")
    private Long studentId;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 登录城市
     */
    private String city;

    /**
     * 登录是否成功
     */
    private Boolean status;
}
