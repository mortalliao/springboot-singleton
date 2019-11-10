package com.springboot.demo.auth.entity.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.demo.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 登录注册登出记录
 *
 * @author Jim
 */
@TableName("auth_account_log")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AuthAccountLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账户操作日志主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("用户账户操作日志主键")
    private Integer id;

    /**
     * 日志名称(login,register,logout)
     */
    @ApiModelProperty("日志名称(login,register,logout)")
    private String logName;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String userId;

    /**
     * 是否执行成功(0失败1成功)
     */
    @ApiModelProperty("是否执行成功(0失败1成功)")
    private Short succeed;

    /**
     * 登录ip
     */
    @ApiModelProperty("登录ip")
    private String ip;

    /**
     * 具体消息
     */
    @ApiModelProperty("具体消息")
    private String message;

    public static final String ID = "id";
    public static final String LOG_NAME = "log_name";
    public static final String USER_ID = "user_id";
    public static final String SUCCEED = "succeed";
    public static final String IP = "ip";
    public static final String MESSAGE = "message";

}
