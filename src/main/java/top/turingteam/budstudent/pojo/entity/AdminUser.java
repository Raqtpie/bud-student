package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 管理员用户表
 * @author howe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AdminUser {
    @TableId(type = IdType.INPUT)
    @Schema(description = "id")
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @TableField("username")
    @Schema(description = "用户名")
    private String username;

    /**
     * 昵称
     */
    @TableField("nickname")
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @TableField("password")
    @Schema(description = "密码")
    private String password;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @TableField("phone")
    @Schema(description = "手机号")
    private String phone;

    /**
     * 学校
     */
    @NotBlank(message = "学校不能为空")
    @TableField("school")
    @Schema(description = "学校")
    private String school;

    /**
     * 默认正常false
     */
    @TableField("delete_flag")
    private boolean deleteFlag;

    /**
     * 默认为false为学校管理员
     */
    @TableField("role")
    private boolean role;
}
