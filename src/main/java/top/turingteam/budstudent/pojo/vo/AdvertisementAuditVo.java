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
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementAuditVo {
    /**
     * 广告id
     */
    @Schema(description = "广告id")
    @NotNull
    private Integer id;
    /**
     * 审核状态
     */
    @Schema(description = "审核状态")
    @NotNull
    private boolean auditStatus;
}
