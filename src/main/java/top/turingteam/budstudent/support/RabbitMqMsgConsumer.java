package top.turingteam.budstudent.support;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import top.turingteam.budstudent.pojo.dto.StudentUserDto;
import top.turingteam.budstudent.pojo.entity.StudentUser;
import top.turingteam.budstudent.pojo.vo.StudentUserVo;
import top.turingteam.budstudent.service.StudentUserService;

import static top.turingteam.budstudent.common.constant.MqCommon.QUEUE_NAME;

/**
 * @author Raqtpie
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqMsgConsumer {

    private final StudentUserService studentUserService;

    private final StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queues = QUEUE_NAME)
    public void receive(String message) {
        StudentUserDto studentUserDto = JSONUtil.parseObj(message).toBean(StudentUserDto.class);
        StudentUserVo studentUserVo = studentUserDto.getStudentUserVo();
        String schoolCode = studentUserDto.getSchoolCode();
        try {
            StudentUser register = studentUserService.register(studentUserVo, schoolCode);
            if (register == null) {
                stringRedisTemplate.opsForList().leftPush(schoolCode + ":failList", message);
                log.error("注册失败，学生信息为：{}", studentUserVo);
                return;
            }
            log.info("批量注册学生成功，学生姓名：{}", studentUserVo.getName());
        } catch (Exception e) {
            stringRedisTemplate.opsForList().leftPush(schoolCode + ":failList", message);
            log.error("注册失败，异常信息：{}，学生信息为：{}", e, studentUserVo);
        }
    }
}
