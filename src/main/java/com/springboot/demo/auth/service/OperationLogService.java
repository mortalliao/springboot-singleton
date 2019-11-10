package com.springboot.demo.auth.service;

import com.springboot.demo.auth.entity.bo.AuthOperationLog;

import java.util.List;

/**
 * @author Jim
 */
public interface OperationLogService {

    /**
     * description TODO
     *
     * @return java.util.List<AuthOperationLog>
     */
    List<AuthOperationLog> getOperationList();
}
