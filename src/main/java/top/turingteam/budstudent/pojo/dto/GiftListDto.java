package top.turingteam.budstudent.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftListDto implements Serializable {
    private Integer id;

    private Integer status;

    private LocalDateTime exchangeTime;
    private LocalDateTime getTime;

    private Integer giftId;

    private String name;

    private String description;

    private String photoUrl;
}
