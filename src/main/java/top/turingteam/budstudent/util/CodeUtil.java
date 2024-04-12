package top.turingteam.budstudent.util;

/**
 * @author howe
 */
public class CodeUtil {
    /**
     * 生成6位随机数字验证码
     *
     * @return 6位随机数字验证码
     */
    public static String getSixBitCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }
}
