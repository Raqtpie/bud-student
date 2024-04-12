package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.entity.College2major;
import top.turingteam.budstudent.pojo.entity.Major;

/**
 * @author howe
 */
public interface College2majorService extends IService<College2major> {
    Major[] selectMajorByCollegeCode(String collegeCode, String schoolCode);
}
