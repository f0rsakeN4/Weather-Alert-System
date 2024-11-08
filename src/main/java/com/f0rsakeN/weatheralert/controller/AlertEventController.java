package com.f0rsakeN.weatheralert.controller;

import com.f0rsakeN.weatheralert.common.BaseResponse;
import com.f0rsakeN.weatheralert.common.ResultUtils;
import com.f0rsakeN.weatheralert.model.dto.AlertEventDTO;
import com.f0rsakeN.weatheralert.model.vo.AlertEventVO;
import com.f0rsakeN.weatheralert.service.AlertEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 提醒事件控制层
 *
 * @Author f0rsakeN
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
    public BaseResponse<String> createAlertEvent(@RequestBody AlertEventDTO alertEventDTO, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId"); // 从 JWT 中获取当前用户的 userId
        alertEventDTO.setUserId(userId); // 将 userId 设置到事件中
        alertEventService.createAlertEvent(alertEventDTO);
        return ResultUtils.success("提醒事件设置成功");
    }

    @ApiOperation("删除提醒事件")
    @DeleteMapping("/delete/{alertEventId}")
    public BaseResponse<String> deleteAlertEvent(@PathVariable Long alertEventId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        alertEventService.deleteAlertEvent(alertEventId, userId);
        return ResultUtils.success("提醒事件已删除");
    }

    @ApiOperation("打开提醒事件")
    @PostMapping("/open/{alertEventId}")
    public BaseResponse<String> openAlertEvent(@PathVariable Long alertEventId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        alertEventService.openAlertEvent(alertEventId, userId);
        return ResultUtils.success("提醒事件已开启");
    }

    @ApiOperation("关闭提醒事件")
    @PostMapping("/close/{alertEventId}")
    public BaseResponse<String> closeAlertEvent(@PathVariable Long alertEventId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        alertEventService.closeAlertEvent(alertEventId, userId);
        return ResultUtils.success("提醒事件已关闭");
    }

    @ApiOperation("获取提醒事件")
    @GetMapping("/{alertEventId}")
    public BaseResponse<AlertEventVO> getAlertEvent(@PathVariable Long alertEventId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        AlertEventVO alertEventVO = alertEventService.getAlertEvent(alertEventId, userId);
        return ResultUtils.success(alertEventVO);
    }

    @ApiOperation("获取用户提醒事件列表")
    @GetMapping("/list")
    public BaseResponse<List<AlertEventVO>> getAlertEventList(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<AlertEventVO> alertEventList = alertEventService.getAlertEventList(userId);
        return ResultUtils.success(alertEventList);
    }

}
