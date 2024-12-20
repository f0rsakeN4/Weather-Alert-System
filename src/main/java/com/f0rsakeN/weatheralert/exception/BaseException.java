package com.f0rsakeN.weatheralert.exception;

/**
 * 基础异常类
 *
 * @Author f0rsakeN
 * @Date 2024/10/28
 */
public class BaseException extends RuntimeException {

    private int code;

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
