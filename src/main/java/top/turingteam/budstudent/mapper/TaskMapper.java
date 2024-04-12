package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.turingteam.budstudent.pojo.entity.Task;
import top.turingteam.budstudent.pojo.vo.LowTaskCompleteRateStudentVo;

import java.util.List;

/**
 * @author Raqtpie
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {
    /**
     * 新增任务
     * @param task 任务
     */
    void addTask(Task task);

    /**
     * 更新任务
     * @param task 任务
     */
    void updateTask(Task task);

    /**
     * 获取任务数量
     * @param schoolCode 学校代码
     * @return 任务数量
     */
    Integer getTaskMaxNumberBySchoolCode(String schoolCode);

    /**
     * 获取已经开始但未完成的任务
     * @param schoolCode 学校代码
     * @param id 学生id
     * @param date 日期
     * @return 任务列表
     */
    List<Task> getTaskByStudentIdAndSchoolCode(String schoolCode, Long id, String date);

    /**
     * 获取所有任务
     * @param schoolCode 学校代码
     * @return 任务列表
     */
    List<Task> getAllTask(String schoolCode);

    /**
     * 提交任务
     * @param schoolCode 学校代码
     * @return 任务列表
     */
    List<LowTaskCompleteRateStudentVo> getLowerCompleteRateStudentList(String schoolCode);

    /**
     * 根据id获取任务
     * @param id 任务id
     * @return 任务
     */
    Task selectById(Integer id);
}