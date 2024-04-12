package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.turingteam.budstudent.pojo.entity.StudentPoint;
import top.turingteam.budstudent.pojo.vo.StudentPointVo;

/**
 * @author Raqtpie
 */
@Mapper
public interface StudentPointMapper extends BaseMapper<StudentPoint> {
    /**
     * 获取某学校积分排行榜
     * @param schoolCode 学校代码
     * @return 学校积分排行榜(前100名)
     */
    StudentPointVo[] selectSchoolRank(@Param("schoolCode") String schoolCode);

    /**
     * 获取所有学校总积分排行榜
     * @return 总积分排行榜(前100名)
     */
    StudentPointVo[] selectTotalRank();
}
