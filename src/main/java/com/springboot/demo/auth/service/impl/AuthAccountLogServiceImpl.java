package com.springboot.demo.auth.service.impl;

import com.springboot.demo.auth.dao.AuthAccountLogMapper;
import com.springboot.demo.auth.entity.bo.AuthAccountLog;
import com.springboot.demo.auth.service.AuthAccountLogService;
import com.springboot.demo.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 登录注册登出记录 服务实现类
 *
 * @author Jim
 */
@Service
public class AuthAccountLogServiceImpl extends BaseServiceImpl<AuthAccountLogMapper, AuthAccountLog> implements AuthAccountLogService {

    @Override
    public int insertSelective(AuthAccountLog authAccountLog) {
        return baseMapper.insert(authAccountLog);
    }
}
