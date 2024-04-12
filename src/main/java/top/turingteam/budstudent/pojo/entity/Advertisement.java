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
@TableName(value = "tb_advertisement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement implements Serializable {
    /**
     * 广告id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 广告类型
     */
    private String type;

    /**
     * 标题
     */
    private String topic;

    /**
     * 内容
     */
    private String content;

    /**
     * 封面图片
     */
    private String coverUrl;

    /**
     * 内容图片
     */
    private String contentUrl;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 发布天数
     */
    private Integer publishDays;

    /**
     * 发布用户id
     */
    private Long publishUserId;

    /**
     * 审核用户id
     */
    private Long auditId;

    /**
     * 来自地区
     */
    private String forRegion;

    /**
     * 来自学校
     */
    private String forSchool;

    /**
     * 来自学院
     */
    private String forCollege;

    /**
     * 适用性别
     */
    private String forGender;

    /**
     * 提交时间
     */
    private LocalDateTime submitTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
}