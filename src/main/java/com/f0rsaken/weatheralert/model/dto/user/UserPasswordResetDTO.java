package com.f0rsaken.weatheralert.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户修改密码
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
@Data
public class UserPasswordResetDTO implements Serializable {

    private Long id; // 可以根据用户ID验证身份

    private String oldPassword;

    private String newPassword;

    private static final long serialVersionUID = 1L;
}

