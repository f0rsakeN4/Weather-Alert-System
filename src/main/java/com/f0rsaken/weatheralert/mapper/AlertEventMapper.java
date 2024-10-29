package com.f0rsaken.weatheralert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.f0rsaken.weatheralert.model.entity.AlertEvent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlertEventMapper extends BaseMapper<AlertEvent> {
}
