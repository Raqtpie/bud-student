package top.turingteam.budstudent.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 标签内容
     */
    private String topic;
}
