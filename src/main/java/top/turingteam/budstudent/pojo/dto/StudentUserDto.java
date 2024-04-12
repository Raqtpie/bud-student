package top.turingteam.budstudent.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import top.turingteam.budstudent.pojo.vo.StudentUserVo;

/**
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentUserDto {
    private StudentUserVo studentUserVo;

    private String schoolCode;

    private Boolean isLast;
}
