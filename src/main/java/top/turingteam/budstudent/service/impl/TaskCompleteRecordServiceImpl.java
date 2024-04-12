package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kotlin.jvm.internal.Lambda;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.mapper.*;
import top.turingteam.budstudent.pojo.entity.*;
import top.turingteam.budstudent.pojo.vo.TaskCompleteCountForEveryDayVo;
import top.turingteam.budstudent.pojo.vo.TaskCompleteRateByCollegeVo;
import top.turingteam.budstudent.pojo.vo.TaskCompleteRateBySchoolVo;
import top.turingteam.budstudent.service.TaskCompleteRecordService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Raqtpie
 */
@RequiredArgsConstructor
@Service
public class TaskCompleteRecordServiceImpl extends ServiceImpl<TaskCompleteRecordMapper, TaskCompleteRecord> implements TaskCompleteRecordService {
    private final SchoolMapper schoolMapper;

    private final CollegeMapper collegeMapper;

    private final OnlineTaskMapper onlineTaskMapper;

    private final TaskMapper taskMapper;

    private final TaskCompleteRecordMapper taskCompleteRecordMapper;

    @Override
    public List<TaskCompleteRateBySchoolVo> getCompleteData() {
        List<School> schools = schoolMapper.selectList(null);
        List<TaskCompleteRateBySchoolVo> taskCompleteRecords = new ArrayList<>();
        for (School school : schools) {
            Long taskCount = getTaskCountBySchoolId(school.getSchoolCode());
            Long onlineTaskCount = getOnlineTaskCountBySchoolId(school.getSchoolCode());
            long taskTotalCount = taskCount + onlineTaskCount;
            if (taskTotalCount == 0) {
                taskCompleteRecords.add(TaskCompleteRateBySchoolVo.builder()
                        .schoolName(school.getName())
                        .completeCount(0)
                        .completeRate(0L)
                        .build());
                continue;
            }
            Long completeCount = getCompleteCountBySchoolId(school.getSchoolCode());
            Long rate = completeCount * 100 / taskTotalCount;
            taskCompleteRecords.add(TaskCompleteRateBySchoolVo.builder()
                    .schoolName(school.getName())
                    .completeCount(completeCount.intValue())
                    .completeRate(rate)
                    .build());
        }
        return taskCompleteRecords;
    }

    @Override
    public List<TaskCompleteRateByCollegeVo> getCompleteDataBySchoolId(String schoolId) {
        LambdaQueryWrapper<College> collegeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        collegeLambdaQueryWrapper.eq(College::getSchoolCode, schoolId);
        List<College> colleges = collegeMapper.selectList(collegeLambdaQueryWrapper);
        List<TaskCompleteRateByCollegeVo> taskCompleteRecords = new ArrayList<>();
        Long taskCount = getTaskCountBySchoolId(schoolId);
        Long onlineTaskCount = getOnlineTaskCountBySchoolId(schoolId);
        long taskTotalCount = taskCount + onlineTaskCount;
        if (taskTotalCount == 0) {
            return taskCompleteRecords;
        }
        for (College college : colleges) {
            Long completeCount = taskCompleteRecordMapper.getCompleteCountBySchoolIdAndCollegeId(schoolId, college.getCollageCode());
            Long rate = completeCount * 100 / taskTotalCount;
            taskCompleteRecords.add(TaskCompleteRateByCollegeVo.builder()
                    .schoolName(schoolMapper.selectById(schoolId).getName())
                    .collegeName(college.getName())
                    .completeCount(completeCount.intValue())
                    .completeRate(rate)
                    .build());
        }
        return taskCompleteRecords;
    }

    @Override
    public List<TaskCompleteCountForEveryDayVo> getCompleteCountForEveryDay(String schoolId) {
        List<OnlineTask> onlineTasks = onlineTaskMapper.selectList(new LambdaQueryWrapper<OnlineTask>().eq(OnlineTask::getSchoolCode, schoolId));
        List<Task> tasks = taskMapper.selectList(new LambdaQueryWrapper<Task>().eq(Task::getSchoolCode, schoolId));
        Map<Date, TaskCompleteCountForEveryDayVo> map = new HashMap<>(90);
        for (OnlineTask onlineTask : onlineTasks) {
            Date date = onlineTask.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(date);
            map.put(onlineTask.getDate(), TaskCompleteCountForEveryDayVo.builder()
                    .date(format)
                    .onlineTaskCompleteCount(0)
                    .taskCompleteCount(0)
                    .build());
        }
        for (Task task : tasks) {
            Date date = task.getStartTime();
            do {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(date);
                map.put(date, TaskCompleteCountForEveryDayVo.builder()
                        .date(format)
                        .onlineTaskCompleteCount(0)
                        .taskCompleteCount(0)
                        .build());
                date = new Date(date.getTime() + 24 * 60 * 60 * 1000);
            } while (date.before(task.getEndTime()));
        }
        List<TaskCompleteRecord> taskCompleteRecords = taskCompleteRecordMapper.selectList(new LambdaQueryWrapper<TaskCompleteRecord>().eq(TaskCompleteRecord::getSchoolCode, schoolId));
        for (TaskCompleteRecord taskCompleteRecord : taskCompleteRecords) {
            Date date = taskCompleteRecord.getCompleteTime();
            LocalDate from = LocalDate.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            Date dateByDay = Date.from(from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            TaskCompleteCountForEveryDayVo taskCompleteCountForEveryDayVo = map.get(dateByDay);
            if(taskCompleteCountForEveryDayVo == null) {
                map.put(dateByDay, TaskCompleteCountForEveryDayVo.builder()
                        .date(new SimpleDateFormat("yyyy-MM-dd").format(dateByDay))
                        .onlineTaskCompleteCount(0)
                        .taskCompleteCount(0)
                        .build());
                taskCompleteCountForEveryDayVo = map.get(dateByDay);
            }
            if (taskCompleteRecord.getOnlineTaskId() != null) {
                taskCompleteCountForEveryDayVo.setOnlineTaskCompleteCount(taskCompleteCountForEveryDayVo.getOnlineTaskCompleteCount() + 1);
            } else {
                taskCompleteCountForEveryDayVo.setTaskCompleteCount(taskCompleteCountForEveryDayVo.getTaskCompleteCount() + 1);
            }
        }
        return map.values().stream().sorted(Comparator.comparing(TaskCompleteCountForEveryDayVo::getDate)).collect(Collectors.toList());
    }

    private Long getTaskCountBySchoolId(String schoolId) {
        LambdaQueryWrapper<Task> taskLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskLambdaQueryWrapper.eq(Task::getSchoolCode, schoolId);
        return taskMapper.selectCount(taskLambdaQueryWrapper);
    }

    private Long getOnlineTaskCountBySchoolId(String schoolId) {
        LambdaQueryWrapper<OnlineTask> onlineTaskLambdaQueryWrapper = new LambdaQueryWrapper<>();
        onlineTaskLambdaQueryWrapper.eq(OnlineTask::getSchoolCode, schoolId);
        return onlineTaskMapper.selectCount(onlineTaskLambdaQueryWrapper);
    }

    private Long getCompleteCountBySchoolId(String schoolId) {
        LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getSchoolCode, schoolId);
        return taskCompleteRecordMapper.selectCount(taskCompleteRecordLambdaQueryWrapper);
    }
}
