package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.pojo.entity.StudentInfo;
import top.turingteam.budstudent.pojo.vo.StudentActiveCountByProvinceVo;
import top.turingteam.budstudent.pojo.vo.StudentInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @author Raqtpie
 */
public interface StudentInfoService extends IService<StudentInfo> {
    /**
     * 更新头像
     * @param id 学生用户id
     * @param file 头像文件
     */
    void updateAvatar(Long id, MultipartFile file);

    /**
     * 更新昵称
     * @param id 学生用户id
     * @param signature 昵称
     */
    void updateSignature(Long id, String signature);

    /**
     * 更新地址
     * @param id 学生用户id
     * @param address 地址
     */
    void updateAddress(Long id, String address);

    /**
     * 获取学生信息
     * @param id 学生用户id
     * @return 学生信息
     */
    StudentInfoVo getInfoById(Long id);

    /**
     * 获取各省激活人数
     * @return 各省激活人数
     */
    List<StudentActiveCountByProvinceVo> getActiveCountForEveryProvince();

    /**
     * 获取学校各省激活人数
     * @param schoolCode 学校代码
     * @return 某学校各省激活人数
     */
    List<StudentActiveCountByProvinceVo> getActiveCountForEveryProvinceBySchoolCode(String schoolCode);

    /**
     * 获取学校激活与未激活的人数
     *
     * @param schoolCode 学校代码
     * @return 某学校激活与未激活的人数
     */
    Map<String, Double> getStudentActiveCountBySchoolCode(String schoolCode);

    /**
     * 获取学生列表
     * @param schoolCode 学校代码
     * @param page 页码
     * @param size 每页大小
     * @return 学生列表
     */
    List<StudentInfo> getList(String schoolCode, Integer page, Integer size);

    /**
     * 添加学生照片
     * @param studentId 学生id
     * @param photo 照片
     */
    void addPhotoByStudentId(Long studentId, MultipartFile photo);
}
