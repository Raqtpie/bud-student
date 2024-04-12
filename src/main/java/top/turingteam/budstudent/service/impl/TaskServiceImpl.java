package top.turingteam.budstudent.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.FileContentType;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.*;
import top.turingteam.budstudent.pojo.bo.WeChatGetAccessTokenResponse;
import top.turingteam.budstudent.pojo.bo.WeChatMessageData;
import top.turingteam.budstudent.pojo.bo.WeChatMessageRequest;
import top.turingteam.budstudent.pojo.bo.WeChatResponse;
import top.turingteam.budstudent.pojo.dto.TaskDto;
import top.turingteam.budstudent.pojo.entity.*;
import top.turingteam.budstudent.pojo.vo.LowTaskCompleteRateStudentVo;
import top.turingteam.budstudent.pojo.vo.TaskCountAndRateVo;
import top.turingteam.budstudent.service.TaskService;
import top.turingteam.budstudent.support.CvHelper;
import top.turingteam.budstudent.support.MinioHelper;
import top.turingteam.budstudent.support.RedisLockProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Raqtpie
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    private final TaskMapper taskMapper;

    private final TaskObjectMapper taskObjectMapper;

    private final TaskCompleteRecordMapper taskCompleteRecordMapper;

    private final StudentUserMapper studentUserMapper;

    private final StudentInfoMapper studentInfoMapper;

    private final StudentPointMapper studentPointMapper;

    private final StudentPointRecordMapper studentPointRecordMapper;

    private final MinioHelper minioHelper;

    private final CvHelper cvHelper;

    private final RedisLockProvider redisLockProvider;

    private final RestTemplate restTemplate;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.template_id}")
    private String templateId;

    @Value("${wx.page}")
    private String page;

    @Value("${wx.miniprogram_state}")
    private String miniProgramState;

    @Value("${wx.lang}")
    private String lang;

    private static final Integer NOT_SUBSCRIBED = 43101;

    private static final Integer SUBSCRIPTION_DISABLED = 43107;

    @Override
    public void addTask(Task task, AdminUser user) {
        if (task.getParentTaskId() != null) {
            Task parentTask = taskMapper.selectById(task.getParentTaskId());
            if (parentTask == null) {
                throw new CustomException("主线任务不存在");
            } else if (!parentTask.getSchoolCode().equals(user.getSchool())) {
                throw new CustomException("学校不一致");
            }
        }
        if (task.getPreTaskId() != null) {
            Task preTask = taskMapper.selectById(task.getPreTaskId());
            if (preTask == null) {
                throw new CustomException("前置任务不存在");
            } else if (!preTask.getSchoolCode().equals(user.getSchool())) {
                throw new CustomException("学校不一致");
            }
        }
        Integer count = taskMapper.getTaskMaxNumberBySchoolCode(user.getSchool());
        task.setTaskSubId(count + 1);
        task.setCreatorId(user.getId());
        task.setSchoolCode(user.getSchool());
        taskMapper.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    @Override
    public void deleteTask(Integer id) {
        taskMapper.deleteById(id);
    }

    @Override
    public List<TaskDto> getTask(Long id) {
        StudentInfo studentInfo = studentInfoMapper.selectById(id);
        String date = LocalDate.now().getYear() + "-01-01";
        List<Task> tasks = taskMapper.getTaskByStudentIdAndSchoolCode(studentInfo.getSchoolCode(), id, date);
        List<TaskDto> taskList = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(task, taskDto);
            taskDto.setPreTaskIsDone(true);
            if (task.getParentTaskId() == null) {
                taskList.add(taskDto);
                continue;
            }
            for (Task t : tasks) {
                if (task.getPreTaskId() != null && task.getPreTaskId().equals(t.getId())) {
                    taskDto.setPreTaskIsDone(false);
                }
            }
            taskList.add(taskDto);
        }
        return taskList;
    }

    @Override
    public List<Task> getAllTask(String schoolCode) {
        return taskMapper.getAllTask(schoolCode);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitTask(Integer taskId, MultipartFile file, Long studentId) {
        Task task = taskMapper.selectById(taskId);
        checkAll(task, studentId);
        redisLockProvider.tryLock("submitTask:" + taskId + ":" + studentId, Thread.currentThread().getName(), 1000L);
        try {
            TaskCompleteRecord taskCompleteRecord = createRecord(taskId, studentId);
            taskCompleteRecord.setSchoolCode(taskMapper.selectById(taskId).getSchoolCode());
            StudentPoint studentPoint = getStudentPoint(studentId);
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
                throw new CustomException("文件格式错误");
            }
            String url = minioHelper.uploadFile(file, "submitTask-" + UUID.randomUUID() + "." + contentType.split("/")[1]);
            String result = cvHelper.getResultFromObjectCv(url);
            if (result == null || result.isBlank()) {
                throw new CustomException("识别失败");
            }
            TaskObject taskObject = taskObjectMapper.selectById(task.getObjId());
            LambdaQueryWrapper<TaskCompleteRecord> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TaskCompleteRecord::getTaskId, taskId).eq(TaskCompleteRecord::getStudentId, studentId);
            TaskCompleteRecord record = taskCompleteRecordMapper.selectOne(queryWrapper);
            if (result.contains(taskObject.getObjectName()) || result.contains(taskObject.getObjectNameZh())) {
                taskCompleteRecord.setImgUrl(url);
                taskCompleteRecord.setStatus(1);
                if (record == null) {
                    taskCompleteRecordMapper.insert(taskCompleteRecord);
                } else {
                    taskCompleteRecord.setId(record.getId());
                    taskCompleteRecord.setCompleteTime(new Date());
                    taskCompleteRecordMapper.update(taskCompleteRecord, queryWrapper);
                }
                updatePointAndPointRecord(studentId, studentPoint, task);
            } else {
                taskCompleteRecord.setStatus(2);
                taskCompleteRecordMapper.insert(taskCompleteRecord);
                throw new CustomException("照片物品不为指定物品");
            }
        } finally {
            redisLockProvider.releaseLock("submitTask:" + taskId + ":" + studentId, Thread.currentThread().getName());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitTask(Integer taskId, String answer, Long studentId) {
        Task task = taskMapper.selectById(taskId);
        checkAll(task, studentId);
        redisLockProvider.tryLock("submitTask:" + taskId + ":" + studentId, Thread.currentThread().getName(), 1000L);
        try {
            TaskCompleteRecord record = createRecord(taskId, studentId);
            record.setSchoolCode(task.getSchoolCode());
            if (answer.equals(task.getAnswer())) {
                record.setStatus(1);
                taskCompleteRecordMapper.insert(record);
                StudentPoint studentPoint = getStudentPoint(studentId);
                updatePointAndPointRecord(studentId, studentPoint, task);
            } else {
                throw new CustomException("答案错误");
            }
        } finally {
            redisLockProvider.releaseLock("submitTask:" + taskId + ":" + studentId, Thread.currentThread().getName());
        }
    }

    @Override
    public void submitTask(Integer taskId, Long studentId) {
        Task task = taskMapper.selectById(taskId);
        checkAll(task, studentId);
        LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getTaskId, taskId)
                .eq(TaskCompleteRecord::getStudentId, studentId).eq(TaskCompleteRecord::getStatus, 3);
        if (taskCompleteRecordMapper.selectOne(taskCompleteRecordLambdaQueryWrapper) != null) {
            throw new CustomException("已经识别过该人脸");
        }
        redisLockProvider.tryLock("submitTask:" + taskId + ":" + studentId, Thread.currentThread().getName(), 1000L);
        try {
            TaskCompleteRecord taskCompleteRecord = createRecord(taskId, studentId);
            taskCompleteRecord.setSchoolCode(taskMapper.selectById(taskId).getSchoolCode());
            taskCompleteRecord.setStatus(3);
            taskCompleteRecordMapper.insertRecord(taskCompleteRecord);
        } finally {
            redisLockProvider.releaseLock("submitTask:" + taskId + ":" + studentId, Thread.currentThread().getName());
        }
        // todo 测试消息发送是否可用
        sendMsg(studentId);
    }

    @Override
    public void submitTask(Integer taskId, Point point, Long studentId) {
        Task task = taskMapper.selectById(taskId);
        if (task.getLocation() == null || task.getRadius() == null) {
            throw new CustomException("任务未设置地理位置");
        }
        checkAll(task, studentId);
        redisLockProvider.tryLock("submitTask:" + taskId + ":" + studentId, Thread.currentThread().getName(), 1000L);
        try {
            LambdaQueryWrapper<TaskCompleteRecord> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(TaskCompleteRecord::getTaskId, taskId).eq(TaskCompleteRecord::getStudentId, studentId).eq(TaskCompleteRecord::getStatus, 3);
            TaskCompleteRecord taskCompleteRecord = taskCompleteRecordMapper.selectOne(wrapper);
            if (taskCompleteRecord == null) {
                throw new CustomException("请先完成实地人脸识别");
            }
            StudentPoint studentPoint = getStudentPoint(studentId);
            taskCompleteRecord.setLocation(point);
            if (computeLocation(task.getLocation(), point, task.getRadius())) {
                taskCompleteRecord.setStatus(1);
                taskCompleteRecordMapper.updateRecord(taskCompleteRecord);
                updatePointAndPointRecord(studentId, studentPoint, task);
            } else {
                throw new CustomException("地理位置不正确");
            }
        } finally {
            redisLockProvider.releaseLock("submitTask:" + taskId + ":" + studentId, Thread.currentThread().getName());
        }
    }

    private void sendMsg(Long studentId) {
        String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        String responseStr = restTemplate.getForObject(getAccessTokenUrl, String.class);
        if (responseStr == null) {
            log.error("获取access_token失败, responseStr为空");
        }
        WeChatGetAccessTokenResponse response = JSONUtil.parseObj(responseStr).toBean(WeChatGetAccessTokenResponse.class);
        String accessToken = null;
        if (response != null) {
            accessToken = response.getAccess_token();
        }
        String sendMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken;
        LocalDateTime now = LocalDateTime.now();
        String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String dateTime = now.format(formatter);
        HashMap<String, String> thing5 = new HashMap<>(1);
        thing5.put("value", "实地人脸识别成功，请点击完成任务！");
        HashMap<String, String> date2 = new HashMap<>(1);
        date2.put("value", dateTime);
        String wxId = studentUserMapper.selectById(studentId).getWxId();
        WeChatMessageRequest weChatMessageRequestDto = new WeChatMessageRequest(templateId, page, wxId, miniProgramState, lang, new WeChatMessageData(thing5, date2));
        String requestBody = JSONUtil.toJsonStr(weChatMessageRequestDto);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody);
        ResponseEntity<String> sendMsgResponse = restTemplate.exchange(sendMsgUrl, HttpMethod.POST, requestEntity, String.class);
        String body = sendMsgResponse.getBody();
        WeChatResponse weChatResponse = JSONUtil.parseObj(body).toBean(WeChatResponse.class);
        if (weChatResponse == null) {
            log.error("发送订阅消息失败, userId: {}, 日期: {}", wxId, LocalDate.now());
        } else if (weChatResponse.getErrcode() == NOT_SUBSCRIBED || weChatResponse.getErrcode() == SUBSCRIPTION_DISABLED) {
            log.info("发送订阅消息失败, userId: {}, 日期: {}", wxId, LocalDate.now());
        } else if (weChatResponse.getErrcode() != 0) {
            log.error("发送订阅消息失败, userId: {}, errcode: {}, errmsg: {}, 日期: {}", wxId, weChatResponse.getErrcode(), weChatResponse.getErrmsg(), LocalDate.now());
        }
    }

    private void checkAll(Task task, Long studentId) {
        checkCompleteStatus(task, studentId);
        if (checkDate(task)) {
            throw new CustomException("任务未开始");
        }
        checkPreTaskAndParentTaskIsDone(task, studentId);
    }

    private void checkPreTaskAndParentTaskIsDone(Task task, Long studentId) {
        if (task.getPreTaskId() != null) {
            LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
            taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getTaskId, task.getPreTaskId())
                    .eq(TaskCompleteRecord::getStudentId, studentId).eq(TaskCompleteRecord::getStatus, 1);
            if (taskCompleteRecordMapper.selectOne(taskCompleteRecordLambdaQueryWrapper) != null) {
                throw new CustomException("前置任务未完成");
            }
        }
        if (task.getParentTaskId() != null) {
            LambdaQueryWrapper<Task> taskLambdaQueryWrapper = new LambdaQueryWrapper<>();
            taskLambdaQueryWrapper.eq(Task::getId, task.getParentTaskId());
            Task parentTask = taskMapper.selectOne(taskLambdaQueryWrapper);
            LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
            taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getTaskId, parentTask.getId())
                    .eq(TaskCompleteRecord::getStudentId, studentId).eq(TaskCompleteRecord::getStatus, 1);
            if (taskCompleteRecordMapper.selectOne(taskCompleteRecordLambdaQueryWrapper) != null) {
                throw new CustomException("主线任务的前置任务未完成");
            }
        }
    }

    private TaskCompleteRecord createRecord(Integer taskId, Long id) {
        LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getTaskId, taskId)
                .eq(TaskCompleteRecord::getStudentId, id).eq(TaskCompleteRecord::getStatus, 1);
        if (taskCompleteRecordMapper.selectOne(taskCompleteRecordLambdaQueryWrapper) != null) {
            throw new CustomException("任务已提交");
        }
        return TaskCompleteRecord.builder().taskId(taskId).studentId(id).completeTime(new Date()).status(0).build();
    }

    private StudentPoint getStudentPoint(Long id) {
        LambdaQueryWrapper<StudentPoint> studentPointLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentPointLambdaQueryWrapper.eq(StudentPoint::getId, id);
        return studentPointMapper.selectOne(studentPointLambdaQueryWrapper);
    }

    private void updatePointAndPointRecord(Long studentId, StudentPoint studentPoint, Task task) {
        Date currentTime = new Date();
        Date startTime = task.getStartTime();
        Date endTime = task.getEndTime();
        boolean withinTimeRange = currentTime.after(startTime) && currentTime.before(endTime);
        int pointAward = task.getPointAward();
        if (withinTimeRange) {
            studentPoint.setPointsNow(studentPoint.getPointsNow() + pointAward);
            studentPoint.setPointsTotal(studentPoint.getPointsTotal() + pointAward);
        } else {
            studentPoint.setPointsNow(studentPoint.getPointsNow() + pointAward / 2);
            studentPoint.setPointsTotal(studentPoint.getPointsTotal() + pointAward / 2);
        }
        studentPointMapper.updateById(studentPoint);
        String reason = withinTimeRange ? "完成任务:" + task.getTopic() : "未在规定时间内完成任务:" + task.getTopic();
        int awardedPoint = withinTimeRange ? pointAward : pointAward / 2;
        StudentPointRecord studentPointRecord = StudentPointRecord.builder().studentId(studentId).reason(reason).point(awardedPoint).build();
        studentPointRecordMapper.insert(studentPointRecord);
    }

    private Boolean checkDate(Task task) {
        Date currentTime = new Date();
        Date startTime = task.getStartTime();
        return currentTime.before(startTime);
    }

    private void checkCompleteStatus(Task task, Long studentId) {
        LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getTaskId, task.getId())
                .eq(TaskCompleteRecord::getStudentId, studentId).eq(TaskCompleteRecord::getStatus, 1);
        if (taskCompleteRecordMapper.selectOne(taskCompleteRecordLambdaQueryWrapper) != null) {
            throw new CustomException("任务已提交");
        }
    }

    private Boolean computeLocation(Point taskLocation, Point subLocation, Integer radius) {
        if (taskLocation == null || subLocation == null || radius == null) {
            return false;
        }
        double distance = calculateDistance(taskLocation, subLocation);
        return distance <= radius;
    }

    private double calculateDistance(Point point1, Point point2) {
        double lat1 = Math.toRadians(point1.getLat());
        double lon1 = Math.toRadians(point1.getLng());
        double lat2 = Math.toRadians(point2.getLat());
        double lon2 = Math.toRadians(point2.getLng());
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double earthRadius = 6371000;
        return earthRadius * c;
    }

    @Override
    public List<LowTaskCompleteRateStudentVo> getLowerCompleteRateStudentList(String schoolCode) {
        List<Task> tasks = taskMapper.selectList(null);
        if (tasks.isEmpty()) {
            throw new CustomException("还没有添加任务，无法查看任务完成率");
        }
        return taskMapper.getLowerCompleteRateStudentList(schoolCode);
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
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return minioHelper.uploadFile(inputStream, schoolCode + "_TaskLowerRateList.xlsx", FileContentType.XLSX_TYPE);
        } catch (Exception e) {
            throw new CustomException("导出失败");
        }
    }

    @Override
    public TaskCountAndRateVo getCountAndRateByStudentId(Long studentId) {
        StudentInfo studentInfo = studentInfoMapper.selectById(studentId);
        LambdaQueryWrapper<Task> taskLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskLambdaQueryWrapper.eq(Task::getSchoolCode, studentInfo.getSchoolCode());
        Long taskCount = taskMapper.selectCount(taskLambdaQueryWrapper);
        LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getStudentId, studentId).isNotNull(TaskCompleteRecord::getTaskId);
        Long taskCompleteCount = taskCompleteRecordMapper.selectCount(taskCompleteRecordLambdaQueryWrapper);
        if (taskCount == 0) {
            return TaskCountAndRateVo.builder().taskRate(0L).taskCount(0L).build();
        }
        return TaskCountAndRateVo.builder().taskRate(taskCompleteCount * 100 / taskCount).taskCount(taskCompleteCount).build();
    }

    @Override
    public Boolean getStatusByLocationTask(Integer taskId, Long id) {
        LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getTaskId, taskId)
                .eq(TaskCompleteRecord::getStudentId, id).eq(TaskCompleteRecord::getStatus, 3);
        return taskCompleteRecordMapper.selectOne(taskCompleteRecordLambdaQueryWrapper) != null;
    }

    @Override
    public Boolean getDoneStatus(Integer taskId, Long studentId) {
        LambdaQueryWrapper<TaskCompleteRecord> taskCompleteRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        taskCompleteRecordLambdaQueryWrapper.eq(TaskCompleteRecord::getTaskId, taskId)
                .eq(TaskCompleteRecord::getStudentId, studentId).eq(TaskCompleteRecord::getStatus, 1);
        return taskCompleteRecordMapper.selectOne(taskCompleteRecordLambdaQueryWrapper) != null;
    }
}
