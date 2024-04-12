package top.turingteam.budstudent.mapping;

import org.mapstruct.Mapper;
import top.turingteam.budstudent.pojo.dto.MerchantUserDto;
import top.turingteam.budstudent.pojo.entity.MerchantUser;
import top.turingteam.budstudent.pojo.vo.MerchantRetrieveVo;
import top.turingteam.budstudent.pojo.vo.MerchantUserVo;

/**
 * vo entity dto转换
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface MerchantUserMapping {
    MerchantUser ToMerchantUser(MerchantUserVo merchantUserVo);
    MerchantUser merchantRetrieveVoToMerchantUser(MerchantRetrieveVo merchantRetrieveVo);

    MerchantUserDto ToMerchantUserDto(MerchantUser merchantUser);
}