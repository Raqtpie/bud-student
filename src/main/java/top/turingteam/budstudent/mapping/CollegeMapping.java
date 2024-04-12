package top.turingteam.budstudent.mapping;

import org.mapstruct.Mapper;
import top.turingteam.budstudent.pojo.entity.College;
import top.turingteam.budstudent.pojo.vo.CollegeVo;

/**
 * @author howe
 */

@Mapper(componentModel = "spring")
public interface CollegeMapping {
    College toCollege(CollegeVo collegeVo);
}
