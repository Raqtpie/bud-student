package top.turingteam.budstudent.pojo.dto;

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
public class AdvertisementPushDto {
    /**
     * 广告id
     */
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
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 标签名称
     */
    private List<String> tags;
}
