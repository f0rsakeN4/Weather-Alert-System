package com.f0rsakeN.weatheralert.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础响应
 *
 * @Author f0rsakeN
 * @Date 2024/10/24
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
