package com.f0rsaken.weatheralert.model.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户注册/登录返回类
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
@Data
public class UserRegisterVO implements Serializable {

    private String username;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updatedTime;

    private static final long serialVersionUID = 1L;

}
