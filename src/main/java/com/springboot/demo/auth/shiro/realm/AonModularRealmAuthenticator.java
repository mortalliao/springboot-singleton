package com.springboot.demo.auth.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 验证用户帐号，是ShiroAPI中身份验证核心的入口点
 * 接口中声明的authenticate方法就是用来实现认证逻辑的。
 * <p>
 * <p>
 * Authenticator 实现认证逻辑
 * 如果验证成功，将返回AuthenticationInfo验证信息, 此信息中包含了身份及凭证;
 * 如果验证失败将抛出相应的 AuthenticationException 实现
 * <p>
 * ModularRealmAuthenticator认证策略接口，自定义认证策略时只需要继承该类
 *
 *
 * @author Jim
 */
public class AonModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();
        List<Realm> realms = this.getRealms()
                .stream()
                .filter(realm -> realm.supports(authenticationToken))
                .collect(toList());
        return realms.size() == 1 ? this.doSingleRealmAuthentication(realms.iterator().next(), authenticationToken)
                : this.doMultiRealmAuthentication(realms, authenticationToken);
    }
}
