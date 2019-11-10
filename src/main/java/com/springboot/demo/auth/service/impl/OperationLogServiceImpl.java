package com.springboot.demo.auth.service.impl;

import com.springboot.demo.auth.dao.AuthOperationLogMapper;
import com.springboot.demo.auth.entity.bo.AuthOperationLog;
import com.springboot.demo.auth.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Jim
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    AuthOperationLogMapper authOperationLogMapper;

    @Override
    public List<AuthOperationLog> getOperationList() {
        return authOperationLogMapper.selectOperationLogList();
    }
}
