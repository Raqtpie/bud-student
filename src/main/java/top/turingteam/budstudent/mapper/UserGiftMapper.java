package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.turingteam.budstudent.pojo.dto.GiftListDto;
import top.turingteam.budstudent.pojo.entity.UserGift;

/**
* @author howe
*/
@Mapper
public interface UserGiftMapper extends BaseMapper<UserGift> {
    GiftListDto[] selectUserGiftByUserId(Long studentId);

    UserGift[] getUserGiftBySchoolCode(Long studentId);

    Integer exchangeGiftCount(Integer giftId);
}




