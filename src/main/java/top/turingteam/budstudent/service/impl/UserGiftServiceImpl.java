package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.mapper.UserGiftMapper;
import top.turingteam.budstudent.pojo.dto.GiftListDto;
import top.turingteam.budstudent.pojo.entity.StudentInfo;
import top.turingteam.budstudent.pojo.entity.UserGift;
import top.turingteam.budstudent.service.UserGiftService;

/**
 * @author howe
 */
@Service
@RequiredArgsConstructor
public class UserGiftServiceImpl extends ServiceImpl<UserGiftMapper, UserGift>
        implements UserGiftService {

    private final UserGiftMapper userGiftMapper;

    @Override
    public GiftListDto[] MyGiftList(StudentInfo studentInfo) {
        return userGiftMapper.selectUserGiftByUserId(studentInfo.getId());
    }

    @Override
    public UserGift[] SchoolGiftExchangeList(String schoolCode) {
        return userGiftMapper.getUserGiftBySchoolCode(Long.parseLong(schoolCode));
    }
}




