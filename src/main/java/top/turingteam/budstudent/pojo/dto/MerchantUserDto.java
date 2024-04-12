package top.turingteam.budstudent.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantUserDto {

    private Long id;

    /**
     * 手机号
     */
    private String phone;
}
