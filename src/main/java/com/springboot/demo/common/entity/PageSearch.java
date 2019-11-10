package com.springboot.demo.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedHashMap;

/**
 * @author Jim
 * 分页搜索条件
 */
@ApiModel
@Data
public class PageSearch {

    /**
     * 搜索条件
     */
    @ApiModelProperty(value = "搜索条件", position = 1)
    private LinkedHashMap<String, Object> where;

    /**
     * 排序条件
     */
    @ApiModelProperty(value = "排序条件", position = 2)
    private LinkedHashMap<String, String> orderBy;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", position = 3, example = "1")
    private int currentPage =1;

    /**
     * 页大小
     */
    @ApiModelProperty(value = "页大小", position = 3, example = "10")
    private int pageSize = 10;

}
