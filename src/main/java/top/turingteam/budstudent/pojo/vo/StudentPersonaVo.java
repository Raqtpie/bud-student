package top.turingteam.budstudent.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class StudentPersonaVo {
    @Schema(description = "学生个人形象名称")
    @NotBlank(message = "名字不能为空")
    private String name;

    @Schema(description = "学生个人形象性别")
    @NotBlank(message = "性别不能为空")
    @Pattern(regexp = "^(男|女)$", message = "性别必须为“男”或“女”")
    private String gender;

    @Schema(description = "兑换所需积分")
    @NotNull(message = "兑换所需积分不能为空")
    private Integer requirePoint;
}
