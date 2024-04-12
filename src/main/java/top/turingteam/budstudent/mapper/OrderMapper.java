package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.turingteam.budstudent.pojo.entity.Order;

/**
 * @author howe
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 查询广告对应标签的总价格
     *
     * @param adId 广告id
     * @return 标签对应金额
     */
    @Select("SELECT SUM(tl.price) AS total_amount FROM turing.tb_advertisement AS ad JOIN turing.tb_ad2tag AS a2t ON ad.id = a2t.ad_id JOIN turing.tb_tag_level AS tl ON a2t.tag_id = tl.level WHERE ad.id = #{adId};")
    Integer selectTagTotal(Integer adId);

//    /**
//     * 查询用户的所有订单信息
//     */
//    @Select("SELECT o.order_id, o.user_id, o.ad_id, o.amount, o.pay_status, o.create_time, o.complete_time " +
//            "FROM turing.tb_order o " +
//            "INNER JOIN turing.tb_advertisement a ON o.ad_id = a.id " +
//            "WHERE a.publish_user_id = #{userId}")
//    List<Order> getOrdersByUserId(Long userId);
}