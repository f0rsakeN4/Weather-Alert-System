package com.f0rsaken.weatheralert.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 提醒事件实体类
 *
 * @Author f0rsaken
 * @Date 2024/10/29
 */

@Data
@TableName("alert_event") // 确保与数据库表名一致
public class AlertEvent implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "alert_type")
    private String alertType;

    /**
     * 温度提醒阈值
     */
    private BigDecimal threshold;

    /**
     * 0关闭，1开启
     */
    private int status;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(value = "updated_time")
    private LocalDateTime updatedTime;
}
