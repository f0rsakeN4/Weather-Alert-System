package com.f0rsakeN.weatheralert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.f0rsakeN.weatheralert.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
