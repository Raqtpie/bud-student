package top.turingteam.budstudent.mapping;

import org.mapstruct.Mapper;
import top.turingteam.budstudent.pojo.dto.TagDto;
import top.turingteam.budstudent.pojo.entity.TagEntity;
import top.turingteam.budstudent.pojo.vo.TagVo;

import java.util.List;

/**
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface TagMapping {
    TagEntity toTag(TagVo tagVo);

    List<TagDto> toTagDto(List<TagEntity> tagEntityList);

    TagDto toTagDto(TagEntity tagEntity);
}
