package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.turingteam.budstudent.pojo.vo.StudentActivityCountVo;
import top.turingteam.budstudent.pojo.vo.StudentLoginRecord;

/**
 * @author Raqtpie
 */
@Mapper
public interface StudentLoginRecordMapper extends BaseMapper<StudentLoginRecord> {
    @Select("SELECT DATE(`time`) AS `date`, COUNT(*) AS `login_count` FROM turing.tb_stu_login_record WHERE `time` >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) GROUP BY DATE(`time`) ORDER BY `date` DESC;")
    StudentActivityCountVo[] selectStudentActivityCount();
}
