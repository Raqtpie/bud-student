package top.turingteam.budstudent.middleware.handler;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import top.turingteam.budstudent.mapper.StudentLoginRecordMapper;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.entity.TagEntity;
import top.turingteam.budstudent.pojo.vo.*;
import top.turingteam.budstudent.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Raqtpie
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataScreenWebSocketHandler extends TextWebSocketHandler {
    private final AdminUserService adminUserService;

    private final StudentInfoService studentInfoService;

    private final TaskCompleteRecordService taskCompleteRecordService;

    private final TagService tagService;

    private final GiftService giftService;

    private final StudentLoginRecordMapper studentLoginRecordMapper;

    private final SchoolService schoolService;

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) throws IOException {
        log.info("新的连接: {}", session);
        Long userId = (Long) session.getAttributes().get("id");
        if (userId == null) {
            session.sendMessage(new TextMessage("未登录"));
            session.close(CloseStatus.NOT_ACCEPTABLE);
            return;
        }
        sessions.add(session);
//        sendMsg(session);
    }

    /**
     * 定时发送消息
     * 每隔10秒发送一次消息
     */
    @Scheduled(fixedRate = 10000)
    public void sendPeriodicMessage() throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                sendMsg(session);
            }
        }
    }

    private void sendMsg(WebSocketSession session) throws IOException {
        Long userId = (Long) session.getAttributes().get("id");
        AdminUser user = adminUserService.getById(userId);
        String school = user.getSchool();
        boolean role = user.isRole();
        if (school != null && !role) {  // 学校管理员
            List<TaskCompleteRateByCollegeVo> taskCompleteRateByCollegeVos = taskCompleteRecordService.getCompleteDataBySchoolId(school);
            List<TaskCompleteCountForEveryDayVo> completeCountForEveryDay = taskCompleteRecordService.getCompleteCountForEveryDay(school);
            List<StudentActiveCountByProvinceVo> studentActiveCountByProvinceVos = studentInfoService.getActiveCountForEveryProvinceBySchoolCode(school);
            Map<String, Double> studentActiveCountBySchoolCode = studentInfoService.getStudentActiveCountBySchoolCode(school);
            List<SchoolPointsRedeemPopularGiftsVo> schoolPointsRedeemPopularGifts = giftService.getSchoolPointsRedeemPopularGifts(school);
            Map<String, Object> data = Map.of("taskCompleteRateByCollegeVos", taskCompleteRateByCollegeVos,
                    "completeCountForEveryDay", completeCountForEveryDay,
                    "studentActiveCountByProvinceVos", studentActiveCountByProvinceVos,
                    "studentActiveCountBySchoolCode", studentActiveCountBySchoolCode,
                    "schoolPointsRedeemPopularGifts", schoolPointsRedeemPopularGifts);
            session.sendMessage(new TextMessage(JSONUtil.toJsonStr(data)));
        } else {
            List<TaskCompleteRateBySchoolVo> taskCompleteRateBySchoolVos = taskCompleteRecordService.getCompleteData();
            List<StudentActiveCountByProvinceVo> studentActiveCountByProvinceVos = studentInfoService.getActiveCountForEveryProvince();
            StudentActivityCountVo[] studentActivityCountVos = studentLoginRecordMapper.selectStudentActivityCount();
            List<SchoolRegionDistributionVo> schoolRegionDistribution = schoolService.getSchoolRegionDistribution();
            List<TagEntity> tagEntities = tagService.listAllTag();
            Map<String, Object> data = Map.of("taskCompleteRateBySchoolVos", taskCompleteRateBySchoolVos,
                    "studentActiveCountByProvinceVos", studentActiveCountByProvinceVos,
                    "tagEntities", tagEntities,
                    "studentActivityCountVos", studentActivityCountVos,
                    "schoolRegionDistribution", schoolRegionDistribution);
            session.sendMessage(new TextMessage(JSONUtil.toJsonStr(data)));
        }
    }

    @Override
    public void afterConnectionClosed(@NotNull WebSocketSession session, @NotNull CloseStatus status) throws Exception {
        sessions.remove(session);
        session.close(CloseStatus.NORMAL);
    }

}
