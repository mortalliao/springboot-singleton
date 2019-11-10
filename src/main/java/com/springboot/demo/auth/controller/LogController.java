package com.springboot.demo.auth.controller;

import com.springboot.demo.auth.service.AuthAccountLogService;
import com.springboot.demo.auth.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jim
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseAction {

    @Autowired
    AuthAccountLogService accountLogService;

    @Autowired
    OperationLogService operationLogService;

//    @SuppressWarnings("unchecked")
//    @ApiOperation(value = "获取日志记录", httpMethod = "GET")
//    @RequestMapping("/accountLog/{currentPage}/{pageSize}")
//    public Message getAccountLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
//        PageHelper.startPage(currentPage, pageSize);
//        List<AuthAccountLog> accountLogs = accountLogService.getAccountLogList();
//        PageInfo pageInfo = new PageInfo(accountLogs);
//        return new Message().ok(6666, "return accountLogs success").addData("data", pageInfo);
//    }

//    @SuppressWarnings("unchecked")
//    @ApiOperation(value = "获取用户操作api日志列表", httpMethod = "GET")
//    @RequestMapping("/operationLog/{currentPage}/{pageSize}")
//    public Message getOperationLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
//        PageHelper.startPage(currentPage, pageSize);
//        List<AuthOperationLog> authOperationLogs = operationLogService.getOperationList();
//        PageInfo pageInfo = new PageInfo(authOperationLogs);
//        return new Message().ok(6666, "return operationLogList success").addData("data", pageInfo);
//    }
}
