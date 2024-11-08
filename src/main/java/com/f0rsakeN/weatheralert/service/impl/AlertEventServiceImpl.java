package com.f0rsakeN.weatheralert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f0rsakeN.weatheralert.exception.UserException;
import com.f0rsakeN.weatheralert.mapper.AlertEventMapper;
import com.f0rsakeN.weatheralert.model.dto.AlertEventDTO;
import com.f0rsakeN.weatheralert.model.entity.AlertEvent;
import com.f0rsakeN.weatheralert.model.vo.AlertEventVO;
import com.f0rsakeN.weatheralert.service.AlertEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.f0rsakeN.weatheralert.common.ErrorCode.*;

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
    public void deleteAlertEvent(Long alertEventId, Long userId) {
        AlertEvent alertEvent = alertEventMapper.selectById(alertEventId);
        if (alertEvent != null && alertEvent.getUserId().equals(userId)) {
            alertEventMapper.deleteById(alertEventId);
        } else {
            throw new UserException(UNAUTHORIZED.getCode(), UNAUTHORIZED.getMessage());
        }
    }

    @Override
    public void openAlertEvent(Long alertEventId, Long userId) {
        AlertEvent alertEvent = alertEventMapper.selectById(alertEventId);
        if (alertEvent != null && alertEvent.getUserId().equals(userId)) {
            alertEvent.setStatus(1);
            this.updateById(alertEvent);
        } else {
            throw new UserException(UNAUTHORIZED.getCode(), UNAUTHORIZED.getMessage());
        }
    }

    @Override
    public void closeAlertEvent(Long alertEventId, Long userId) {
        AlertEvent alertEvent = alertEventMapper.selectById(alertEventId);
        if (alertEvent != null && alertEvent.getUserId().equals(userId)) {
            alertEvent.setStatus(0);
            this.updateById(alertEvent);
        } else {
            throw new UserException(UNAUTHORIZED.getCode(), UNAUTHORIZED.getMessage());
        }
    }

    @Override
    public AlertEventVO getAlertEvent(Long alertEventId, Long userId) {
        AlertEvent alertEvent = alertEventMapper.selectById(alertEventId);
        if (alertEvent != null && alertEvent.getUserId().equals(userId)) {
            AlertEventVO alertEventVO = new AlertEventVO();
            BeanUtils.copyProperties(alertEvent, alertEventVO);

            return alertEventVO;
        } else {
            throw new UserException(UNAUTHORIZED.getCode(), UNAUTHORIZED.getMessage());
        }
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
