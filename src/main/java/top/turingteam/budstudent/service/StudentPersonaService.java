package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.turingteam.budstudent.pojo.entity.StudentPersona;
import top.turingteam.budstudent.pojo.vo.StudentPersonaMsgVo;
import top.turingteam.budstudent.pojo.vo.StudentPersonaVo;

import java.util.List;

/**
 * @author Raqtpie
 */
public interface StudentPersonaService extends IService<StudentPersona> {
    /**
     * 添加学生个人形象
     * @param studentPersonaVo 学生个人形象
     * @param file 图片
     */
    void addPersona(StudentPersonaVo studentPersonaVo, MultipartFile file);

    /**
     * 修改学生个人形象
     * @param studentPersona 学生个人形象
     * @param file 图片
     */
    void updatePersona(StudentPersona studentPersona, MultipartFile file);

    /**
     * 获取所有学生个人形象
     * @param studentId 学生id
     * @return 学生个人形象
     */
    List<StudentPersonaMsgVo> getAllByStudentId(Long studentId);

    /**
     * 获取学生当前的个人形象
     * @param studentId 学生id
     * @return 学生个人形象
     */
    StudentPersona getStudentPersonaById(Long studentId);

    /**
     * 兑换学生个人形象
     * @param studentId 学生id
     * @param personaId 个人形象id
     */
    void exchangePersona(Long studentId, Integer personaId);

    /**
     * 设置学生当前的个人形象
     * @param studentId 学生id
     * @param personaId 个人形象id
     */
    void setNowPersona(Long studentId, Integer personaId);
}
