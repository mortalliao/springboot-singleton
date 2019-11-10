package com.springboot.demo.auth.support;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.springboot.demo.auth.entity.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局的的异常拦截器（拦截所有的控制器）
 * （带有@RequestMapping注解的方法上都会拦截）
 *
 * @author Jim
 */
@Slf4j
@RestControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    /**
     * description 拦截操作数据库异常
     *
     * @param e 1
     * @return Message
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Message sqlException(DataAccessException e) {
        log.error("数据操作异常:", e);
        final Throwable cause = e.getCause();
        // 之后判断cause类型进一步记录日志处理
        if (cause instanceof MySQLIntegrityConstraintViolationException) {
            return new Message().error(1111, "数据冲突操作失败");
        }
        return new Message().error(1111, "服务器开小差");
    }

    /**
     * description 拦截未知的运行时异常
     *
     * @param e 1
     * @return Message
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public Message notFoundException(RuntimeException e) {
        log.error("运行时异常:", e);
        return new Message().error(1111, "服务器开小差");
    }
}
