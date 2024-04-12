package top.turingteam.budstudent.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.FileContentType;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.*;
import top.turingteam.budstudent.pojo.entity.*;
import top.turingteam.budstudent.pojo.vo.LowTaskCompleteRateStudentVo;
import top.turingteam.budstudent.service.OnlineTaskService;
import top.turingteam.budstudent.support.CvHelper;
import top.turingteam.budstudent.support.MinioHelper;
import top.turingteam.budstudent.support.RedisLockProvider;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Raqtpie
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OnlineTaskServiceImpl extends ServiceImpl<OnlineTaskMapper, OnlineTask> implements OnlineTaskService {
    private final MinioHelper minioHelper;

    private final OnlineTaskMapper onlineTaskMapper;

    private final TaskCompleteRecordMapper taskCompleteRecordMapper;

    private final TaskObjectMapper taskObjectMapper;

    private final StudentInfoMapper studentInfoMapper;

    private final StudentPointMapper studentPointMapper;

    private final StudentPointRecordMapper studentPointRecordMapper;

    private final CvHelper cvHelper;

    private final RedisLockProvider redisLockProvider;

    @Override
    public OnlineTask getOnlineTaskToday(String schoolCode) {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(today);
        LambdaQueryWrapper<OnlineTask> onlineTaskLambdaQueryWrapper = new LambdaQueryWrapper<>();
        onlineTaskLambdaQueryWrapper.eq(OnlineTask::getDate, formattedDate)
                .eq(OnlineTask::getSchoolCode, schoolCode);
        OnlineTask onlineTask = onlineTaskMapper.selectOne(onlineTaskLambdaQueryWrapper);
        if (onlineTask != null) {
            onlineTask.setCreatorId(null);
        }
        return onlineTask;
    }

    @Override
    public List<OnlineTask> getOnlineTaskNotYetDone(StudentUser studentUser) {
        Long studentId = studentUser.getId();
        String schoolCode = studentInfoMapper.selectById(studentId).getSchoolCode();
        String date = LocalDate.now().getYear() + "-01-01";
        return onlineTaskMapper.getOnlineTaskNotYetDone(schoolCode, studentId, date);
    }

    @Override
    public List<OnlineTask> getTaskBeforeToday(String schoolCode) {
        Date today = new Date();
        LambdaQueryWrapper<OnlineTask> onlineTaskLambdaQueryWrapper = new LambdaQueryWrapper<>();
        onlineTaskLambdaQueryWrapper
                .le(OnlineTask::getDate, today)
                .eq(OnlineTask::getSchoolCode, schoolCode);
        List<OnlineTask> list = onlineTaskMapper.selectList(onlineTaskLambdaQueryWrapper);
        list.forEach(onlineTask -> onlineTask.setCreatorId(null));
        return list;
    }

    @Override
    public void submitOnlineTask(Integer onlineTaskId, StudentUser studentLoginUser, MultipartFile file) {
        LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getOnlineTaskId, onlineTaskId)
                .eq(TaskCompleteRecord::getStudentId, studentLoginUser.getId()).eq(TaskCompleteRecord::getStatus, 1);
        if (taskCompleteRecordMapper.selectOne(taskCompleteRecordLambdaQueryWrapper) != null) {
            throw new CustomException("任务已提交");
        }
        redisLockProvider.tryLock("submitOnlineTask:" + onlineTaskId + ":" + studentLoginUser.getId(), Thread.currentThread().getName(), 1000L);
        OnlineTask onlineTask = onlineTaskMapper.selectById(onlineTaskId);
        if (new Date().before(onlineTask.getDate())) {
            throw new CustomException("任务未开始");
        }
        StudentPoint studentPoint = studentPointMapper.selectById(studentLoginUser.getId());
        try {
            if (file == null) {
                TaskCompleteRecord taskCompleteRecord = TaskCompleteRecord.builder()
                        .studentId(studentLoginUser.getId())
                        .onlineTaskId(onlineTaskId)
                        .completeTime(new Date())
                        .status(1)
                        .build();
                taskCompleteRecordMapper.insert(taskCompleteRecord);
                updatePointAndPointRecord(studentLoginUser.getId(), studentPoint, onlineTask);
                return;
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
                throw new CustomException("文件格式错误");
            }
            String url = minioHelper.uploadFile(file, "submitOnlineTask-" + UUID.randomUUID() + "." + contentType.split("/")[1]);
            String result = cvHelper.getResultFromObjectCv(url);
            TaskCompleteRecord taskCompleteRecord = TaskCompleteRecord.builder()
                    .studentId(studentLoginUser.getId())
                    .schoolCode(onlineTask.getSchoolCode())
                    .onlineTaskId(onlineTaskId)
                    .imgUrl(url)
                    .completeTime(new Date())
                    .status(1)
                    .build();
            TaskObject taskObject = taskObjectMapper.selectById(onlineTask.getObjId());
            LambdaQueryWrapper<TaskCompleteRecord> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TaskCompleteRecord::getOnlineTaskId, onlineTaskId)
                    .eq(TaskCompleteRecord::getStudentId, studentLoginUser.getId());
            TaskCompleteRecord record = taskCompleteRecordMapper.selectOne(queryWrapper);
            if (result == null || result.isBlank() || !result.contains(taskObject.getObjectName())) {
                taskCompleteRecord.setStatus(2);
                if (record == null) {
                    taskCompleteRecordMapper.insert(taskCompleteRecord);
                } else {
                    taskCompleteRecord.setId(record.getId());
                    taskCompleteRecord.setCompleteTime(new Date());
                    taskCompleteRecordMapper.update(taskCompleteRecord, queryWrapper);
                }
                throw new CustomException("识别失败");
            } else {
                if (record == null) {
                    taskCompleteRecordMapper.insert(taskCompleteRecord);
                } else {
                    taskCompleteRecordMapper.update(taskCompleteRecord, queryWrapper);
                }
                updatePointAndPointRecord(studentLoginUser.getId(), studentPoint, onlineTask);
            }
        } finally {
            redisLockProvider.releaseLock("submitOnlineTask" + onlineTaskId + ":" + studentLoginUser.getId(), Thread.currentThread().getName());
        }
    }

    private void updatePointAndPointRecord(Long studentId, StudentPoint studentPoint, OnlineTask task) {
        Date currentDate = new Date();
        Date taskDate = task.getDate();
        boolean isSameDay = isSameDay(currentDate, taskDate);
        int award = task.getAward();
        if (!isSameDay) {
            award /= 2;
        }
        studentPoint.setPointsNow(studentPoint.getPointsNow() + award);
        studentPoint.setPointsTotal(studentPoint.getPointsTotal() + award);
        studentPointMapper.updateById(studentPoint);
        String reason = isSameDay ? "完成任务:" + task.getTopic() : "未在当天完成任务:" + task.getTopic();
        StudentPointRecord studentPointRecord = StudentPointRecord.builder().studentId(studentId).reason(reason).point(award).build();
        studentPointRecordMapper.insert(studentPointRecord);
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void addOnlineTask(OnlineTask onlineTask, AdminUser user) {
        onlineTask.setCreatorId(user.getId());
        onlineTask.setSchoolCode(user.getSchool());
        if (onlineTaskMapper.insert(onlineTask) == 0) {
            throw new CustomException("添加失败");
        }
    }

    @Override
    public void deleteOnlineTask(Integer taskId, AdminUser user) {
        LambdaQueryWrapper<OnlineTask> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OnlineTask::getId, taskId)
                .eq(OnlineTask::getSchoolCode, user.getSchool());
        if (onlineTaskMapper.delete(queryWrapper) == 0) {
            throw new CustomException("删除失败");
        }
    }

    @Override
    public List<LowTaskCompleteRateStudentVo> getLowerCompleteRateStudentList(String schoolCode) {
        List<OnlineTask> onlineTasks = onlineTaskMapper.selectList(null);
        if (onlineTasks.isEmpty()) {
            throw new CustomException("还没有添加任务，无法查看任务完成率");
        }
        LowTaskCompleteRateStudentVo[] lowTaskCompleteRateStudentVos = onlineTaskMapper.getLowerCompleteRateStudentList(schoolCode);
        return List.of(lowTaskCompleteRateStudentVos);
    }

    @Override
    public String getLowerCompleteRateStudentListXlsx(String schoolCode) {
        List<LowTaskCompleteRateStudentVo> list = getLowerCompleteRateStudentList(schoolCode);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ExcelWriter excelWriter = EasyExcel.write(outputStream, LowTaskCompleteRateStudentVo.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();
            excelWriter.write(list, writeSheet);
            excelWriter.finish();

            // 将 outputStream 中的数据上传到 MinIO 中
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return minioHelper.uploadFile(inputStream, schoolCode + "_onlineTaskLowerRateList.xlsx", FileContentType.XLSX_TYPE);
        } catch (Exception e) {
            throw new CustomException("导出失败");
        }
    }

    @Override
    public Boolean getDoneStatus(Integer taskId, Long studentId) {
        LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getOnlineTaskId, taskId)
                .eq(TaskCompleteRecord::getStudentId, studentId).eq(TaskCompleteRecord::getStatus, 1);
        return taskCompleteRecordMapper.selectOne(taskCompleteRecordLambdaQueryWrapper) != null;
    }
}
