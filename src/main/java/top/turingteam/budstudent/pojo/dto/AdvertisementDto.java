package top.turingteam.budstudent.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDto {
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
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 标签名称
     */
    private List<String> tags;
}
