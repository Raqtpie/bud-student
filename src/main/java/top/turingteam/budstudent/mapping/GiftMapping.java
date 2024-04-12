package top.turingteam.budstudent.mapping;

import org.mapstruct.Mapper;
import top.turingteam.budstudent.pojo.entity.Gift;
import top.turingteam.budstudent.pojo.vo.GiftAddVo;

/**
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface GiftMapping {
    Gift GiftAddVoToGift (GiftAddVo gitAddVo);
}
