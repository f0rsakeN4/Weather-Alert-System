package com.f0rsaken.weatheralert.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatis Plus 元数据处理器
 *
 * @Author f0rsaken
 * @Date 2024/10/24
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, LocalDateTime.now()); // 插入时填充
        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now()); // 插入时填充
        this.strictInsertFill(metaObject, "verificationCodeCreatedTime", LocalDateTime.class, LocalDateTime.now()); // 插入时填充
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now()); // 更新时填充
    }
}
