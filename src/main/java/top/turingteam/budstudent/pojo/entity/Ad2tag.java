package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 广告-标签关联表
 */
@TableName(value = "tb_ad2tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad2tag implements Serializable {
    /**
     *
     */
    private Integer adId;

    /**
     *
     */
    private Integer tagId;
}