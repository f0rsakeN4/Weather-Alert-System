package com.f0rsakeN.weatheralert.common;

/**
 * 错误码
 *
 * @Author f0rsakeN
 * @Date 2024/10/24
 */
public enum ErrorCode {
    SUCCESS(200, "OK"),
    BAD_REQUEST(400, "错误请求"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "未找到"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    USER_ALREADY_EXISTS(40001, "用户名已存在"),
    PARAMS_ERROR(40002, "参数错误"),
    USER_REGISTER_FAILED(40003, "注册失败"),
    USER_LOGIN_FAILED(40005, "用户名/密码错误"),
    INACTIVE_USER(40004, "用户未激活"),
    VERIFICATION_CODE_EXPIRED(40007, "验证码已过期"),
    VERIFICATION_CODE_ERROR(40008, "无效的验证码"),
    USER_NOT_FOUND(40006, "用户不存在");


    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
