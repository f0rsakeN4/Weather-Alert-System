package com.f0rsaken.weatheralert.handler;


import com.f0rsaken.weatheralert.common.BaseResponse;
import com.f0rsaken.weatheralert.common.ErrorCode;
import com.f0rsaken.weatheralert.common.ResultUtils;
import com.f0rsaken.weatheralert.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public BaseResponse<?> handleBusinessException(UserException e) {
        log.error("UserException: {}", e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        log.error("Validation error: {}", errorMessage, e);
        return ResultUtils.error(ErrorCode.PARAMS_ERROR.getCode(), errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<?> handleException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage(), e);
        return ResultUtils.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
