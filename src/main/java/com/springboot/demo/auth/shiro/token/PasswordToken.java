package com.springboot.demo.auth.shiro.token;

import com.springboot.demo.auth.utils.AesUtil;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 登入Token
 *
 * @author Jim
 */
@Data
public class PasswordToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String appId;
    private String password;
    private String timestamp;
    private String host;
    private String tokenKey;

    public PasswordToken(String appId, String password, String timestamp, String host, String tokenKey) throws Exception {
        this.appId = appId;
        this.timestamp = timestamp;
        this.host = host;
        this.password = AesUtil.aesDecode(password, tokenKey);
        this.tokenKey = tokenKey;

    }

    @Override
    public Object getPrincipal() {
        return this.appId;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

}
