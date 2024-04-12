package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.mapper.College2majorMapper;
import top.turingteam.budstudent.mapper.CollegeMapper;
import top.turingteam.budstudent.mapper.MajorMapper;
import top.turingteam.budstudent.pojo.entity.College;
import top.turingteam.budstudent.pojo.entity.College2major;
import top.turingteam.budstudent.pojo.entity.Major;
import top.turingteam.budstudent.service.MajorService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author howe
 */
@RequiredArgsConstructor
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService{

    private final MajorMapper majorMapper;

    private final CollegeMapper collegeMapper;

    private final College2majorMapper college2majorMapper;

    @Override
    public List<Major> getMajorInfoByMajorName(String majorName, String schoolCode) {
        List<Major> result = new ArrayList<>();
        LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Major::getName, majorName);
        List<Major> majors = majorMapper.selectList(queryWrapper);
        LambdaQueryWrapper<College> collegeQueryWrapper = new LambdaQueryWrapper<>();
        collegeQueryWrapper.eq(College::getSchoolCode, schoolCode);
        collegeMapper.selectList(collegeQueryWrapper).forEach(college -> {
            majors.forEach(major -> {
                LambdaQueryWrapper<College2major> college2majorQueryWrapper = new LambdaQueryWrapper<>();
                college2majorQueryWrapper.eq(College2major::getCollegeCode, college.getCollageCode());
                college2majorQueryWrapper.eq(College2major::getMajorCode, major.getMajorCode());
                if (college2majorMapper.selectOne(college2majorQueryWrapper) != null) {
                    result.add(major);
                }
            });
        });
        return result;
    }
}




