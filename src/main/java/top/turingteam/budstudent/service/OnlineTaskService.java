package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.entity.OnlineTask;
import top.turingteam.budstudent.pojo.entity.StudentUser;
import top.turingteam.budstudent.pojo.vo.LowTaskCompleteRateStudentVo;

import java.util.List;

/**
 * @author Raqtpie
 */
public interface OnlineTaskService extends IService<OnlineTask> {
    /**
     * 获取今日任务
     * @param schoolCode 学校代码
     * @return 今日任务
     */
    OnlineTask getOnlineTaskToday(String schoolCode);

    /**
     * 获取未完成的每日任务
     * @param studentUser 学生
     * @return 未完成的任务
     */
    List<OnlineTask> getOnlineTaskNotYetDone(StudentUser studentUser);


    /**
     * 获取返校前任务
     * @param schoolCode 学校代码
     * @return 任务列表
     */
    List<OnlineTask> getTaskBeforeToday(String schoolCode);

    /**
     * 提交每日任务
     * @param onlineTaskId 任务id
     * @param studentLoginUser 学生用户
     * @param file 任务图片
     */
    void submitOnlineTask(Integer onlineTaskId, StudentUser studentLoginUser, MultipartFile file);

    /**
     * 添加返校前每日任务
     *
     * @param onlineTask 任务
     * @param user       管理员用户
     */
    void addOnlineTask(OnlineTask onlineTask, AdminUser user);

    /**
     * 删除每日任务
     * @param taskId 任务id
     * @param user 管理员用户
     */
    void deleteOnlineTask(Integer taskId, AdminUser user);

    /**
     * 获取低任务完成率学生列表
     * @param schoolCode 学校代码
     * @return 学生列表
     */
    List<LowTaskCompleteRateStudentVo> getLowerCompleteRateStudentList(String schoolCode);

    /**
     * 获取低任务完成率学生列表Xlsx
     * @param schoolCode 学校代码
     * @return 文件url
     */
    String getLowerCompleteRateStudentListXlsx(String schoolCode);

    /**
     * 获取任务完成状态
     * @param taskId 任务id
     * @param studentId 学生id
     * @return 任务完成状态
     */
    Boolean getDoneStatus(Integer taskId, Long studentId);
}
