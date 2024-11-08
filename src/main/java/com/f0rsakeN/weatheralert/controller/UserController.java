package com.f0rsakeN.weatheralert.controller;

import com.f0rsakeN.weatheralert.Utils.JwtUtil;
import com.f0rsakeN.weatheralert.common.BaseResponse;
import com.f0rsakeN.weatheralert.common.ResultUtils;
import com.f0rsakeN.weatheralert.model.dto.user.UserLoginDTO;
import com.f0rsakeN.weatheralert.model.dto.user.UserRegisterDTO;
import com.f0rsakeN.weatheralert.service.UserService;
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
 * @Author f0rsakeN
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

    @Autowired
    private JwtUtil jwtUtil;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse<Long> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        Long userId = userService.register(userRegisterDTO);
        return ResultUtils.success(userId);
    }

    @GetMapping("/verify")
    public BaseResponse<Boolean> verifyEmail(@RequestParam String verificationCode) {
        userService.verifyEmail(verificationCode);
        return ResultUtils.success(true);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        // 验证用户并获取用户 ID
        Long userId = userService.login(userLoginDTO);
        // 生成JWT
        String token = jwtUtil.generateToken(userId);
        return ResultUtils.success(token);
    }

}
