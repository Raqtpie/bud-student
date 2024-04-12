package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.entity.School;
import top.turingteam.budstudent.pojo.vo.SchoolRegionDistributionVo;

import java.util.List;

/**
 * howe
 */
public interface SchoolService extends IService<School> {
    /**
     * 获取学校地域分布
     */
    List<SchoolRegionDistributionVo> getSchoolRegionDistribution();
}
