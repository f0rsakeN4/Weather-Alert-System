package com.f0rsaken.weatheralert.model.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("users")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "updated_time")
    private LocalDateTime updatedTime;

    /**
     * 邮箱验证码
     */
    @TableField(value = "verification_code", fill = FieldFill.INSERT)
    private String verificationCode;

    /**
     * 最后更新时间
     */
    @TableField(value = "verification_code_created_time", fill = FieldFill.INSERT)
    private LocalDateTime verificationCodeCreatedTime;

    private static final long serialVersionUID = 1L;
}
