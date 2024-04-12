package top.turingteam.budstudent.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.FileContentType;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.GiftMapper;
import top.turingteam.budstudent.mapper.StudentPointMapper;
import top.turingteam.budstudent.mapper.StudentPointRecordMapper;
import top.turingteam.budstudent.mapper.UserGiftMapper;
import top.turingteam.budstudent.mapping.GiftMapping;
import top.turingteam.budstudent.pojo.entity.*;
import top.turingteam.budstudent.pojo.vo.GiftAddVo;
import top.turingteam.budstudent.pojo.vo.SchoolPointsRedeemPopularGiftsVo;
import top.turingteam.budstudent.service.GiftService;
import top.turingteam.budstudent.service.UserGiftService;
import top.turingteam.budstudent.support.LoginUserProvider;
import top.turingteam.budstudent.support.MinioHelper;
import top.turingteam.budstudent.support.RedisLockProvider;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author howe
 */
@RequiredArgsConstructor
@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements GiftService {
    private final GiftMapping giftMapping;
    private final LoginUserProvider loginUserProvider;
    private final MinioHelper minio;
    private final UserGiftService userGiftService;
    private final RedisLockProvider redisLockProvider;
    private final StudentPointMapper studentPointMapper;
    private final UserGiftMapper userGiftMapper;
    private final StudentPointRecordMapper studentPointRecordMapper;

    @Override
    public List<Gift> getGiftList(String schoolCode) {
        LambdaQueryWrapper<Gift> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Gift::getSchoolCode, schoolCode);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void addGift(GiftAddVo giftAddVo, MultipartFile file) {
        Gift gift = giftMapping.GiftAddVoToGift(giftAddVo);
        if (gift.getMaxExchange() != -1 && gift.getMaxExchange() <= 0) {
            throw new CustomException("最大兑换次数(礼品库存)必须大于0");
        }
        if (file != null) {
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
                throw new CustomException("文件类型不合法");
            }
            String filename = UUID.randomUUID() + "." + contentType.split("/")[1];
            gift.setPhotoUrl(minio.uploadFile(file, filename));
        }
        AdminUser adminUser = loginUserProvider.getAdminLoginUser();
        gift.setCreateId(adminUser.getId());
        gift.setSchoolCode(adminUser.getSchool());
        if (!save(gift)) {
            throw new CustomException("添加礼品失败");
        }
    }

    @Override
    public void removeGift(Integer id) {
        LambdaQueryWrapper<Gift> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Gift::getId, id).eq(Gift::getCreateId, Long.parseLong((String) StpUtil.getLoginId()));
        if (!remove(queryWrapper)) {
            throw new CustomException("删除礼品失败");
        }
    }

    @Override
    public void exchangeGift(Integer giftId, StudentInfo studentInfo) {
        String key = "exchange_gift_" + giftId;
        int expireTime = 10000;
        try {
            while (true) {
                if (redisLockProvider.tryLock(key, Thread.currentThread().getName(), expireTime)) {
                    Gift gift = getById(giftId);
                    if (gift == null) {
                        throw new CustomException("礼品不存在");
                    }
                    if (gift.getMaxExchange() == 0) {
                        throw new CustomException("礼品库存不足");
                    }
                    if (!gift.getSchoolCode().equals(studentInfo.getSchoolCode())) {
                        throw new CustomException("不能兑换非本校礼品");
                    }
                    StudentPoint studentPoint = studentPointMapper.selectById(studentInfo.getId());
                    if (studentPoint.getPointsNow() < gift.getPoint()) {
                        throw new CustomException("积分不足");
                    }
                    studentPoint.setPointsNow(studentPoint.getPointsNow() - gift.getPoint());
                    // 扣除积分
                    studentPointMapper.updateById(studentPoint);
                    // 添加积分记录
                    studentPointRecordMapper.insert(StudentPointRecord.builder()
                            .studentId(studentInfo.getId())
                            .reason("兑换礼品：" + gift.getName())
                            .point(-gift.getPoint()).build());
                    // 扣减库存
                    if (gift.getMaxExchange() != -1) {
                        // 有兑换次数限制，减掉库存
                        LambdaUpdateWrapper<Gift> updateWrapper = new LambdaUpdateWrapper<>();
                        updateWrapper.eq(Gift::getId, giftId).setSql("max_exchange = max_exchange - 1");
                        if (!update(updateWrapper)) {
                            throw new CustomException("兑换失败");
                        }
                    }
                    UserGift userGift = new UserGift();
                    userGift.setGiftId(giftId);
                    userGift.setUserId(studentInfo.getId());
                    if (!userGiftService.save(userGift)) {
                        throw new CustomException("兑换失败");
                    }
                    break;
                }
            }
        } finally {
            redisLockProvider.releaseLock(key, Thread.currentThread().getName());
        }
    }

    @Override
    public void verifyGift(Integer giftId) {
        UserGift userGift = userGiftService.getById(giftId);
        if (userGift == null) {
            throw new CustomException("兑换记录不存在");
        }
        if (userGift.getStatus() == 1) {
            throw new CustomException("已核销");
        }
        userGift.setStatus(1);
        if (!userGiftService.updateById(userGift)) {
            throw new CustomException("核销失败");
        }
    }

    @Override
    public List<SchoolPointsRedeemPopularGiftsVo> getSchoolPointsRedeemPopularGifts(String school) {
        LambdaQueryWrapper<Gift> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Gift::getSchoolCode, school);
        List<Gift> gifts = baseMapper.selectList(queryWrapper);
        return gifts.stream()
                .map(gift -> {
                    Integer count = userGiftMapper.exchangeGiftCount(gift.getId());
                    return new SchoolPointsRedeemPopularGiftsVo(gift.getId(), gift.getName(), count);
                })
                .sorted(Comparator.comparingInt(SchoolPointsRedeemPopularGiftsVo::getCount))
                .collect(Collectors.toList());
    }

}




