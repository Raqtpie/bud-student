package top.turingteam.budstudent.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 用于与CV处理模块交互的任务处理结果
 * @author Raqtpie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TaskCvProcessResponse {
    private Boolean success;

    private Integer studentId;

    private Integer taskId;

    private Boolean accessFlag;
}
