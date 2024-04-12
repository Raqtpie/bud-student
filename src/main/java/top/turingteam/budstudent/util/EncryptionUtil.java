package top.turingteam.budstudent.util;

import cn.dev33.satoken.secure.SaSecureUtil;

/**
 * @author Raqtpie
 */
public class EncryptionUtil {
    private static final String SALT = "turing-wlb-bud-student9999";

    /**
     * 验证输入的加密文本是否正确
     * @param attempted 待验证的明文
     * @param encrypted 密文
     */
    public static boolean authenticate(String attempted, String encrypted) {
        // 用同样的盐值对用户输入的password进行加密
        String encryptedAttemptedPassword = getEncrypted(attempted);
        // 把加密后的密文和原密文进行比較，同样则验证成功。否则失败
        return encryptedAttemptedPassword.equals(encrypted);
    }

    /**
     * 生成密文
     * @param text 明文
     */
    public static String getEncrypted(String text) {
        return SaSecureUtil.sha256(text + SALT);
    }

    public static String encryptPhone(String phone) {
        return SaSecureUtil.aesEncrypt(SALT, phone);
    }

    public static String decryptPhone(String phone) {
        return SaSecureUtil.aesDecrypt(SALT, phone);
    }
}
