package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 商家用户表
 * @author howe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_merchant_user")
public class MerchantUser {
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 手机号
     */
    @TableField("phone")
    @NotBlank(message = "phone不能为空")
    @Schema(description = "手机号")
    private String phone;

    /**
     * 密码
     */
    @TableField("password")
    @NotBlank(message = "password不能为空")
    @Schema(description = "密码")
    private String password;

    /**
     * 封禁/过期状态 0正常 1封禁
     */
    @TableField("ban_expire_status")
    private Boolean banExpireStatus;

}
