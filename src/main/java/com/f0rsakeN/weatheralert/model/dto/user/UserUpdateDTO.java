package com.f0rsakeN.weatheralert.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateDTO implements Serializable {

    private Long id; // 更新时通常需要用户ID

    private String email;

    private String phone;

    private Integer status; // 允许管理员修改用户状态

    private static final long serialVersionUID = 1L;
}

