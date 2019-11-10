package com.springboot.demo.auth.entity.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * user
 *
 * @author Jim
 */
@TableName(value = "auth_user")
@Data
@ApiModel(description = "用户信息")
public class AuthUser {

    @TableField("uid")
    @NotEmpty(message = "账户信息缺失")
    @ApiModelProperty("账户名称")
    private String uid;

    @TableField("username")
    @NotEmpty(message = "用户名缺失")
    @ApiModelProperty("用户名")
    private String username;

    @TableField("password")
    @NotEmpty(message = "密码缺失")
    @ApiModelProperty("密码")
    private String password;

    @TableField("salt")
    private String salt;

    @TableField("realName")
    private String realName;

    @TableField("avatar")
    private String avatar;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("sex")
    private Byte sex;

    @TableField("status")
    private Byte status;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("UPDATE_TIME")
    private Date updateTime;

    @TableField("CREATE_WHERE")
    private Byte createWhere;

    @NotEmpty(message = "tokenKey不能为空")
    @ApiModelProperty("tokenKey")
    @TableField(exist = false)
    private String userKey;

}