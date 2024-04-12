package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.turingteam.budstudent.pojo.entity.StudentInfo;

/**
 * @author Raqtpie
 */
@Mapper
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {
    /**
     * 获取省份激活人数
     * @param postalCode 省份邮政编码
     * @return 省份激活人数
     */
    Long getActiveStudentCountByProvince(String postalCode);

    /**
     * 获取某个学校各个省份的激活人数
     * @param postalCode 省份邮政编码
     * @param schoolCode 学校代码
     * @return 某个学校各个省份的激活人数
     */
    Long getActiveStudentCountByProvinceAndSchoolCode(String postalCode, String schoolCode);

    /**
     * 获取学校总人数
     *
     * @param schoolCode 学校代码
     * @return 学校总人数
     */
    Long selectCountBySchoolId(String schoolCode);

    /**
     * 获取学校激活人数
     * @param schoolCode 学校代码
     * @return 学校激活人数
     */
    Long selectActiveCountBySchoolId(String schoolCode);

    /**
     * 获取学校学生名单
     * @param schoolCode 学校代码
     * @param page 页码
     * @param size 每页大小
     * @return 学校学生名单
     */
    StudentInfo[] getList(String schoolCode, Integer page, Integer size);
}
