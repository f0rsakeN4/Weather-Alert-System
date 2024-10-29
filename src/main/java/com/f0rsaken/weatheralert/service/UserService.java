package com.f0rsaken.weatheralert.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.f0rsaken.weatheralert.model.dto.user.UserLoginDTO;
import com.f0rsaken.weatheralert.model.dto.user.UserRegisterDTO;
import com.f0rsaken.weatheralert.model.entity.User;
import com.f0rsaken.weatheralert.model.vo.user.UserResponseVO;

/**
 * 用户接口
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userRegisterDTO 用户注册信息
     * @return 注册成功的用户
     */
    UserResponseVO register(UserRegisterDTO userRegisterDTO);

    /**
     * 用户登录
     * @param userLoginDTO 用户登录信息
     * @return 登录成功的用户信息
     */
    UserResponseVO login(UserLoginDTO userLoginDTO);

    Boolean verifyEmail(String code);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUserName(String username);
}
