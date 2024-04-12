package top.turingteam.budstudent.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {
    @Schema(description = "标签")
    private String topic;
    @Schema(description = "等级")
    private Integer level;
}
