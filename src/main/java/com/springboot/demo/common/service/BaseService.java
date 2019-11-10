package com.springboot.demo.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.demo.common.entity.PageSearch;

import java.io.Serializable;

/**
 * @author Jim
 */
public interface BaseService<T> extends IService<T> {
    /**
     * 创建数据
     * id由后端生成
     * 注解TableId的字段设为null，而后根据TableId.type()生成
     *
     * @param t 实体
     * @return 成功与否
     */
    boolean insert(T t);

    /**
     * 更新数据
     * 根据id选择性更新
     * id支持String类型和Long类型
     *
     * @param id      id
     * @param version 版本号
     * @param t       数据
     * @return 成功与否
     */
    Boolean update(Serializable id, Long version, T t);

    /**
     * 分页查询
     *
     * @param currentPage  当前页
     * @param pageSize     页大小
     * @param queryWrapper 查询参数
     * @return page
     */
    IPage<T> pageSearch(int currentPage, int pageSize, QueryWrapper<T> queryWrapper);

    /**
     * 分页查询
     *
     * @param pageSearch 查询参数
     * @return page
     */
    IPage<T> pageSearch(PageSearch pageSearch);

    /**
     * @return T
     */
    Class getEntityClass();
}

