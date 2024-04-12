package top.turingteam.budstudent.util;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

/**
 * ID生成工具类
 * @author Raqtpie
 */
public class IDUtil {
    static {
        IdGeneratorOptions options = new IdGeneratorOptions();
        YitIdHelper.setIdGenerator(options);
    }

    public static Long nextId() {
        return YitIdHelper.nextId();
    }
}
