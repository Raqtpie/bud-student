package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 广告标签表
 * @author howe
 */
@TableName(value ="tb_tag")
@Data
public class TagEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 主题内容
     */
    private String topic;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 点击量
     */
    private Integer searchCount;
}