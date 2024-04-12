package top.turingteam.budstudent.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 排行榜 返回给前端的数据
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentPointVo {
    /**
     * 学生id
     */
    private String id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 总积分
     */
    private Integer point;
}
