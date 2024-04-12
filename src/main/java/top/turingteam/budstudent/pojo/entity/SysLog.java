package top.turingteam.budstudent.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Raqtpie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TableName("tb_sys_log")
public class SysLog {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Long userId;

    private String ip;

    @TableField("method_name")
    private String methodName;

    @TableField("method_mark")
    private String methodMark;

    @TableField("req_url")
    private String reqUrl;

    @TableField("opt_req_param")
    private String optReqParam;

    @TableField("opt_resp_info")
    private String optRespInfo;

    @TableField("create_at")
    private Date createTime;

    @TableField("update_at")
    private Date updateTime;
}
