package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.turingteam.budstudent.pojo.entity.OnlineTask;
import top.turingteam.budstudent.pojo.vo.LowTaskCompleteRateStudentVo;

import java.util.List;

/**
 * @author Raqtpie
 */
@Mapper
public interface OnlineTaskMapper extends BaseMapper<OnlineTask> {
    /**
     * 获取未完成的每日任务
     * @param schoolCode 学校代码
     * @param studentId 学生id
     * @param date 日期
     * @return 未完成的任务
     */
    List<OnlineTask> getOnlineTaskNotYetDone(String schoolCode, Long studentId, String date);

    /**
     * 获取低完成率学生列表
     * @param schoolCode 学校代码
     * @return 低完成率学生列表
     */
    LowTaskCompleteRateStudentVo[] getLowerCompleteRateStudentList(String schoolCode);
}
