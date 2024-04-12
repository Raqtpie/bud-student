package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.mapper.TaskObjectMapper;
import top.turingteam.budstudent.pojo.entity.TaskObject;
import top.turingteam.budstudent.service.TaskObjectService;

/**
 * @author Raqtpie
 */
@Service
public class TaskObjectServiceImpl extends ServiceImpl<TaskObjectMapper, TaskObject> implements TaskObjectService {
}
