package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author howe
 */
@TableName(value ="tb_order")
@Data
public class Order implements Serializable {
    /**
     * 
     */
    @TableId
    private String orderId;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private Integer adId;

    /**
     * 
     */
    private Double amount;

    /**
     * 默认false为未支付
     */
    private Integer payStatus;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime completeTime;
}