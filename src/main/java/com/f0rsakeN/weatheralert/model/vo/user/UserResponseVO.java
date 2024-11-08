package com.f0rsakeN.weatheralert.model.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户注册/登录返回类
 *
 * @Author f0rsakeN
 * @Date 2024/10/24
 */
@Data
public class UserResponseVO implements Serializable {

    private String username;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private static final long serialVersionUID = 1L;

}
