package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.turingteam.budstudent.pojo.entity.Ad2tag;
import top.turingteam.budstudent.pojo.entity.Advertisement;

import java.util.List;

/**
 * @author howe
 */
@Mapper
public interface Ad2tagMapper extends BaseMapper<Ad2tag> {
    /**
     * 查询广告对应标签
     * @param adId 广告id
     * @return 标签名
     */
    @Select("select a.topic from turing.tb_tag as a where a.id in (select b.tag_id from turing.tb_ad2tag as b where b.ad_id = #{adId});")
    List<String> selectByAdId(Integer adId);

    /**
     * 查询标签对应广告
     */
    @Select("select * from turing.tb_advertisement where id in (select ad_id from turing.tb_ad2tag where tag_id = #{tagId});")
    List<Advertisement> selectAdByTagId(Integer tagId);
}