package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.entity.Major;

import java.util.List;

/**
 * @author howe
 */
public interface MajorService extends IService<Major> {

    /**
     * 根据专业名称获取专业信息
     * @param majorName 专业名称
     * @param schoolCode 学校代码
     * @return 专业信息
     */
    List<Major> getMajorInfoByMajorName(String majorName, String schoolCode);
}
