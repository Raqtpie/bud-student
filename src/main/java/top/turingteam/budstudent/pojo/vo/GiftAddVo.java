package top.turingteam.budstudent.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftAddVo implements Serializable {
    /**
     *
     */
    @Schema(description = "物品名称")
    @NotBlank
    private String name;

    /**
     *
     */
    @Schema(description = "描述(兑换方式)")
    @NotBlank
    private String description;

    /**
     * 最大的兑换次数，-1表示无限制
     */
    @Schema(description = "礼品的最大的兑换次数(礼品库存)。默认是-1，也就是无限制次数。")
    private Integer maxExchange;

    /**
     *
     */
    @Schema(description = "单次兑换所需积分数,最小为1")
    @Min(1)
    @NotNull
    private Integer point;
}