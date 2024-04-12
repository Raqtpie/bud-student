package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author howe
 */
@TableName(value ="tb_ad_day_amount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDayAmount implements Serializable {
    private Integer amount;
}