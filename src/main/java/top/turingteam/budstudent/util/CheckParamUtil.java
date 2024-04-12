package top.turingteam.budstudent.util;

import cn.hutool.core.lang.Validator;
import top.turingteam.budstudent.exception.CustomException;

/**
 * @author Raqtpie
 */
public class CheckParamUtil {
    private CheckParamUtil() {}

    public static void checkAll(String idCard, String phone, String name) {
        boolean mobile = Validator.isMobile(phone);
//        boolean citizenId = Validator.isCitizenId(idCard);
        boolean citizenId = idCard.matches("^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9Xx]$");
        boolean chineseName = Validator.isChineseName(name);
        if (!mobile) {
            throw new CustomException("手机号格式错误");
        } else if (!citizenId) {
            throw new CustomException("身份证格式错误");
        } else if (!chineseName) {
            // 正则表达式匹配英语姓名
            String regex = "^[a-zA-Z]+(?: [a-zA-Z]+)?$\n";
            if (!name.matches(regex)) {
                throw new CustomException("姓名格式错误");
            }
        }
    }
}
