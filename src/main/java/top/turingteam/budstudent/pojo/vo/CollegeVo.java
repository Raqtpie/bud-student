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
@AllArgsConstructor
@NoArgsConstructor
public class CollegeVo {
    @Schema(description = "学院代码")
    @NotNull
    private String collageCode;

    @Schema(description = "学院名称")
    @NotBlank
    private String name;

    @Schema(description = "学院简介")
    @NotBlank
    private String introduction;

    @Schema(description = "学院网站地址")
    private String websiteUrl;
}
