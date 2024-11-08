package com.f0rsakeN.weatheralert.exception;

/**
 * 用户异常
 *
 * @Author f0rsakeN
 * @Date 2024/10/28
 */
public class UserException extends BaseException {

    public UserException(int code) {
        super(code, "用户异常"); // 或者你可以定义一个默认的消息
    }

    public UserException(int code, String message) {
        super(code, message);
    }
}
