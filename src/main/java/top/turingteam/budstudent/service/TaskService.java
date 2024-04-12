package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.pojo.dto.TaskDto;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.entity.Point;
import top.turingteam.budstudent.pojo.entity.Task;
import top.turingteam.budstudent.pojo.vo.LowTaskCompleteRateStudentVo;
import top.turingteam.budstudent.pojo.vo.TaskCountAndRateVo;

import java.util.List;

/**
 * @author Raqtpie
 */
public interface TaskService extends IService<Task> {
    /**
     * 新增任务
     * @param task 任务
     * @param user 执行该操作的管理员用户
     */
    void addTask(Task task, AdminUser user);

    /**
     * 更新任务
     * @param task 任务
     */
    void updateTask(Task task);

    /**
     * 删除任务
     * @param id 任务id
     */
    void deleteTask(Integer id);

    /**
     * 获取所有开始但未完成的任务
     * @param id 学生id
     * @return 任务列表
     */
    List<TaskDto> getTask(Long id);

    /**
     * 获取所有任务
     * @param schoolCode 学校代码
     * @return 任务列表
     */
    List<Task> getAllTask(String schoolCode);

    /**
     * 提交任务：拍照
     * @param taskId 任务id
     * @param file 提交图片
     * @param studentId 学生id
     */
    void submitTask(Integer taskId, MultipartFile file, Long studentId);

    /**
     * 提交任务：选择题
     * @param taskId 任务id
     * @param answer 答案
     * @param studentId 学生id
     */
    void submitTask(Integer taskId, String answer, Long studentId);

    /**
     * 提交任务：CV识别
     * @param taskId 任务id
     * @param studentId 学生id
     */
    void submitTask(Integer taskId, Long studentId);

    /**
     * 提交任务：前端完成最后的打卡
     * @param taskId 任务id
     * @param point 地理位置
     * @param studentId 学生id
     */
    void submitTask(Integer taskId, Point point, Long studentId);

    /**
     * 低完成率学生列表
     * @param schoolCode 学校代码
     * @return 学生列表
     */
    List<LowTaskCompleteRateStudentVo> getLowerCompleteRateStudentList(String schoolCode);

    /**
     * 低完成率学生列表
     * @param schoolCode 学校代码
     * @return 学生列表
     */
    String getLowerCompleteRateStudentListXlsx(String schoolCode);

    /**
     * 获取学生任务完成数量和完成率
     * @param studentId 学生id
     * @return 任务完成数量和完成率
     */
    TaskCountAndRateVo getCountAndRateByStudentId(Long studentId);

    /**
     * 获取任务
     * @param taskId 任务id
     * @param id 学生id
     * @return 任务
     */
    Boolean getStatusByLocationTask(Integer taskId, Long id);

    /**
     * 获取任务完成状态
     * @param taskId 任务id
     * @param studentId 学生id
     * @return 任务完成状态
     */
    Boolean getDoneStatus(Integer taskId, Long studentId);
}
