package top.turingteam.budstudent.service;

import top.turingteam.budstudent.pojo.entity.TagEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.entity.TagLevel;

import java.util.List;

/**
* @author howe
*/
public interface TagService extends IService<TagEntity> {
    /**
     * 更新标签等级
     * @param tagLevel 标签等级
     */
    void updateTagLevel(TagLevel tagLevel);

    /**
     * 获取所有标签等级
     * @return 标签等级列表
     */
    List<TagLevel> listAllTagLevel();

    /**
     * 标签搜索次数加一
     * @param id 标签id
     */
    void tagSearchCountAddOne(Integer id);

    /**
     * 获取标签搜索次数
     * @param id 标签id
     * @return 搜索次数
     */
    Integer getTagSearchCount(Integer id);

    /**
     * 通过标签id获取价格
     * @param id 标签id
     * @return 价格
     */
    Integer getPriceByTagId(Integer id);

    /**
     * 获取所有标签
     * @return 标签列表
     */
    List<TagEntity> listAllTag();
}
