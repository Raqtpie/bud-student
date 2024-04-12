package top.turingteam.budstudent.common.constant;

/**
 * @author Raqtpie
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200, "success"),
    /**
     * 服务器内部错误
     */
    ERROR(500, "error"),
    /**
     * 资源未找到
     */
    NOT_FOUND(404, "not found"),
    /**
     * 请求错误
     */
    BAD_REQUEST(400, "bad request"),
    /**
     * 请求未授权
     */
    UNAUTHORIZED(403, "unauthorized");
    /**
     * 状态码
     */
    public final int code;
    /**
     * 状态码描述
     */
    public final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
