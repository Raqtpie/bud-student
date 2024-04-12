package top.turingteam.budstudent.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.AdvertisementStatus;
import top.turingteam.budstudent.common.constant.FileContentType;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.Ad2tagMapper;
import top.turingteam.budstudent.mapper.AdvertisementMapper;
import top.turingteam.budstudent.mapping.AdvertisementMapping;
import top.turingteam.budstudent.pojo.dto.AdvertisementDto;
import top.turingteam.budstudent.pojo.dto.AdvertisementPushDto;
import top.turingteam.budstudent.pojo.entity.*;
import top.turingteam.budstudent.pojo.vo.AdvertisementAddVo;
import top.turingteam.budstudent.service.AdvertisementService;
import top.turingteam.budstudent.service.MerchantUserService;
import top.turingteam.budstudent.service.OrderService;
import top.turingteam.budstudent.service.StudentInfoService;
import top.turingteam.budstudent.support.LoginUserProvider;
import top.turingteam.budstudent.support.MinioHelper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author howe
 */
@RequiredArgsConstructor
@Service
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, Advertisement> implements AdvertisementService {

    private final AdvertisementMapper advertisementMapper;

    private final LoginUserProvider loginUserProvider;

    private final Ad2tagMapper ad2tagMapper;

    private final AdvertisementMapping advertisementMapping;

    private final OrderService orderService;

    private final MerchantUserService merchantUserService;

    private final MinioHelper minioHelper;

    private final StudentInfoService studentInfoService;

    @Override
    public void auditAdvertisement(Integer id, boolean auditStatus) {
        Advertisement ad = advertisementMapper.selectById(id);
        if (ad == null) {
            throw new CustomException("广告id不存在");
        }
        if (!AdvertisementStatus.WAITING_FOR_REVIEW.getStatus().equals(ad.getAuditStatus())) {
            throw new CustomException("广告" + ad.getAuditStatus());
        }
        ad.setAuditId(Long.valueOf((String) StpUtil.getTokenInfo().getLoginId()));
        if (auditStatus) {
            // 通过
            ad.setAuditStatus(AdvertisementStatus.WAITING_FOR_PUBLISH.getStatus());
        } else {
            ad.setAuditStatus(AdvertisementStatus.REVIEW_FAILED.getStatus());
        }
        int i = advertisementMapper.updateById(ad);
        if (i == 0) {
            throw new CustomException("审核失败");
        }
        // 审核不通过直接不用创建订单
        if (!auditStatus) return;
        MerchantUser merchantUser = merchantUserService.getById(ad.getPublishUserId());
        if (merchantUser == null) {
            return;
        }
        orderService.createOrder(ad);
    }

    @Override
    public List<AdvertisementDto> getList() {
        List<Advertisement> advertisements;
        AdminUser adminUser = loginUserProvider.getAdminLoginUser();
        if (adminUser != null && adminUser.isRole()) {
            advertisements = advertisementMapper.selectList(new LambdaQueryWrapper<>());
        } else {
            advertisements = advertisementMapper.selectList(new LambdaQueryWrapper<Advertisement>()
                    .eq(Advertisement::getPublishUserId, StpUtil.getTokenInfo().getLoginId()));
        }
        return setAdDtoTags(advertisementMapping.toAdvertisementDto(advertisements));
    }

    @Override
    public AdvertisementDto detail(Integer id) {
        Advertisement ad = advertisementMapper.selectOne(new LambdaQueryWrapper<Advertisement>().eq(Advertisement::getId, id));
        AdvertisementDto adDto = advertisementMapping.toAdvertisementDto(ad);
        adDto.setTags(ad2tagMapper.selectByAdId(id));
        return adDto;
    }

    @Override
    public void AddAdvertisement(AdvertisementAddVo adVo, MultipartFile coverPhoto, MultipartFile contentPhoto) {
        Advertisement ad = advertisementMapping.toAdvertisement(adVo);
        ad.setCoverUrl(uploadPhoto(coverPhoto));
        ad.setContentUrl(uploadPhoto(contentPhoto));
        ad.setAuditStatus(AdvertisementStatus.WAITING_FOR_REVIEW.getStatus());
        ad.setPublishUserId(Long.valueOf((String) StpUtil.getTokenInfo().getLoginId()));
        int insert = advertisementMapper.insert(ad);
        if (insert == 0) {
            throw new CustomException("广告申请失败");
        }
        // 批量插入标签
        List<Integer> tags = adVo.getTags();
        for (Integer tagId : tags) {
            Ad2tag ad2tag = new Ad2tag(ad.getId(), tagId);
            int i = ad2tagMapper.insert(ad2tag);
            if (i == 0) {
                throw new CustomException("标签有误");
            }
        }
    }

    private String uploadPhoto(MultipartFile photo) {
        if (photo != null) {
            String contentType = photo.getContentType();
            if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
                throw new CustomException("文件类型不合法");
            }
            String filename = UUID.randomUUID() + "." + contentType.split("/")[1];
            return minioHelper.uploadFile(photo, filename);
        }
        return "";
    }

    @Override
    public void releaseAd(Integer adId) {
        MerchantUser merchantLoginUser = loginUserProvider.getMerchantLoginUser();
        if (merchantLoginUser != null) {
            // 商家用户
            Order order = orderService.getOne(new LambdaQueryWrapper<Order>()
                    .eq(Order::getAdId, adId));
            if (order == null) {
                throw new CustomException("订单不存在");
            }
            if (!order.getUserId().equals(merchantLoginUser.getId())) {
                throw new CustomException("订单不属于当前用户");
            }
            if (order.getPayStatus() == 0) {
                throw new CustomException("请先支付对应的订单");
            }
        }
        Long loginUserId = Long.valueOf((String) StpUtil.getTokenInfo().getLoginId());
        LambdaQueryWrapper<Advertisement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Advertisement::getId, adId);
        Advertisement ad = advertisementMapper.selectOne(queryWrapper);
        if (ad == null) {
            throw new CustomException("广告id不存在");
        }
        if (!ad.getPublishUserId().equals(loginUserId)) {
            throw new CustomException("广告不属于当前用户");
        }
        if (ad.getAuditStatus().equals(AdvertisementStatus.WAITING_FOR_PUBLISH.getStatus())) {
            Advertisement advertisement = new Advertisement();
            advertisement.setAuditStatus(AdvertisementStatus.PUBLISHING.getStatus());
            advertisement.setPublishTime(LocalDateTime.now());
            advertisement.setExpireTime(LocalDateTime.now().plusDays(ad.getPublishDays())); // 设置过期时间
            advertisementMapper.update(advertisement, queryWrapper);
        } else {
            throw new CustomException("广告" + ad.getAuditStatus());
        }
    }

    @Override
    public void updateAuditState() {
        LambdaQueryWrapper<Advertisement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Advertisement::getAuditStatus, AdvertisementStatus.PUBLISHING.getStatus())
                .le(Advertisement::getExpireTime, LocalDateTime.now())
                .select(Advertisement::getId);
        // 要改的id集合
        List<Integer> list = advertisementMapper.selectList(queryWrapper).stream().map(Advertisement::getId).toList();
        if (list.isEmpty()) {
            return;
        }
        LambdaUpdateWrapper<Advertisement> luw = new LambdaUpdateWrapper<>();
        luw.in(Advertisement::getId, list)
                .set(Advertisement::getAuditStatus, AdvertisementStatus.EXPIRED.getStatus());
        advertisementMapper.update(luw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAdvertisement(Integer adId) {
        Advertisement ad = advertisementMapper.selectById(adId);
        if (ad == null) {
            throw new CustomException("广告不存在");
        }
        ad2tagMapper.delete(new LambdaQueryWrapper<Ad2tag>().eq(Ad2tag::getAdId, adId));
        advertisementMapper.delete(new LambdaUpdateWrapper<Advertisement>().eq(Advertisement::getId, adId));
        orderService.remove(new LambdaQueryWrapper<Order>().eq(Order::getAdId, adId));
        if (ad.getCoverUrl() != null) minioHelper.deleteFile(ad.getCoverUrl());
        if (ad.getContentUrl() != null) minioHelper.deleteFile(ad.getContentUrl());
    }

    @Override
    public List<AdvertisementPushDto> pushAd() {
        LambdaQueryWrapper<Advertisement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Advertisement::getAuditStatus, AdvertisementStatus.PUBLISHING.getStatus());
        List<Advertisement> advertisements = advertisementMapper.selectList(queryWrapper);
        StudentInfo studentInfo = studentInfoService.getById(Long.valueOf((String) StpUtil.getLoginId()));
        List<AdvertisementPushDto> list = new ArrayList<>();
        if (advertisements.isEmpty()) {
            return list;
        }
        for (Advertisement ad : advertisements) {
            String region = ad.getForRegion();
            if (region != null && !region.isEmpty()) {
                if (studentInfo.getPostalCode() != null && !studentInfo.getPostalCode().isEmpty() && !studentInfo.getPostalCode().equals(region)) {
                    continue;
                }
            }
            String school = ad.getForSchool();
            if (school != null && !school.isEmpty()) {
                if (studentInfo.getSchoolCode() != null && !studentInfo.getSchoolCode().isEmpty() && !studentInfo.getSchoolCode().equals(school)) {
                    continue;
                }
            }
            String college = ad.getForCollege();
            if (college != null && !college.isEmpty()) {
                if (studentInfo.getCollegeCode() != null && !studentInfo.getCollegeCode().isEmpty() && !studentInfo.getCollegeCode().equals(college)) {
                    continue;
                }
            }
            String gender = ad.getForGender();
            if (gender != null && !gender.isEmpty()) {
                if (studentInfo.getGender() != null && !studentInfo.getGender().isEmpty() && !studentInfo.getGender().equals(gender)) {
                    continue;
                }
            }
            AdvertisementPushDto adDto = advertisementMapping.toAdvertisementPush(ad);
            adDto.setTags(ad2tagMapper.selectByAdId(ad.getId()));
            list.add(adDto);
        }
        return list;
    }

    @Override
    public List<AdvertisementPushDto> adByTagId(Integer tag) {
        List<AdvertisementPushDto> advertisementPushDtos = advertisementMapping.AdListToAdPushList(ad2tagMapper.selectAdByTagId(tag));
        for (AdvertisementPushDto advertisementPushDto : advertisementPushDtos) {
            advertisementPushDto.setTags(ad2tagMapper.selectByAdId(advertisementPushDto.getId()));
        }
        return advertisementPushDtos;
    }

    @Override
    public List<AdvertisementDto> getListByStatus(String status) {
        if (AdvertisementStatus.INPROGRESS.getStatus().equals(status)) {
            List<Advertisement> advertisements = advertisementMapper.selectList(new LambdaQueryWrapper<Advertisement>()
                    .eq(Advertisement::getAuditStatus, AdvertisementStatus.WAITING_FOR_REVIEW.getStatus())
                    .or()
                    .eq(Advertisement::getAuditStatus, AdvertisementStatus.WAITING_FOR_PUBLISH.getStatus()));
            return setAdDtoTags(advertisementMapping.toAdvertisementDto(advertisements));
        }
        List<Advertisement> advertisements = advertisementMapper.selectList(new LambdaQueryWrapper<Advertisement>()
                .eq(Advertisement::getAuditStatus, status));
        return setAdDtoTags(advertisementMapping.toAdvertisementDto(advertisements));
    }

    private List<AdvertisementDto> setAdDtoTags(List<AdvertisementDto> list) {
        for (AdvertisementDto dto : list) {
            dto.setTags(ad2tagMapper.selectByAdId(dto.getId()));
        }
        return list;
    }
}
