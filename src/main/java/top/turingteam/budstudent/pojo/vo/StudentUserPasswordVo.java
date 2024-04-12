package top.turingteam.budstudent.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUserPasswordVo {
    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;
}
