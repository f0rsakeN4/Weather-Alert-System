package com.f0rsaken.weatheralert.controller;

import com.f0rsaken.weatheralert.common.BaseResponse;
import com.f0rsaken.weatheralert.common.ResultUtils;
import com.f0rsaken.weatheralert.model.dto.user.UserLoginDTO;
import com.f0rsaken.weatheralert.model.dto.user.UserRegisterDTO;
import com.f0rsaken.weatheralert.model.vo.user.UserResponseVO;
import com.f0rsaken.weatheralert.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户控制层
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
@Slf4j
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse<UserResponseVO> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        UserResponseVO userResponseVO = userService.register(userRegisterDTO);
        return ResultUtils.success(userResponseVO);
    }

    @GetMapping("/verify")
    public BaseResponse<Boolean> verifyEmail(@RequestParam String verificationCode) {
        userService.verifyEmail(verificationCode);
        return ResultUtils.success(true);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse<UserResponseVO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        UserResponseVO userResponseVO = userService.login(userLoginDTO);
        return ResultUtils.success(userResponseVO);
    }

}
