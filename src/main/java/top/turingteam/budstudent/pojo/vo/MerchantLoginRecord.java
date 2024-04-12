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
 * 商家登录日志表
 * @author howe
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_merchant_login_record")
public class MerchantLoginRecord {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商家id
     */
    @TableField("merchant_id")
    private Long merchantId;

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
