package com.springboot.demo.auth.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.springboot.demo.auth.entity.bo.AuthUser;
import com.springboot.demo.auth.entity.vo.Message;
import com.springboot.demo.auth.service.AccountService;
import com.springboot.demo.auth.service.UserService;
import com.springboot.demo.auth.support.factory.LogTaskFactory;
import com.springboot.demo.auth.support.manager.LogExeManager;
import com.springboot.demo.auth.utils.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * post新增,get读取,put完整更新,patch部分更新,delete删除
 *
 * @author Jim
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BaseAction{

    private static final String STR_USERNAME = "username";
    private static final String STR_REALNAME = "realName";
    private static final String STR_AVATAR = "avatar";
    private static final String STR_PHONE = "phone";
    private static final String STR_EMAIL = "email";
    private static final String STR_SEX = "sex";
    private static final String STR_WHERE = "createWhere";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    /**
     * description 登录签发 JWT ,这里已经在 passwordFilter 进行了登录认证
     *
     * @param appId 1
     * @return Message
     */
    @ApiOperation(value = "用户登录", notes = "POST用户登录签发JWT")
    @PostMapping("/login")
    public Message accountLogin(@RequestParam("appId") String appId, HttpServletRequest request) {

        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
        String jwt = accountService.issueJwt(appId);
        AuthUser authUser = userService.getUserByAppId(appId);
        authUser.setPassword(null);
        authUser.setSalt(null);

        LogExeManager.getInstance()
                .executeLogTask(LogTaskFactory.loginLog(appId, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "登录成功"));

        return new Message().ok(1003, "issue jwt success").addData("jwt", jwt).addData("user", authUser);
    }

    /**
     * description 用户账号的注册
     *
     * @param request 1
     * @return Message
     */
    @ApiOperation(value = "用户注册", notes = "POST用户注册")
    @PostMapping("/register")
    public R accountRegister(@RequestBody AuthUser authUser, BindingResult bindingResult,
                             HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return R.failed(Optional.ofNullable(bindingResult.getFieldError())
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .orElse("数据不符合要求!"));
        }

        if (accountService.isAccountExistByUid(authUser.getUid())) {
            // 账户已存在
            return R.failed("账户已存在");
        }

        // 从Redis取出密码传输加密解密秘钥
        String tokenKey = redisTemplate.opsForValue()
                .get("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase() + authUser.getUserKey());
        String realPassword = AesUtil.aesDecode(authUser.getPassword(), tokenKey);

        String salt = CommonUtil.getRandomString(6);
        // 存储到数据库的密码为 MD5(原密码+盐值)
        authUser.setPassword(Md5Util.md5(realPassword + salt));
        authUser.setSalt(salt);
        authUser.setCreateTime(new Date());
//        if (!StringUtils.isEmpty(params.get(STR_USERNAME))) {
//            authUser.setUsername(params.get(STR_USERNAME));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_REALNAME))) {
//            authUser.setRealName(params.get(STR_REALNAME));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_AVATAR))) {
//            authUser.setAvatar(params.get(STR_AVATAR));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_PHONE))) {
//            authUser.setPhone(params.get(STR_PHONE));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_EMAIL))) {
//            authUser.setEmail(params.get(STR_EMAIL));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_SEX))) {
//            authUser.setSex(Byte.valueOf(params.get(STR_SEX)));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_WHERE))) {
//            authUser.setCreateWhere(Byte.valueOf(params.get(STR_WHERE)));
//        }
        authUser.setStatus((byte) 1);

        if (accountService.registerAccount(authUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(authUser.getUid(), IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "注册成功"));
            return R.ok("注册成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(authUser.getUid(), IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
            return R.failed("注册失败");
        }
    }

}
