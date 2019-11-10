package com.springboot.demo.auth.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author mortalliao
 * 返回状态码与信息
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusCodeEnum {
    /**
     * 服务器出现错误
     */
    ERROR(-1, "server error"),

    /**
     * 操作成功
     */
    SUCCESS(0, "success"),

    /**
     * 操作失败
     */
    FAILED(1, "failed"),

    /**
     *
     */
    ERROR_HTTP_MESSAGE_NOT_READABLE(4500, "请求的数据错误，请确认您的数据格式符合规范！"),

    /**
     * 违反数据库约束
     */
    ERROR_SQL_CONSTRAINT(3780, "ID冲突，请填入其它ID！(或其它要求唯一的属性冲突，请更换！)");

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 提示信息
     */
    private String message;

}
