package top.turingteam.budstudent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.common.constant.PostalCodeToProvinceCommon;
import top.turingteam.budstudent.mapper.SchoolMapper;
import top.turingteam.budstudent.pojo.entity.School;
import top.turingteam.budstudent.pojo.vo.SchoolRegionDistributionVo;
import top.turingteam.budstudent.service.SchoolService;

import java.util.HashMap;
import java.util.List;

/**
 * howe
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Override
    public List<SchoolRegionDistributionVo> getSchoolRegionDistribution() {
        List<School> schools = baseMapper.selectList(null);
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (School school : schools) {
            String sub2PostalCode = school.getPostalCode().substring(0, 2);
            // 根据省份前两位代码查询对应的省份名称是什么？
            String provinceNameByPostalCode = PostalCodeToProvinceCommon.findProvinceNameByPostalCode(sub2PostalCode);
            if (hashMap.containsKey(provinceNameByPostalCode)) {
                hashMap.merge(provinceNameByPostalCode, 1, Integer::sum);
            } else {
                hashMap.put(provinceNameByPostalCode, 1);
            }
        }
        return hashMap.entrySet().stream().map(entry -> new SchoolRegionDistributionVo(entry.getKey(), entry.getValue().longValue())).toList();
    }
}




