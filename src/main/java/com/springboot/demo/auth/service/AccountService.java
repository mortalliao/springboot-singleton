package com.springboot.demo.auth.service;

import com.springboot.demo.auth.entity.bo.AuthUser;
import com.springboot.demo.auth.entity.vo.Account;

/**
 * @author Jim
 */
public interface AccountService {

    /**
     * 查询权限用户
     *
     * @param appId 1
     * @return Account
     */
    Account loadAccount(String appId);

    /**
     * 根据uid查询是否存在账户
     *
     * @param uid 1
     * @return boolean
     */
    boolean isAccountExistByUid(String uid);

    /**
     * 注册账户
     *
     * @param account 1
     * @return boolean
     */
    boolean registerAccount(AuthUser account);

    /**
     * 根据appId查询账户角色
     *
     * @param appId 1
     * @return java.lang.String
     */
    String loadAccountRole(String appId);

    /**
     * 签发JWT
     *
     * @param appId
     * @return
     */
    String issueJwt(String appId);
}
