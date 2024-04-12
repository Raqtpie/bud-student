package top.turingteam.budstudent.common.constant;

/**
 * @author howe
 */
public class ExcludePathCommon {
    /**
     * 放行路径
     */
    public static final String[] EXCLUDE_PATH_PATTERNS = {
            // Swagger
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v3/**",
            "/swagger-ui.html/**",
            "/doc.html/**",
            "/error",
            "/favicon.ico",
            "/student/user/login",
            "/student/user/loginByWeChat/**",
            "/student/user/checkNotice/**",
            "/student/user/checkIdCardAndActive/**",
            "/admin/user/login",
            "/admin/user/register/**",
            "/admin/user/retrieve/**",
            "/merchant/user/login",
            "/merchant/user/register/**",
            "/merchant/user/retrieve/**",
            "/task/submitTaskByFace/**",
            "/alipay/**",
            "/order/notify"
    };

    public static final String[] WEBLOG_EXCLUDE = {
            "login",
            "logout",
            "register",
            "code",
            "activeCheckNotice",
            "active",
            "submitTaskByFace",
            "alipay",
            "order/notify"
    };
}
