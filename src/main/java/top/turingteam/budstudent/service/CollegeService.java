package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.entity.College;
import top.turingteam.budstudent.pojo.vo.CollegeVo;

/**
 * @author howe
 */
public interface CollegeService extends IService<College> {
    /**
     * 删除学院
     */
    void deleteCollege(String collegeCode);

    /**
     * 更新学院
     */
    void updateCollege(CollegeVo collegeVo);
}
