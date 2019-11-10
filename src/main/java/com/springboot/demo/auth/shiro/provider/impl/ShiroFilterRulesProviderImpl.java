package com.springboot.demo.auth.shiro.provider.impl;

import com.springboot.demo.auth.dao.AuthResourceMapper;
import com.springboot.demo.auth.shiro.provider.ShiroFilterRulesProvider;
import com.springboot.demo.auth.shiro.rule.RolePermRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Jim
 */
@Service("ShiroFilterRulesProvider")
public class ShiroFilterRulesProviderImpl implements ShiroFilterRulesProvider {

    @Autowired
    private AuthResourceMapper authResourceMapper;

    @Override
    public List<RolePermRule> loadRolePermRules() {
        return authResourceMapper.selectRoleRules();
    }

}
