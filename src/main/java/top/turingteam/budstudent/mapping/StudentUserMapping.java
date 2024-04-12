package top.turingteam.budstudent.mapping;

import org.mapstruct.Mapper;
import top.turingteam.budstudent.pojo.vo.StudentUserVo;
import top.turingteam.budstudent.pojo.entity.StudentInfo;
import top.turingteam.budstudent.pojo.entity.StudentUser;

/**
 * @author Raqtpie
 */
@Mapper(componentModel = "spring")
public interface StudentUserMapping {
    /**
     * vo转entity
     * @param studentUserVo vo
     * @return entity
     */
    StudentUser toStudentUser(StudentUserVo studentUserVo);

    /**
     * entity转vo
     * @param studentUserVo entity
     * @return vo
     */
    StudentInfo toStudentInfo(StudentUserVo studentUserVo);
}
