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
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TableName("tb_stu_point_record")
public class StudentPointRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("student_id")
    private Long studentId;

    private String reason;

    @TableField("points")
    private Integer point;

    @TableField("create_time")
    private String time;
}
