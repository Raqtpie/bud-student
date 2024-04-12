package top.turingteam.budstudent.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantUserVo {
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

}
