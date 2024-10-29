package com.f0rsaken.weatheralert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f0rsaken.weatheralert.mapper.AlertEventMapper;
import com.f0rsaken.weatheralert.model.dto.AlertEventDTO;
import com.f0rsaken.weatheralert.model.entity.AlertEvent;
import com.f0rsaken.weatheralert.model.vo.AlertEventVO;
import com.f0rsaken.weatheralert.service.AlertEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AlertEventServiceImpl extends ServiceImpl<AlertEventMapper, AlertEvent> implements AlertEventService {

    @Autowired
    private AlertEventMapper alertEventMapper;

    @Override
    public void createAlertEvent(AlertEventDTO alertEventDTO) {
        AlertEvent alertEvent = new AlertEvent();
        BeanUtils.copyProperties(alertEventDTO, alertEvent);
        alertEvent.setStatus(1);
        alertEventMapper.insert(alertEvent);
    }

    @Override
    public void deleteAlertEvent(Long alertEventId) {
        alertEventMapper.deleteById(alertEventId);
    }

    @Override
    public void openAlertEvent(Long alertEventId) {
        AlertEvent alertEvent = alertEventMapper.selectById(alertEventId);
        alertEvent.setStatus(1);
    }

    @Override
    public void closeAlertEvent(Long alertEventId) {
        AlertEvent alertEvent = alertEventMapper.selectById(alertEventId);
        alertEvent.setStatus(0);
    }

    @Override
    public AlertEventVO getAlertEvent(Long alertEventId) {
        AlertEvent alertEvent = alertEventMapper.selectById(alertEventId);
        AlertEventVO alertEventVO = new AlertEventVO();
        BeanUtils.copyProperties(alertEvent, alertEventVO);

        return alertEventVO;
    }

    @Override
    public List<AlertEventVO> getAlertEventList(Long userId) {
        List<AlertEvent> alertEvents = alertEventMapper.selectList(new QueryWrapper<AlertEvent>().eq("user_id", userId));

        // 使用 BeanUtils 复制属性
        return alertEvents.stream()
                .map(alertEvent -> {
                    AlertEventVO alertEventVO = new AlertEventVO();
                    BeanUtils.copyProperties(alertEvent, alertEventVO); // 复制属性
                    return alertEventVO;
                })
                .collect(Collectors.toList());
    }


}
