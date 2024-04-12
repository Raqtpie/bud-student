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
public class AdminLoginVo {
    @Schema(description = "用户名或手机号")
    @NotBlank(message = "用户不能为空")
    private String username;
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    @Schema(description = "是否为超级管理员")
    @NotBlank(message = "权限标识不能为空")
    private String isSuperAdmin;
}