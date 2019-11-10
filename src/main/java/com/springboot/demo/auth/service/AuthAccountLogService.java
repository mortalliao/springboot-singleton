package com.springboot.demo.auth.service;

import com.springboot.demo.auth.entity.bo.AuthAccountLog;
import com.springboot.demo.common.service.BaseService;
import org.springframework.dao.DataAccessException;

/**
 * 登录注册登出记录 服务类
 *
 * @author Jim
 * @since 2019-11-10
 */
public interface AuthAccountLogService extends BaseService<AuthAccountLog> {

    /**
     * description 插入日志到数据库
     *
     * @param authAccountLog 1
     * @return int
     * @throws DataAccessException when
     */
    int insertSelective(AuthAccountLog authAccountLog);
}
