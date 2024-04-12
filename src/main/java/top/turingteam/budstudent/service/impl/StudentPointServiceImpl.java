package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.mapper.StudentPointMapper;
import top.turingteam.budstudent.mapper.StudentPointRecordMapper;
import top.turingteam.budstudent.pojo.entity.StudentPoint;
import top.turingteam.budstudent.pojo.entity.StudentPointRecord;
import top.turingteam.budstudent.pojo.vo.StudentPointVo;
import top.turingteam.budstudent.service.StudentPointService;

import java.util.List;

/**
 * @author Raqtpie
 */
@RequiredArgsConstructor
@Service
public class StudentPointServiceImpl extends ServiceImpl<StudentPointMapper, StudentPoint> implements StudentPointService {
    private final StudentPointMapper studentPointMapper;

    private final StudentPointRecordMapper studentPointRecordMapper;

    @Override
    public Integer getPoint(Long id) {
        StudentPoint studentPoint = studentPointMapper.selectById(id);
        return studentPoint.getPointsNow();
    }

    @Override
    public Integer getTotalPoint(Long id) {
        StudentPoint studentPoint = studentPointMapper.selectById(id);
        return studentPoint.getPointsTotal();
    }

    @Override
    public List<StudentPointRecord> getPointRecord(Long id, Integer current, Integer size) {
        LambdaQueryWrapper<StudentPointRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentPointRecord::getStudentId, id);
        wrapper.orderByDesc(StudentPointRecord::getTime);
        Page<StudentPointRecord> page = new Page<>(current, size);
        return studentPointRecordMapper.selectList(page, wrapper);
    }

    @Override
    public List<StudentPointVo> getSchoolRank(String schoolCode) {
        StudentPointVo[] studentPointVos = studentPointMapper.selectSchoolRank(schoolCode);
        return studentPointVos == null ? null : List.of(studentPointVos);
    }

    @Override
    public List<StudentPointVo> getRank() {
        StudentPointVo[] studentPointVos = studentPointMapper.selectTotalRank();
        return studentPointVos == null ? null : List.of(studentPointVos);
    }
}
