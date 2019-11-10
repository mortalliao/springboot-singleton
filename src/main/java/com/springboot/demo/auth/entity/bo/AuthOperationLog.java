package com.springboot.demo.auth.entity.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志
 *
 * @author Jim
 */
@Data
@TableName(value = "auth_operation_log")
public class AuthOperationLog {

    @TableId(value = "ID")
    private Integer id;

    @TableField("LOG_NAME")
    private String logName;

    @TableField("USER_ID")
    private String userId;

    @TableField("API")
    private String api;

    @TableField("METHOD")
    private String method;

    @TableField("SUCCEED")
    private Short succeed;

    @TableField("MESSAGE")
    private String message;

    @TableField("CREATE_TIME")
    private Date createTime;

}
