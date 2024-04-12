package top.turingteam.budstudent.pojo.vo;

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
public class AdminRetrieveVo {
    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;
}
