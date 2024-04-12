package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.mapper.College2majorMapper;
import top.turingteam.budstudent.pojo.entity.College2major;
import top.turingteam.budstudent.pojo.entity.Major;
import top.turingteam.budstudent.service.College2majorService;

/**
 * @author howe
 */
@RequiredArgsConstructor
@Service
public class College2majorServiceImpl extends ServiceImpl<College2majorMapper, College2major> implements College2majorService {

    private final College2majorMapper college2majorMapper;

    @Override
    public Major[] selectMajorByCollegeCode(String collegeCode , String schoolCode) {
        return college2majorMapper.selectMajorByCollegeCode(collegeCode, schoolCode);
    }
}




