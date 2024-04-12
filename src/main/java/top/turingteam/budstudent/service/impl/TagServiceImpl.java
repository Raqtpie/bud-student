package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.TagLevelMapper;
import top.turingteam.budstudent.mapper.TagMapper;
import top.turingteam.budstudent.pojo.entity.TagEntity;
import top.turingteam.budstudent.pojo.entity.TagLevel;
import top.turingteam.budstudent.service.TagService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author howe
 */
@RequiredArgsConstructor
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity> implements TagService {
    private final TagMapper tagMapper;

    private final TagLevelMapper tagLevelMapper;

    private final StringRedisTemplate stringRedisTemplate;

    private final Map<String, Object> locks = new ConcurrentHashMap<>();


    @Override
    public void updateTagLevel(TagLevel tagLevel) {
        LambdaQueryWrapper<TagLevel> tagLevelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tagLevelLambdaQueryWrapper.eq(TagLevel::getLevel, tagLevel.getLevel());
        if (tagLevelMapper.selectOne(tagLevelLambdaQueryWrapper) == null) {
            throw new CustomException("等级不存在");
        }
        tagLevelMapper
                .update(tagLevel, new LambdaQueryWrapper<TagLevel>().eq(TagLevel::getLevel, tagLevel.getLevel()));
    }

    @Override
    public List<TagLevel> listAllTagLevel() {
        return tagLevelMapper.selectList(null);
    }

    @Override
    public void tagSearchCountAddOne(Integer id) {
        String prefix = "tagSearchCount:";
        String key = prefix + id;
        if (stringRedisTemplate.opsForValue().get(key) == null && locks.get(key) == null) {
            synchronized (locks) {
                if (locks.get(key) == null) {
                    locks.compute(key, (k, v) -> new Object());
                    stringRedisTemplate.opsForValue().set(key, "0");
                }
            }
        }
        stringRedisTemplate.opsForValue().increment(key, 1);
    }

    @Override
    public Integer getTagSearchCount(Integer id) {
        String prefix = "tagSearchCount:";
        String key = prefix + id;
        String countStr = stringRedisTemplate.opsForValue().get(key);
        int count;
        if (countStr == null) {
            count = tagMapper.selectById(id).getSearchCount();
        } else {
            count = Integer.parseInt(countStr);
        }
        return count;
    }

    @Override
    public Integer getPriceByTagId(Integer id) {
        TagEntity tagEntity = tagMapper.selectById(id);
        return tagLevelMapper.selectById(tagEntity.getLevel()).getPrice();
    }

    @Override
    public List<TagEntity> listAllTag() {
        List<TagEntity> tagEntities = tagMapper.selectList(null);
        for (TagEntity tagEntity : tagEntities) {
            Integer tagSearchCount = getTagSearchCount(tagEntity.getId());
            tagEntity.setSearchCount(tagSearchCount);
        }
        return tagEntities;
    }

}
