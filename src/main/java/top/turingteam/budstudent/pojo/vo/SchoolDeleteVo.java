package top.turingteam.budstudent.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class SchoolDeleteVo {
    @Schema(description = "学校代码")
    @NotNull(message = "学校代码不能为空")
    private String schoolCode;
}
