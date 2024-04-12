package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author howe
 */
@TableName(value = "tb_college2major")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class College2major implements Serializable {

    @Schema(description = "学校代码")
    @NotBlank
    private String schoolCode;
    @Schema(description = "学院代码")
    @NotBlank
    private String collegeCode;

    @Schema(description = "专业代码")
    @NotBlank
    private String majorCode;
}