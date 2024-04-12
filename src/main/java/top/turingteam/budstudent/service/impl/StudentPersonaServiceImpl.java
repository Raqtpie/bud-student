package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.FileContentType;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.StudentInfoMapper;
import top.turingteam.budstudent.mapper.StudentPersonaMapper;
import top.turingteam.budstudent.mapper.StudentPointMapper;
import top.turingteam.budstudent.mapper.StudentPointRecordMapper;
import top.turingteam.budstudent.pojo.entity.StudentInfo;
import top.turingteam.budstudent.pojo.entity.StudentPersona;
import top.turingteam.budstudent.pojo.entity.StudentPoint;
import top.turingteam.budstudent.pojo.entity.StudentPointRecord;
import top.turingteam.budstudent.pojo.vo.StudentPersonaMsgVo;
import top.turingteam.budstudent.pojo.vo.StudentPersonaVo;
import top.turingteam.budstudent.service.StudentPersonaService;
import top.turingteam.budstudent.support.MinioHelper;
import top.turingteam.budstudent.support.RedisLockProvider;

import java.util.List;
import java.util.UUID;

/**
 * @author Raqtpie
 */
@RequiredArgsConstructor
@Service
public class StudentPersonaServiceImpl extends ServiceImpl<StudentPersonaMapper, StudentPersona> implements StudentPersonaService {
    private final StudentInfoMapper studentInfoMapper;

    private final StudentPersonaMapper studentPersonaMapper;

    private final StudentPointMapper studentPointMapper;

    private final StudentPointRecordMapper studentPointRecordMapper;

    private final RedisLockProvider redisLockProvider;

    private final MinioHelper minioHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPersona(StudentPersonaVo studentPersonaVo, MultipartFile file) {
        LambdaQueryWrapper<StudentPersona> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentPersona::getName, studentPersonaVo.getName());
        if (studentPersonaMapper.selectOne(wrapper) != null) {
            throw new RuntimeException("该形象已存在");
        }
        StudentPersona persona = StudentPersona.builder().name(studentPersonaVo.getName()).gender(studentPersonaVo.getGender()).requirePoint(studentPersonaVo.getRequirePoint()).build();
        uploadPersona(persona, file);
    }

    @Override
    public void updatePersona(StudentPersona studentPersona, MultipartFile file) {
        if (file == null) {
            studentPersonaMapper.updateById(studentPersona);
            return;
        }
        uploadPersona(studentPersona, file);
    }

    @Override
    public List<StudentPersonaMsgVo> getAllByStudentId(Long studentId) {
        StudentInfo studentInfo = studentInfoMapper.selectById(studentId);
        List<StudentPersonaMsgVo> allByStudentId = studentPersonaMapper.getAllByStudentId(studentInfo.getGender());
        for (StudentPersonaMsgVo studentPersonaMsgVo : allByStudentId) {
            if (studentPersonaMsgVo.getRequirePoint() == 0) {
                studentPersonaMsgVo.setOwnFlag(true);
            } else {
                studentPersonaMsgVo.setOwnFlag(studentPersonaMapper.isOwned(studentId, studentPersonaMsgVo.getId()));
            }
            studentPersonaMsgVo.setUseFlag(studentPersonaMapper.isUsed(studentId, studentPersonaMsgVo.getId()));
        }
        return allByStudentId;
    }

    @Override
    public StudentPersona getStudentPersonaById(Long studentId) {
        return studentPersonaMapper.getStudentPersonaById(studentId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void exchangePersona(Long studentId, Integer personaId) {
        String key = "exchangePersona:" + studentId + ":" + personaId;
        int expireTime = 10000;
        if (redisLockProvider.tryLock(key, Thread.currentThread().getName(), expireTime)) {
            StudentPersona studentPersona = studentPersonaMapper.selectById(personaId);
            if (studentPersona == null) {
                throw new CustomException("形象不存在");
            }
            if (studentPersona.getRequirePoint() == 0 || studentPersonaMapper.isOwned(studentId, personaId)) {
                throw new CustomException("已拥有该形象");
            }
            Integer requirePoint = studentPersona.getRequirePoint();
            StudentPoint studentPoint = studentPointMapper.selectById(studentId);
            int pointsNow = studentPoint.getPointsNow() - requirePoint;
            if (pointsNow < 0) {
                throw new CustomException("积分不足");
            }
            studentPoint.setPointsNow(pointsNow);
            studentPersonaMapper.insertStu2Persona(studentId, personaId);
            studentPointMapper.updateById(studentPoint);
            studentPointRecordMapper.insert(StudentPointRecord.builder().studentId(studentId).reason("兑换个人形象" + studentPersona.getName()).point(-requirePoint).build());
            redisLockProvider.releaseLock(key, Thread.currentThread().getName());
        } else {
            throw new CustomException("请勿重复操作");
        }
    }

    @Override
    public void setNowPersona(Long studentId, Integer personaId) {
        Boolean owned = studentPersonaMapper.isOwned(studentId, personaId);
        StudentPersona persona = studentPersonaMapper.selectById(personaId);
        if (!owned && persona.getRequirePoint() != 0) {
            throw new CustomException("未拥有该形象");
        }
        studentPersonaMapper.setNowPersona(studentId, personaId);
    }

    private void uploadPersona(StudentPersona studentPersona, MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
            throw new CustomException("文件类型不合法");
        }
        UUID uuid = UUID.randomUUID();
        String url = minioHelper.uploadFile(file, studentPersona.getUrl() == null ? "persona_" + uuid + "." + contentType.split("/")[1] : studentPersona.getUrl());
        studentPersona.setUrl(url);
        saveOrUpdate(studentPersona);
    }
}
