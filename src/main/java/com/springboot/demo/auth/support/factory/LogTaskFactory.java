package com.springboot.demo.auth.support.factory;

import com.springboot.demo.auth.dao.AuthOperationLogMapper;
import com.springboot.demo.auth.entity.bo.AuthAccountLog;
import com.springboot.demo.auth.entity.bo.AuthOperationLog;
import com.springboot.demo.auth.service.AuthAccountLogService;
import com.springboot.demo.auth.support.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 日志操作任务工厂
 *
 * @author Jim
 */
@Slf4j
public class LogTaskFactory {

    private static AuthOperationLogMapper operationLogMapper = SpringContextHolder.getBean(AuthOperationLogMapper.class);
    private static AuthAccountLogService authAccountLogService = SpringContextHolder.getBean(AuthAccountLogService.class);

    private LogTaskFactory() {

    }

    public static TimerTask loginLog(String userId, String ip, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthAccountLog accountLog = LogFactory.createAccountLog(userId, "用户登录日志", ip, succeed, message);
                    authAccountLogService.insertSelective(accountLog);
                } catch (Exception e) {
                    log.error("写入用户登录日志异常", e.getCause().getMessage());
                }
            }
        };
    }

    public static TimerTask exitLog(String userId, String ip, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthAccountLog accountLog = LogFactory.createAccountLog(userId, "用户退出日志", ip, succeed, message);
                    authAccountLogService.insertSelective(accountLog);
                } catch (Exception e) {
                    log.error("写入用户退出日志异常", e.getCause().getMessage());
                }
            }
        };
    }

    public static TimerTask registerLog(String userId, String ip, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthAccountLog accountLog = LogFactory.createAccountLog(userId, "用户注册日志", ip, succeed, message);
                    authAccountLogService.insertSelective(accountLog);
                } catch (Exception e) {
                    log.error("写入用户注册日志异常", e.getCause().getMessage());
                }
            }
        };
    }

    public static TimerTask bussinssLog(String userId, String api, String method, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthOperationLog operationLog = LogFactory.createOperationLog(userId, "业务操作日志", api, method, succeed, message);
                    operationLogMapper.insertSelective(operationLog);
                } catch (Exception e) {
                    log.error("写入业务操作日志异常", e.getCause().getMessage());
                }
            }
        };
    }

    public static TimerTask exceptionLog(String userId, String api, String method, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthOperationLog exceptionLog = LogFactory.createOperationLog(userId, "业务异常日志", api, method, succeed, message);
                    operationLogMapper.insertSelective(exceptionLog);
                } catch (Exception e) {
                    log.error("写入业务异常日志异常", e.getCause().getMessage());
                }
            }
        };
    }

}
