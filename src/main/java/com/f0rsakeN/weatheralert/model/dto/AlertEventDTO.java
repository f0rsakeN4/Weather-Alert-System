package com.f0rsakeN.weatheralert.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 提醒事件DTO
 *
 * @Author f0rsakeN
 * @Date 2024/10/29
 */

@Data
public class AlertEventDTO implements Serializable {

    @NotNull(message = "提醒类型不能为空")
    private String alertType; // 提醒类型，例如温度变化、雨雪、雾霾

    private BigDecimal threshold; // 阈值

    @NotNull(message = "用户id不能为空")
    private Long userId; // 用户 ID
}
