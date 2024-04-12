package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.entity.StudentPoint;
import top.turingteam.budstudent.pojo.entity.StudentPointRecord;
import top.turingteam.budstudent.pojo.vo.StudentPointVo;

import java.util.List;

/**
 * @author Raqtpie
 */
public interface StudentPointService extends IService<StudentPoint> {
    /**
     * 更新身份证号
     * @param id 学生用户id
     * @return 学生登录用户
     */
    Integer getPoint(Long id);

    /**
     * 获取学生总积分
     * @param id 学生用户id
     * @return 学生总积分
     */
    Integer getTotalPoint(Long id);

    /**
     * 获取积分记录
     * @param id 学生用户id
     * @param current 当前页数
     * @param size 每页条数
     * @return 积分记录
     */
    List<StudentPointRecord> getPointRecord(Long id, Integer current, Integer size);

    /**
     * 获取学校排名
     * @param schoolCode 学校代码
     * @return 学校排名
     */
    List<StudentPointVo> getSchoolRank(String schoolCode);

    /**
     * 获取总排名
     * @return 总排名
     */
    List<StudentPointVo> getRank();
}
