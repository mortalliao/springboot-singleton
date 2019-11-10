package com.springboot.demo.auth.service.impl;

import com.springboot.demo.auth.dao.AuthUserMapper;
import com.springboot.demo.auth.entity.bo.AuthUser;
import com.springboot.demo.auth.entity.vo.Account;
import com.springboot.demo.auth.service.AccountService;
import com.springboot.demo.auth.service.UserService;
import com.springboot.demo.auth.utils.JsonWebTokenUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Jim
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthUserMapper authUserMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Account loadAccount(String appId) throws DataAccessException {
        AuthUser user = authUserMapper.selectByUniqueKey(appId);
        return user != null ? new Account(user.getUsername(), user.getPassword(), user.getSalt()) : null;
    }

    @Override
    public boolean isAccountExistByUid(String uid) {
        AuthUser user = authUserMapper.selectByPrimaryKey(uid);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(AuthUser account) throws DataAccessException {

        // 给新用户授权访客角色
        userService.authorityUserRole(account.getUid(), 103);

        return authUserMapper.insertSelective(account) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public String loadAccountRole(String appId) throws DataAccessException {
        return authUserMapper.selectUserRoles(appId);
    }

    @Override
    public String issueJwt(String appId) {
        String roles = loadAccountRole(appId);
        //seconds为单位,10 hours
        long refreshPeriodTime = 36000L;
        String newJwt = JsonWebTokenUtil.issueJWT(UUID.randomUUID().toString(), appId,
                "token-server", refreshPeriodTime >> 1, roles, null, SignatureAlgorithm.HS512);
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        redisTemplate.opsForValue().set("JWT-SESSION-" + appId, newJwt, refreshPeriodTime, TimeUnit.SECONDS);
        return newJwt;
    }

}
