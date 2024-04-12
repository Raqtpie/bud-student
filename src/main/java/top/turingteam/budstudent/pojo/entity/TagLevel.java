package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_tag_level")
public class TagLevel {
    /**
     * 等级
     */
    @NotNull(message = "等级不能为空")
    @Min(value = 0, message = "等级不能小于0")
    @Max(value = 10, message = "等级不能大于10")
    @TableField("level")
    private Integer level;

    /**
     * 价格
     */
    @NotNull(message = "价格不能为空")
    private Integer price;
}
