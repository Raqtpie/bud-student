package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.turingteam.budstudent.pojo.entity.TaskCompleteRecord;

/**
 * @author Raqtpie
 */
@Mapper
public interface TaskCompleteRecordMapper extends BaseMapper<TaskCompleteRecord> {
    /**
     * 获取学院所有学生的任务完成次数
     * @param schoolId 学校id
     * @param collegeId 学院id
     * @return 学校各学院的任务完成次数
     */
    Long getCompleteCountBySchoolIdAndCollegeId(String schoolId, String collegeId);

    /**
     * 新增记录
     * @param taskCompleteRecord 任务完成记录
     */
    void insertRecord(TaskCompleteRecord taskCompleteRecord);

    /**
     * 更新记录
     * @param taskCompleteRecord 任务完成记录
     */
    void updateRecord(TaskCompleteRecord taskCompleteRecord);
}
