package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import top.turingteam.budstudent.pojo.dto.MerchantUserDto;
import top.turingteam.budstudent.pojo.entity.MerchantUser;
import top.turingteam.budstudent.pojo.vo.MerchantRetrieveVo;
import top.turingteam.budstudent.pojo.vo.MerchantUserVo;

/**
 * @author howe
 */
public interface MerchantUserService extends IService<MerchantUser> {
    /**
     * 根据用户名或手机号获取商家登录用户
     *
     * @param merchantUserVo 商家登录用户
     * @return 商家登录用户
     */
    MerchantUserDto getMerchantLoginUserByPhone(HttpServletRequest request, MerchantUserVo merchantUserVo);

    /**
     * 注册商家用户
     *
     * @param merchantRetrieveVo 商家用户
     * @return 商家用户
     */
    MerchantUserDto registerMerchantUser(HttpServletRequest request, MerchantRetrieveVo merchantRetrieveVo);

    /**
     * 获取手机验证码
     *
     * @param phone 商家用户
     */
    void getRetrievePhoneCode(HttpServletRequest request, String phone);

    void getRegisterPhoneCode(HttpServletRequest request, String phone);

    /**
     * 修改商家用户密码
     *
     * @param merchantRetrieveVoe 商家用户
     */
    void retrieveMerchantUser(HttpServletRequest request, MerchantRetrieveVo merchantRetrieveVoe);
}