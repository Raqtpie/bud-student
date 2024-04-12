package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author howe
 */
@TableName(value ="tb_user_gift")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGift implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private Integer giftId;

    /**
     * 领取状态(默认未领取)
     */
    private Integer status;

    /**
     * 兑换时间
     */
    private LocalDateTime exchangeTime;

    /**
     * 领取时间
     */
    private LocalDateTime getTime;
}