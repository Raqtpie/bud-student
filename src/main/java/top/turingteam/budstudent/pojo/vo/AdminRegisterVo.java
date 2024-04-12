package top.turingteam.budstudent.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRegisterVo {
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Schema(description = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @Schema(description = "学校代码")
    @NotNull(message = "学校代码不能为空")
    private String school;
}
