package top.turingteam.budstudent.middleware.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.turingteam.budstudent.pojo.entity.TagEntity;
import top.turingteam.budstudent.service.AdvertisementService;
import top.turingteam.budstudent.service.TagService;

import java.util.List;

/**
 * @author Raqtpie
 */
@RequiredArgsConstructor
@Component
public class ScheduledTasksHandler {
    private final TagService tagService;

    private final AdvertisementService advertisementService;

    /**
     * 每半小时执行: 更新标签搜索次数到数据库
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateTagSearchCountInMysql() {
        List<TagEntity> list = tagService.list();
        for (TagEntity tagEntity : list) {
            Integer tagSearchCount = tagService.getTagSearchCount(tagEntity.getId());
            tagEntity.setSearchCount(tagSearchCount);
            tagService.updateById(tagEntity);
        }
    }

    /**
     * 每十分钟更新任务的状态 发布中->已过期
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateAdStatus() {
        advertisementService.updateAuditState();
    }
}
