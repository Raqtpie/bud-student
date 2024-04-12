package top.turingteam.budstudent.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentActiveCountByProvinceVo {
    /**
     * 省
     */
    private String provinceName;

    /**
     * 激活人数
     */
    private Long activeCount;
}
