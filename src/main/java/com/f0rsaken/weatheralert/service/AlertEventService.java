package com.f0rsaken.weatheralert.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.f0rsaken.weatheralert.model.dto.AlertEventDTO;
import com.f0rsaken.weatheralert.model.entity.AlertEvent;
import com.f0rsaken.weatheralert.model.vo.AlertEventVO;

import java.util.List;

/**
 * 提醒事件接口
 *
 * @Author f0rsaken
 * @Date 2024/10/29
 */
public interface AlertEventService extends IService<AlertEvent> {

    /**
     * 设置提醒事件
     * @param alertEventDTO
     */
    void createAlertEvent(AlertEventDTO alertEventDTO);

    /**
     * 删除提醒事件
     * @param alertEventId
     */
    void deleteAlertEvent(Long alertEventId);

    /**
     * 开启提醒事件
     * @param alertEventId
     */
    void openAlertEvent(Long alertEventId);

    /**
     * 关闭提醒时间
     * @param alertEventId
     */
    void closeAlertEvent(Long alertEventId);

    /**
     * 获取提醒事件
     * @param alertEventId
     * @return
     */
    AlertEventVO getAlertEvent(Long alertEventId);

    /**
     * 获取用户提醒事件列表
     * @param userId
     * @return
     */
    List<AlertEventVO> getAlertEventList(Long userId);
}
