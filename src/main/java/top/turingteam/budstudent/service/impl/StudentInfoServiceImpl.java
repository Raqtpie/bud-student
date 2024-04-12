package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.common.constant.FileContentType;
import top.turingteam.budstudent.common.constant.PostalCodeToProvinceCommon;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.*;
import top.turingteam.budstudent.pojo.entity.*;
import top.turingteam.budstudent.pojo.vo.StudentActiveCountByProvinceVo;
import top.turingteam.budstudent.pojo.vo.StudentInfoVo;
import top.turingteam.budstudent.service.StudentInfoService;
import top.turingteam.budstudent.support.MinioHelper;
import top.turingteam.budstudent.util.EncryptionUtil;

import java.util.*;

/**
 * @author Raqtpie
 */
@RequiredArgsConstructor
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements StudentInfoService {
    private final StudentInfoMapper studentInfoMapper;

    private final StudentUserMapper studentUserMapper;

    private final SchoolMapper schoolMapper;

    private final CollegeMapper collegeMapper;

    private final MajorMapper majorMapper;

    private final MinioHelper minioHelper;

    @Override
    public void updateAvatar(Long id, MultipartFile file) {
        StudentInfo studentInfo = studentInfoMapper.selectById(id);
        String contentType = file.getContentType();
        // 检测文件类型
        if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
            throw new CustomException("文件类型不合法");
        }
        String filename = id + "." + contentType.split("/")[1];
        String url = minioHelper.uploadFile(file, filename);
        studentInfo.setAvatarUrl(url);
        studentInfoMapper.updateById(studentInfo);
    }

    @Override
    public void updateSignature(Long id, String signature) {
        StudentInfo studentInfo = studentInfoMapper.selectById(id);
        studentInfo.setSignature(signature);
        studentInfoMapper.updateById(studentInfo);
    }

    @Override
    public void updateAddress(Long id, String address) {
        StudentInfo studentInfo = studentInfoMapper.selectById(id);
        studentInfo.setAddress(address);
        studentInfoMapper.updateById(studentInfo);
    }

    @Override
    public StudentInfoVo getInfoById(Long id) {
        StudentInfo studentInfo = studentInfoMapper.selectById(id);
        StudentInfoVo studentInfoVo = new StudentInfoVo();
        BeanUtils.copyProperties(studentInfo, studentInfoVo);
        String phone = EncryptionUtil.decryptPhone(studentUserMapper.selectById(id).getPhone());
        phone = phone.substring(0, 3) + "****" + phone.substring(7);
        studentInfoVo.setPhone(phone);
        School school = schoolMapper.selectOne(new LambdaQueryWrapper<School>().eq(School::getSchoolCode, studentInfo.getSchoolCode()));
        studentInfoVo.setSchool(school == null? null : school.getName());
        College college = collegeMapper.selectOne(new LambdaQueryWrapper<College>().eq(College::getSchoolCode, studentInfo.getSchoolCode()).eq(College::getCollageCode, studentInfo.getCollegeCode()));
        studentInfoVo.setCollegeName(college == null ? null : college.getName());
        Major major = majorMapper.selectOne(new LambdaQueryWrapper<Major>().eq(Major::getMajorCode, studentInfo.getMajorCode()));
        studentInfoVo.setMajor(major == null ? null : major.getName());
        return studentInfoVo;
    }

    @Override
    public List<StudentActiveCountByProvinceVo> getActiveCountForEveryProvince() {
        Map<String, StudentActiveCountByProvinceVo> map = new HashMap<>(34);
        for (PostalCodeToProvinceCommon value : PostalCodeToProvinceCommon.values()) {
            String postalCode = value.getPostalCode();
            String provinceName = value.getProvinceName();
            Long activeStudentCountByProvince = studentInfoMapper.getActiveStudentCountByProvince(postalCode);
            StudentActiveCountByProvinceVo studentActiveCountByProvinceVo = map.get(provinceName);
            if (studentActiveCountByProvinceVo != null) {
                studentActiveCountByProvinceVo.setActiveCount(studentActiveCountByProvinceVo.getActiveCount() + activeStudentCountByProvince);
            } else {
                studentActiveCountByProvinceVo = StudentActiveCountByProvinceVo.builder()
                        .provinceName(provinceName)
                        .activeCount(activeStudentCountByProvince)
                        .build();
                map.put(provinceName, studentActiveCountByProvinceVo);

            }
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public List<StudentActiveCountByProvinceVo> getActiveCountForEveryProvinceBySchoolCode(String schoolCode) {
        List<StudentActiveCountByProvinceVo> studentActiveCountByProvinceVos = new ArrayList<>();
        for (PostalCodeToProvinceCommon value : PostalCodeToProvinceCommon.values()) {
            String postalCode = value.getPostalCode();
            String provinceName = value.getProvinceName();
            Long activeStudentCountByProvince = studentInfoMapper.getActiveStudentCountByProvinceAndSchoolCode(postalCode, schoolCode);
            studentActiveCountByProvinceVos.add(StudentActiveCountByProvinceVo.builder()
                    .provinceName(provinceName)
                    .activeCount(activeStudentCountByProvince)
                    .build());
        }
        return studentActiveCountByProvinceVos;
    }

    @Override
    public Map<String, Double> getStudentActiveCountBySchoolCode(String schoolCode) {
        Map<String, Double> map = new HashMap<>(3);
        map.put("totalCount", Double.valueOf(studentInfoMapper.selectCountBySchoolId(schoolCode)));
        map.put("activeCount", Double.valueOf(studentInfoMapper.selectActiveCountBySchoolId(schoolCode)));
        map.put("inactiveCount", map.get("totalCount") - map.get("activeCount"));
        map.put("activeRate", map.get("totalCount") == 0 ? 0 : map.get("activeCount") * 100 / map.get("totalCount"));
        map.put("inactiveRate", 100.0 - map.get("activeRate"));
        return map;
    }

    @Override
    public List<StudentInfo> getList(String schoolCode, Integer page, Integer size) {
        StudentInfo[] studentInfos = studentInfoMapper.getList(schoolCode, (page - 1) * size, size);
        return Arrays.asList(studentInfos);
    }

    @Override
    public void addPhotoByStudentId(Long studentId, MultipartFile photo) {
        StudentInfo studentInfo = studentInfoMapper.selectById(studentId);
        String contentType = photo.getContentType();
        // 检测文件类型
        if (contentType == null || !contentType.startsWith(FileContentType.IMAGE_TYPE)) {
            throw new CustomException("文件类型不合法");
        }
        String filename = "face_" + studentId + "." + contentType.split("/")[1];
        String url = minioHelper.uploadFile(photo, filename);
        studentInfo.setPhotoUrl(url);
        studentInfoMapper.updateById(studentInfo);
    }
}
