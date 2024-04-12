package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.dto.GiftListDto;
import top.turingteam.budstudent.pojo.entity.StudentInfo;
import top.turingteam.budstudent.pojo.entity.UserGift;

/**
* @author howe
*/
public interface UserGiftService extends IService<UserGift> {
    GiftListDto[] MyGiftList(StudentInfo studentInfo);

    UserGift[] SchoolGiftExchangeList(String schoolCode);
}
