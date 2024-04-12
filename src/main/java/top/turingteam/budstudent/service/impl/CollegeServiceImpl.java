package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.CollegeMapper;
import top.turingteam.budstudent.mapping.CollegeMapping;
import top.turingteam.budstudent.pojo.entity.College;
import top.turingteam.budstudent.pojo.vo.CollegeVo;
import top.turingteam.budstudent.service.CollegeService;
import top.turingteam.budstudent.support.LoginUserProvider;

/**
 * @author howe
 */
@RequiredArgsConstructor
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {
    private final LoginUserProvider loginUserProvider;

    private final CollegeMapper collegeMapper;

    private final CollegeMapping collegeMapping;



    @Override
    public void deleteCollege(String collegeCode) {
        College college = new College();
        college.setSchoolCode(loginUserProvider.getAdminLoginUser().getSchool());
        college.setCollageCode(collegeCode);
        LambdaQueryWrapper<College> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(College::getSchoolCode, college.getSchoolCode())
                .eq(College::getCollageCode, college.getCollageCode());
        int delete = collegeMapper.delete(wrapper);
        if (delete == 0) {
            throw new CustomException("删除学院失败");
        }
    }

    @Override
    public void updateCollege(CollegeVo collegeVo) {
        College college = collegeMapping.toCollege(collegeVo);
        college.setSchoolCode(loginUserProvider.getAdminLoginUser().getSchool());
        LambdaQueryWrapper<College> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(College::getSchoolCode, college.getSchoolCode())
                .eq(College::getCollageCode, college.getCollageCode());
        int update = collegeMapper.update(college, wrapper);
        if (update == 0) {
            throw new CustomException("更新学院失败");
        }
    }


}




