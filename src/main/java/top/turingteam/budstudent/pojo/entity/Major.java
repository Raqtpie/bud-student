package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author howe
 */
@TableName(value ="tb_major")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Major implements Serializable {

    @TableId
    @Schema(description = "专业代码")
    @NotBlank(message = "专业代码不能为空")
    private String majorCode;

    @Schema(description = "专业名称")
    @NotBlank(message = "专业名称不能为空")
    private String name;

    @Schema(description = "专业类别")
    @NotBlank(message = "专业类别不能为空")
    private String degreeType;

    @Schema(description = "专业学制")
    @NotNull(message = "专业学制不能为空")
    private Integer duration;

}