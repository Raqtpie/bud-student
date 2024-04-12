package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@TableName(value = "tb_college")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class College  {
    private String schoolCode;

    private String collageCode;

    private String name;

    private String introduction;

    private String websiteUrl;
}