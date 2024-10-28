package com.f0rsaken.weatheralert.model.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
@Data
public class UserLoginDTO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private static final long serialVersionUID = 1L;
}
