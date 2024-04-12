package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.turingteam.budstudent.pojo.entity.College2major;
import top.turingteam.budstudent.pojo.entity.Major;

/**
 * @author howe
 */
@Mapper
public interface College2majorMapper extends BaseMapper<College2major> {
    @Select("select * from turing.tb_major where major_code in (select major_code from turing.tb_college2major where college_code = #{collegeCode} and school_code = #{schoolCode})")
    Major[] selectMajorByCollegeCode(String collegeCode, String schoolCode);
}




