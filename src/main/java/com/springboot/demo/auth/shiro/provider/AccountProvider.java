package com.springboot.demo.auth.shiro.provider;

import com.springboot.demo.auth.entity.vo.Account;

/**
 * 数据库用户密码账户提供
 *
 * @author Jim
 */
public interface AccountProvider {

    /**
     * description 数据库用户密码账户提供
     *
     * @param appId 1
     * @return Account
     */
    Account loadAccount(String appId);

}
