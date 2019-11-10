package com.springboot.demo.auth.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Jim
 */
@Data
public class MenuTreeNode extends BaseTreeNode {

    private String code;

    private String name;

    private String uri;

    private Short type;

    private String method;

    private String icon;

    private Short status;

    private Date createTime;

    private Date updateTime;

}
