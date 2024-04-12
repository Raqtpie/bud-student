package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.pojo.entity.Gift;
import top.turingteam.budstudent.pojo.entity.StudentInfo;
import top.turingteam.budstudent.pojo.vo.GiftAddVo;
import top.turingteam.budstudent.pojo.vo.SchoolPointsRedeemPopularGiftsVo;

import java.util.List;

/**
* @author howe
*/
public interface GiftService extends IService<Gift> {
    List<Gift> getGiftList(String schoolCode);

    void addGift(GiftAddVo giftAddVo, MultipartFile file);

    void removeGift(Integer giftId);

    void exchangeGift(Integer giftId, StudentInfo studentInfo);

    void verifyGift(Integer giftId);

    /**
     * 获取学校热门礼品排行榜
     * @param school 学校
     * @return 排行榜
     */
    List<SchoolPointsRedeemPopularGiftsVo> getSchoolPointsRedeemPopularGifts(String school);
}
