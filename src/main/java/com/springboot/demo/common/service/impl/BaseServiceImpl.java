package com.springboot.demo.common.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.demo.common.constant.PageSearchConstant;
import com.springboot.demo.common.entity.BaseEntity;
import com.springboot.demo.common.entity.PageSearch;
import com.springboot.demo.common.service.BaseService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jim
 */
@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    public boolean insert(@NonNull T t) {
        ReflectionUtils.doWithFields(t.getClass(), field -> {
            field.setAccessible(true);
            field.set(t, null);
        }, field -> field.getAnnotation(TableId.class) != null);
        return super.save(t);
    }

    @Override
    public Boolean update(@NonNull Serializable id, @NonNull Long version, @NonNull T t) {
        ReflectionUtils.doWithFields(t.getClass(), field -> {
            field.setAccessible(true);
            if (field.getType() == Long.class) {
                field.set(t, Long.parseLong(id.toString()));
            } else if (field.getType() == String.class) {
                field.set(t, id);
            }
        }, field -> field.getAnnotation(TableId.class) != null);
        ReflectionUtils.doWithFields(t.getClass(), field -> {
            field.setAccessible(true);
            if (field.getType() == Long.class) {
                field.set(t, version);
            }
        }, field -> field.getAnnotation(Version.class) != null);
        return updateById(t);
    }

    @Override
    public IPage<T> pageSearch(int currentPage, int pageSize, QueryWrapper<T> queryWrapper) {
        Page<T> page = new Page<>(currentPage, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<T> pageSearch(@NotNull PageSearch pageSearch) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        searchConditions(pageSearch.getWhere(), queryWrapper);
        sortConditions(pageSearch.getOrderBy(), queryWrapper);
        return pageSearch(pageSearch.getCurrentPage(), pageSearch.getPageSize(), queryWrapper);
    }

    /**
     * 加载 排序条件
     *
     * @param orderBy      orderBy Map
     * @param queryWrapper queryWrapper
     */
    private void sortConditions(LinkedHashMap<String, String> orderBy, QueryWrapper<T> queryWrapper) {
        if (orderBy != null && !orderBy.isEmpty()) {
            String asc = "asc";
            orderBy.forEach((k, v) -> {
                if (k.trim().length() > 0 && v.trim().length() > 0) {
                    if (asc.equalsIgnoreCase(v.toLowerCase())) {
                        queryWrapper.orderByAsc(StringUtils.camelToUnderline(k));
                    } else {
                        queryWrapper.orderByDesc(StringUtils.camelToUnderline(k));
                    }
                }
            });
        } else {
            queryWrapper.orderByDesc(BaseEntity.UPDATE_TIME);
        }
    }

    /**
     * 加载 查询条件
     *
     * @param where        where Map
     * @param queryWrapper queryWrapper
     */
    private void searchConditions(Map<String, Object> where, QueryWrapper<T> queryWrapper) {
        if (where != null && !where.isEmpty()) {
            where.forEach((k, v) -> {
                if (isLoadCondition(PageSearchConstant.SEARCH_EQ, k, v)) {
                    queryWrapper.eq(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_EQ)[0]), v);
                } else if (isLoadCondition(PageSearchConstant.SEARCH_NE, k, v)) {
                    queryWrapper.ne(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_NE)[0]), v);
                } else if (isLoadCondition(PageSearchConstant.SEARCH_LT, k, v)) {
                    queryWrapper.lt(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_LT)[0]), v);
                } else if (isLoadCondition(PageSearchConstant.SEARCH_LE, k, v)) {
                    queryWrapper.le(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_LE)[0]), v);
                } else if (isLoadCondition(PageSearchConstant.SEARCH_GT, k, v)) {
                    queryWrapper.gt(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_GT)[0]), v);
                } else if (isLoadCondition(PageSearchConstant.SEARCH_GE, k, v)) {
                    queryWrapper.ge(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_GE)[0]), v);
                } else if (isLoadCondition(PageSearchConstant.SEARCH_LLIKE, k, v)) {
                    queryWrapper.likeLeft(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_LLIKE)[0]), String.valueOf(v));
                } else if (isLoadCondition(PageSearchConstant.SEARCH_RLIKE, k, v)) {
                    queryWrapper.likeRight(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_RLIKE)[0]), String.valueOf(v));
                } else if (isLoadCondition(PageSearchConstant.SEARCH_LIKE, k, v)) {
                    queryWrapper.like(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_LIKE)[0]), String.valueOf(v));
                } else if (isLoadCondition(PageSearchConstant.SEARCH_IN, k, v)) {
                    queryWrapper.in(StringUtils.camelToUnderline(k.split(PageSearchConstant.SEARCH_IN)[0]), String.valueOf(v));
                }
            });
        }
    }

    /**
     * 是否加载 查询条件
     *
     * @param conditionSuffix 查询条件前缀
     * @param k               key
     * @param v               value
     * @return boolean
     */
    private boolean isLoadCondition(String conditionSuffix, String k, Object v) {
        return k.endsWith(conditionSuffix) && null != v && v.toString().trim().length() > 0;
    }

    @Override
    public Class getEntityClass() {
        return (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

}
