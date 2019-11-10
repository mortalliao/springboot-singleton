package com.springboot.demo.auth.shiro.provider.impl;

import com.springboot.demo.auth.entity.vo.Account;
import com.springboot.demo.auth.service.AccountService;
import com.springboot.demo.auth.shiro.provider.AccountProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jim
 */
@Service("AccountProvider")
public class AccountProviderImpl implements AccountProvider {

    @Autowired
    private AccountService accountService;

    @Override
    public Account loadAccount(String appId) {
        return accountService.loadAccount(appId);
    }
}
