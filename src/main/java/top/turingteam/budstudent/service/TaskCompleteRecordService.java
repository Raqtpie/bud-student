package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.entity.TaskCompleteRecord;
import top.turingteam.budstudent.pojo.vo.TaskCompleteCountForEveryDayVo;
import top.turingteam.budstudent.pojo.vo.TaskCompleteRateByCollegeVo;
import top.turingteam.budstudent.pojo.vo.TaskCompleteRateBySchoolVo;

import java.util.List;

/**
 * @author Raqtpie
 */
public interface TaskCompleteRecordService extends IService<TaskCompleteRecord> {
    /**
     * 获取完成任务的数据
     * @return 各学校任务完成情况
     */
    List<TaskCompleteRateBySchoolVo> getCompleteData();

    /**
     * 根据学校id获取完成任务的数据
     * @param schoolId 学校id
     * @return 各学院任务完成情况
     */
    List<TaskCompleteRateByCollegeVo> getCompleteDataBySchoolId(String schoolId);

    /**
     * 获取每日任务完成次数
     * @param schoolId 学校id
     * @return 每日任务完成次数
     */
    List<TaskCompleteCountForEveryDayVo> getCompleteCountForEveryDay(String schoolId);
}
