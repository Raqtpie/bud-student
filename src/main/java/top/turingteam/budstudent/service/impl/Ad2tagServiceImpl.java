package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.mapper.Ad2tagMapper;
import top.turingteam.budstudent.pojo.entity.Ad2tag;
import top.turingteam.budstudent.service.Ad2tagService;
import top.turingteam.budstudent.service.TagService;

import java.util.List;

/**
 * @author howe
 */
@RequiredArgsConstructor
@Service
public class Ad2tagServiceImpl extends ServiceImpl<Ad2tagMapper, Ad2tag>
        implements Ad2tagService {

    private final Ad2tagMapper ad2tagMapper;

    private final TagService tagService;

    @Override
    public void tagsCountAddOne(Integer adId) {
        LambdaQueryWrapper<Ad2tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ad2tag::getAdId, adId);
        List<Integer> list =
                ad2tagMapper.selectList(queryWrapper).stream().map(Ad2tag::getTagId).toList();
        if (list.isEmpty()) {
            return;
        }
        for (Integer integer : list) {
            tagService.tagSearchCountAddOne(integer);
        }
    }
}




