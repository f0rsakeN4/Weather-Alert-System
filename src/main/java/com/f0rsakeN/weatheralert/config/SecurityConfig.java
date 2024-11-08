package com.f0rsakeN.weatheralert.config;

import com.f0rsakeN.weatheralert.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 安全配置
 *
 * @Author f0rsakeN
 * @Date 2024/10/30
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 禁用CSRF保护（开发阶段）
                .authorizeRequests()
                .antMatchers("/user/register", "/user/login").permitAll() // 放行注册和登录接口
                .antMatchers("/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs").permitAll() // 放行 Knife4j 相关路径
                .anyRequest().authenticated() // 其他请求需要认证
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // 添加 JWT 过滤器
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
