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
 * 管理员登录日志表
 * @author howe
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_admin_login_record")
public class AdminLoginRecord {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员id
     */
    @TableField("admin_id")
    private Long adminId;

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
