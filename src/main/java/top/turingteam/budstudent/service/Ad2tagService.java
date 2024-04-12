package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.entity.Ad2tag;

/**
* @author howe
*/
public interface Ad2tagService extends IService<Ad2tag> {
    void tagsCountAddOne(Integer adId);
}
