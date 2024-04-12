package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.pojo.dto.AdvertisementDto;
import top.turingteam.budstudent.pojo.dto.AdvertisementPushDto;
import top.turingteam.budstudent.pojo.entity.Advertisement;
import top.turingteam.budstudent.pojo.vo.AdvertisementAddVo;

import java.util.List;

/**
 * @author howe
 */
public interface AdvertisementService extends IService<Advertisement> {
    /**
     * 审核广告
     *
     * @param id          广告id
     * @param auditStatus 广告审核状态
     */
    void auditAdvertisement(Integer id, boolean auditStatus);

    List<AdvertisementDto> getList();

    AdvertisementDto detail(Integer id);

    void AddAdvertisement(AdvertisementAddVo adVo, MultipartFile coverPhoto, MultipartFile contentPhoto);

    void releaseAd(Integer adId);

    void updateAuditState();

    void deleteAdvertisement(Integer adId);

    List<AdvertisementPushDto> pushAd();

    List<AdvertisementPushDto> adByTagId(Integer tag);

    List<AdvertisementDto> getListByStatus(String status);
}