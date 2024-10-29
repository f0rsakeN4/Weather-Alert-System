package com.f0rsaken.weatheralert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f0rsaken.weatheralert.Utils.EmailUtil;
import com.f0rsaken.weatheralert.exception.UserException;
import com.f0rsaken.weatheralert.mapper.UserMapper;
import com.f0rsaken.weatheralert.model.dto.user.UserLoginDTO;
import com.f0rsaken.weatheralert.model.dto.user.UserRegisterDTO;
import com.f0rsaken.weatheralert.model.entity.User;
import com.f0rsaken.weatheralert.model.vo.user.UserResponseVO;
import com.f0rsaken.weatheralert.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.f0rsaken.weatheralert.common.ErrorCode.*;

/**
 * 用户接口实现类
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public UserResponseVO register(UserRegisterDTO userRegisterDTO) {
        // 检查用户名是否已存在
        User existingUser = findByUserName(userRegisterDTO.getUsername());
        if (existingUser != null) {
            throw new UserException(USER_ALREADY_EXISTS.getCode(), USER_ALREADY_EXISTS.getMessage());
        }


        // 创建新用户
        User newUser = new User();
        BeanUtils.copyProperties(userRegisterDTO, newUser, "password");
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword())); // 加密密码
        // 生成验证代码（你可以根据需要自定义验证逻辑）
        String verificationCode = UUID.randomUUID().toString();
        newUser.setVerificationCode(verificationCode);
        newUser.setStatus(0); // 启用状态
        this.save(newUser);

        // 发送验证邮件
        emailUtil.sendVerificationEmail(userRegisterDTO.getEmail(), verificationCode);

        UserResponseVO userResponseVO = new UserResponseVO();
        BeanUtils.copyProperties(newUser, userResponseVO);
        if (userResponseVO == null) {
            throw new UserException(USER_REGISTER_FAILED.getCode(), USER_REGISTER_FAILED.getMessage());
        }

        return userResponseVO;

    }

    @Override
    public Boolean verifyEmail(String verificationCode) {
        // 查找数据库中是否存在与 code 匹配的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("verification_code", verificationCode);
        User user = userMapper.selectOne(queryWrapper);

        if (user != null) {
            // 检查验证代码是否过期（24小时有效）
            LocalDateTime verificationCodeCreatedTime = user.getVerificationCodeCreatedTime();
            if (verificationCodeCreatedTime.plusMinutes(5).isAfter(LocalDateTime.now())) {
                // 验证成功
                user.setStatus(1);
                this.updateById(user);
                return true;
            } else {
                throw new UserException(VERIFICATION_CODE_EXPIRED.getCode(), VERIFICATION_CODE_EXPIRED.getMessage());
            }
        } else {
            throw new UserException(VERIFICATION_CODE_ERROR.getCode(), VERIFICATION_CODE_ERROR.getMessage());
        }
    }

    @Override
    public UserResponseVO login(UserLoginDTO userLoginDTO) {
        // 根据用户名查找用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userLoginDTO.getUsername());
        User user = userMapper.selectOne(queryWrapper);

        // 检查用户是否存在以及密码是否匹配
        if (user == null || !passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new UserException(USER_LOGIN_FAILED.getCode(), USER_LOGIN_FAILED.getMessage());
        }
        // 检查用户是否激活
        if (user.getStatus() == 0) {
            throw new UserException(INACTIVE_USER.getCode(), INACTIVE_USER.getMessage());
        }

        UserResponseVO userResponseVO = new UserResponseVO();
        BeanUtils.copyProperties(user, userResponseVO);
        return userResponseVO;

    }

    @Override
    public User findByUserName(String username) {
        // 根据用户名查找用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);

        return user;
    }
}
