package com.f0rsakeN.weatheralert.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 提醒事件返回类
 *
 * @Author f0rsakeN
 * @Date 2024/10/29
 */

@Data
public class AlertEventVO {

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

}
