package com.f0rsaken.weatheralert.controller;

import com.f0rsaken.weatheralert.common.BaseResponse;
import com.f0rsaken.weatheralert.common.ResultUtils;
import com.f0rsaken.weatheralert.model.dto.AlertEventDTO;
import com.f0rsaken.weatheralert.model.vo.AlertEventVO;
import com.f0rsaken.weatheralert.service.AlertEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提醒事件控制层
 *
 * @Author f0rsaken
 * @Date 2024/10/29
 */

@Slf4j
@Api(tags = "提醒事件接口")
@RestController
@RequestMapping("/alertEvent")
@Validated
public class AlertEventController {

    @Autowired
    private AlertEventService alertEventService;

    @ApiOperation("设置提醒事件")
    @PostMapping("/create")
    public BaseResponse<String> createAlertEvent(@RequestBody AlertEventDTO alertEventDTO) {
        alertEventService.createAlertEvent(alertEventDTO);
        return ResultUtils.success("提醒事件设置成功");
    }

    @ApiOperation("删除提醒事件")
    @DeleteMapping("/delete/{id}")
    public BaseResponse<String> deleteAlertEvent(@PathVariable Long id) {
        alertEventService.deleteAlertEvent(id);
        return ResultUtils.success("提醒事件删除成功");
    }

    @ApiOperation("打开提醒事件")
    @PostMapping("/open/{id}")
    public BaseResponse<String> openAlertEvent(@PathVariable Long id) {
        alertEventService.openAlertEvent(id);
        return ResultUtils.success("提醒事件已开启");
    }

    @ApiOperation("关闭提醒事件")
    @PostMapping("/close/{id}")
    public BaseResponse<String> closeAlertEvent(@PathVariable Long id) {
        alertEventService.closeAlertEvent(id);
        return ResultUtils.success("提醒事件已关闭");
    }

    @ApiOperation("获取提醒事件")
    @GetMapping("/{id}")
    public BaseResponse<AlertEventVO> getAlertEvent(@PathVariable Long id) {
        AlertEventVO alertEventVO = alertEventService.getAlertEvent(id);
        return ResultUtils.success(alertEventVO);
    }

    @ApiOperation("获取用户提醒事件列表")
    @GetMapping("/list/{userId}")
    public BaseResponse<List<AlertEventVO>> getAlertEventList(@PathVariable Long userId) {
        List<AlertEventVO> alertEventList = alertEventService.getAlertEventList(userId);
        return ResultUtils.success(alertEventList);
    }

}
