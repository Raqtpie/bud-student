package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 学生用户表
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_stu_user")
public class StudentUser {
    /**
     * 学生id
     */
    @TableId(type = IdType.INPUT)
    @Hidden
    private Long id;

    /**
     * 微信Oauth2登录生成的id
     */
    @TableField("wx_id")
    @JsonIgnore
    private String wxId;

    /**
     * 华为Oauth2登录生成的id
     */
    @TableField("hw_id")
    @JsonIgnore
    private String hwId;

    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 激活状态
     */
    @TableField("active_status")
    @Hidden
    private Boolean activationStatus;

    /**
     * 封禁/过期状态
     */
    @TableField("ban_expire_status")
    @Hidden
    private Boolean banExpireStatus;
}
