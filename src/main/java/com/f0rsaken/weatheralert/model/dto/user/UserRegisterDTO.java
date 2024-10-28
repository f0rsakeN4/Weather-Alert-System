package com.f0rsaken.weatheralert.model.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用户注册
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
@Data
public class UserRegisterDTO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+={}:;\"'<>?,./]{8,}$", message = "密码必须至少8个字符，且包含字母和数字")
    private String password;

    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Pattern(regexp = "^\\d{10,15}$", message = "手机号格式不正确")
    private String phone;

    private static final long serialVersionUID = 1L;
}