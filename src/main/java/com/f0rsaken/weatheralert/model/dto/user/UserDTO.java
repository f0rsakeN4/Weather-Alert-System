package com.f0rsaken.weatheralert.model.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
@Data
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String phone;

    /**
     * 用户状态，0表示禁用，1表示启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updatedTime;

    private LocalDateTime verificationCodeCreatedTime;

    private static final long serialVersionUID = 1L;
}
