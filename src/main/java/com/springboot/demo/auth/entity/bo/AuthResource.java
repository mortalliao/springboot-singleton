package com.springboot.demo.auth.entity.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 资源
 *
 * @author Jim
 */
@Data
@TableName("auth_resource")
public class AuthResource {

    @TableId("id")
    private Integer id;

    @TableField("CODE")
    private String code;

    @TableField("NAME")
    private String name;

    @TableField("PARENT_ID")
    private Integer parentId;

    private String uri;

    private Short type;

    private String method;

    private String icon;

    private Short status;

    private Date createTime;

    private Date updateTime;

}