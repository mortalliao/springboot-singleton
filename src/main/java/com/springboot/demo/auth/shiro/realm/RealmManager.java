package com.springboot.demo.auth.shiro.realm;

import com.springboot.demo.auth.shiro.matcher.JwtMatcher;
import com.springboot.demo.auth.shiro.matcher.PasswordMatcher;
import com.springboot.demo.auth.shiro.provider.AccountProvider;
import com.springboot.demo.auth.shiro.token.JwtToken;
import com.springboot.demo.auth.shiro.token.PasswordToken;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * realm管理器
 *
 * @author Jim
 */
@Component
public class RealmManager {

    private PasswordMatcher passwordMatcher;
    private AccountProvider accountProvider;

    private JwtMatcher jwtMatcher;

    @Autowired
    public RealmManager(AccountProvider accountProvider, PasswordMatcher passwordMatcher, JwtMatcher jwtMatcher) {
        this.passwordMatcher = passwordMatcher;
        this.accountProvider = accountProvider;
        this.jwtMatcher = jwtMatcher;
    }

    public List<Realm> initGetRealm() {
        List<Realm> realmList = new LinkedList<>();

        // ----- password
        PasswordRealm passwordRealm = new PasswordRealm();
        passwordRealm.setCredentialsMatcher(passwordMatcher);
        passwordRealm.setAccountProvider(accountProvider);
        passwordRealm.setAuthenticationTokenClass(PasswordToken.class);
        realmList.add(passwordRealm);

        // ----- jwt
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCredentialsMatcher(jwtMatcher);
        jwtRealm.setAuthenticationTokenClass(JwtToken.class);
        realmList.add(jwtRealm);

        return Collections.unmodifiableList(realmList);
    }
}
