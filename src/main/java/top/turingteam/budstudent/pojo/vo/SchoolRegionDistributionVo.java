package top.turingteam.budstudent.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRegionDistributionVo {
    /**
     * 省
     */
    private String provinceName;

    /**
     * 学校数量
     */
    private Long schoolCount;
}
