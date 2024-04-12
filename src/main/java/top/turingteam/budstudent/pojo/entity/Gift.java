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
@TableName(value ="tb_gift")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gift implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * 最大的兑换次数，0表示无限制
     */
    private Integer maxExchange;

    /**
     * 
     */
    private String photoUrl;

    /**
     * 
     */
    private Integer point;

    /**
     * 
     */
    private String schoolCode;

    /**
     * 
     */
    private Long createId;

    /**
     * 
     */
    private LocalDateTime createTime;
}