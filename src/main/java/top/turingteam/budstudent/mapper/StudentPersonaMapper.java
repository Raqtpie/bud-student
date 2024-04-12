package top.turingteam.budstudent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.turingteam.budstudent.pojo.entity.StudentPersona;
import top.turingteam.budstudent.pojo.vo.StudentPersonaMsgVo;

import java.util.List;

/**
 * @author Raqtpie
 */
@Mapper
public interface StudentPersonaMapper extends BaseMapper<StudentPersona> {
    /**
     * 获取学生当前的个人形象
     * @param studentId 学生id
     * @return 学生个人形象
     */
    StudentPersona getStudentPersonaById(Long studentId);

    /**
     * 判断学生是否拥有个人形象
     * @param studentId 学生id
     * @param personaId 个人形象id
     * @return 是否拥有
     */
    Boolean isOwned(Long studentId, Integer personaId);

    /**
     * 判断学生是否使用个人形象
     * @param studentId 学生id
     * @param personaId 个人形象id
     * @return 是否使用
     */
    Boolean isUsed(Long studentId, Integer personaId);

    /**
     * 插入学生个人形象拥有关系
     * @param studentId 学生id
     * @param personaId 个人形象id
     */
    void insertStu2Persona(Long studentId, Integer personaId);

    /**
     * 设置当前形象
     * @param studentId 学生id
     * @param personaId 个人形象id
     */
    void setNowPersona(Long studentId, Integer personaId);

    /**
     * 新增当前形象行数据
     * @param studentId 学生id
     * @param personaId 个人形象id
     */
    void insertNowPersona(Long studentId, Integer personaId);

    /**
     * 获取所有学生个人形象
     * @param gender 性别
     * @return 学生个人形象
     */
    List<StudentPersonaMsgVo> getAllByStudentId(String gender);
}
