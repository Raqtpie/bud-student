package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@TableName("tb_task_object")
public class TaskObject {
    /**
     * 物品id
     */
    @Schema(description = "物品id")
    private Integer id;

    /**
     * 物品名称
     */
    @Schema(description = "物品名称")
    private String objectName;

    /**
     * 物品名称(中文)
     */
    @Schema(description = "物品名称(中文)")
    private String objectNameZh;
}
