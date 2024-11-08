package com.f0rsakeN.weatheralert.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 邮件工具类
 *
 * @Author f0rsakeN
 * @Date 2024/10/24
 */

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2967268931@qq.com"); // 确保与 spring.mail.username 配置一致
        message.setTo(to);
        message.setSubject("邮箱验证");
        message.setText("请点击以下链接进行邮箱验证：\n" +
                "http://localhost:8080/api/user/verify?verificationCode=" + verificationCode);
        mailSender.send(message);
    }
}
