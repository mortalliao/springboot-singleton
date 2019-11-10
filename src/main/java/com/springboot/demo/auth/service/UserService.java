package com.springboot.demo.auth.service;

import com.springboot.demo.auth.entity.bo.AuthUser;

import java.util.List;

/**
 * @author Jim
 */
public interface UserService {

    /**
     * description TODO
     *
     * @param appId 1
     * @return java.lang.String
     */
    String loadAccountRole(String appId);

    /**
     * description TODO
     *
     * @return java.util.List<AuthUser>
     */
    List<AuthUser> getUserList();

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthUser>
     */
    List<AuthUser> getUserListByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param appId 1
     * @param roleId 2
     * @return boolean
     */
    boolean authorityUserRole(String appId, int roleId);

    /**
     * description TODO
     *
     * @param uid 1
     * @param roleId 2
     * @return boolean
     */
    boolean deleteAuthorityUserRole(String uid, int roleId);

    /**
     * description TODO
     *
     * @param appId 1
     * @return AuthUser
     */
    AuthUser getUserByAppId(String appId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthUser>
     */
    List<AuthUser> getNotAuthorityUserListByRoleId(Integer roleId);
}
