package top.turingteam.budstudent.mapping;

import org.mapstruct.Mapper;
import top.turingteam.budstudent.pojo.dto.AdvertisementDto;
import top.turingteam.budstudent.pojo.dto.AdvertisementPushDto;
import top.turingteam.budstudent.pojo.entity.Advertisement;
import top.turingteam.budstudent.pojo.vo.AdvertisementAddVo;

import java.util.List;

/**
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface AdvertisementMapping {
    Advertisement toAdvertisement(AdvertisementAddVo advertisementAddVo);

    List<AdvertisementDto> toAdvertisementDto(List<Advertisement> advertisements);

    AdvertisementDto toAdvertisementDto(Advertisement advertisements);

    AdvertisementPushDto toAdvertisementPush(Advertisement advertisement);
    List<AdvertisementPushDto> AdListToAdPushList(List<Advertisement> advertisements);
}