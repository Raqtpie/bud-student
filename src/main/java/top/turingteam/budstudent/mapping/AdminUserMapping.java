package top.turingteam.budstudent.mapping;

import org.mapstruct.Mapper;
import top.turingteam.budstudent.pojo.dto.AdminUserDto;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.vo.AdminRegisterVo;
import top.turingteam.budstudent.pojo.vo.AdminRetrieveVo;

/**
 * vo entity dto转换
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface AdminUserMapping {
    AdminUser toAdminUser(AdminRegisterVo adminRegisterVO);
    AdminUser toAdminUser(AdminRetrieveVo adminRetrieveVO);

    AdminUserDto toAdminUserDTO(AdminUser adminUser);
}
