package com.springboot.demo.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.common.entity.BaseEntity;
import com.springboot.demo.common.entity.PageSearch;
import com.springboot.demo.common.service.BaseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Optional;

/**
 * @author Jim
 */
@Slf4j
public class BaseController<T extends BaseEntity<T>> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    private BaseService<T> baseService;

    /**
     * 保存 单资源
     *
     * @param entity        实体
     * @param bindingResult hibernate validator
     * @return R
     */
    @ApiOperation(value = "保存 单资源", notes = "保存单资源，返回保存的数据")
    @PostMapping()
    public R insert(@RequestBody @Valid T entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.failed(Optional.ofNullable(bindingResult.getFieldError())
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .orElse("数据不符合要求!"));
        }

        if (baseService.save(entity)) {
            return R.ok(entity);
        }
        return R.failed("保存失败");
    }

    /**
     * 获取 单资源带id
     *
     * @param id id
     * @return Result
     */
    @ApiOperation(value = "获取 单资源带id")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    @GetMapping("/{id}")
    public R select(@PathVariable("id") Serializable id) {
        T entity = baseService.getById(id);
        return entity != null ? R.ok(entity) : R.failed("id为" + id + "的资源不存在");
    }

    /**
     * 删除 单资源带id
     *
     * @param id id
     * @return Result
     */
    @ApiOperation(value = "删除 单资源带id", notes = "返回删除之前的数据")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Serializable id) {
        T selectEntity = baseService.getById(id);
        if (selectEntity == null) {
            return R.failed("id为" + id + "的资源不存在");
        }

        if (baseService.removeById(id)) {
            return R.ok(selectEntity);
        }
        return R.failed("删除失败");
    }

    /**
     * 更新 单资源带id
     *
     * @param id id
     * @return Result
     */
    @ApiOperation(value = "更新 单资源带id", notes = "返回更新之前的数据")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    @PutMapping("/{id}")
    public R update(@PathVariable("id") Serializable id, @RequestBody T entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.failed(Optional.ofNullable(bindingResult.getFieldError())
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .orElse("数据不符合要求!"));
        }

        T selectEntity = baseService.getById(id);
        if (selectEntity == null) {
            return R.failed("id为" + id + "的资源不存在");
        }

        if (baseService.update(id, selectEntity.getVersion(), entity)) {
            return R.ok(selectEntity);
        }
        return R.failed("更新失败");
    }

    /**
     * 获取 多资源 分页
     *
     * @param currentPage 当前页
     * @param pageSize    页大小
     * @return Result
     */
    @ApiOperation(value = "获取 多资源 分页", notes = "当前页默认第1页，页大小默认10", response = R.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = false, paramType = "form", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = false, paramType = "form", dataType = "int")
    })
    @GetMapping()
    public R page(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        IPage<T> entityPage = baseService.page(new Page<>(currentPage, pageSize),
                new QueryWrapper<T>().orderByDesc(BaseEntity.UPDATE_TIME));
        return R.ok(entityPage);
    }

    /**
     * 获取 多资源 分页查询
     *
     * @param currentPage 当前页
     * @param pageSize    页大小
     * @param pageSearch  分页查询对象
     * @return Result
     */
    @ApiOperation(value = "获取 多资源 分页查询", notes = "当前页默认第1页，页大小默认10", response = R.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = false, paramType = "form", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = false, paramType = "form", dataType = "int")
    })
    @PostMapping("/search")
    public R pageSearch(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                        @RequestBody(required = false) PageSearch pageSearch) {
        if (null != pageSearch) {
            pageSearch.setCurrentPage(currentPage);
            pageSearch.setPageSize(pageSize);
        }
        return R.ok(baseService.pageSearch(pageSearch));
    }

    /**
     * 获取 多资源 分页查询
     *
     * @param pageSearch 分页查询对象
     * @return Result
     */
    @ApiOperation(value = "获取 多资源 分页查询", notes = "当前页默认第1页，页大小默认10", response = R.class)
    @PostMapping("/page-search")
    public R pageSearch(@RequestBody(required = false) PageSearch pageSearch) {
        return R.ok(baseService.pageSearch(pageSearch));
    }

}

