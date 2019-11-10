package com.springboot.demo.auth.service;


import com.springboot.demo.auth.entity.bo.AuthResource;

import java.util.List;

/**
 * @author Jim
 */
public interface ResourceService {

    /**
     * description TODO
     *
     * @param appId 1
     * @return java.util.List<AuthResource>
     */
    List<AuthResource> getAuthorityMenusByUid(String appId);

    /**
     * description TODO
     *
     * @return java.util.List<AuthResource>
     */
    List<AuthResource> getMenus();

    /**
     * description TODO
     *
     * @param menu 1
     * @return java.lang.Boolean
     */
    Boolean addMenu(AuthResource menu);

    /**
     * description TODO
     *
     * @param menu 1
     * @return java.lang.Boolean
     */
    Boolean modifyMenu(AuthResource menu);

    /**
     * description TODO
     *
     * @param menuId 1
     * @return java.lang.Boolean
     */
    Boolean deleteMenuByMenuId(Integer menuId);


    /**
     * description TODO
     *
     * @return java.util.List<AuthResource>
     */
    List<AuthResource> getApiTeamList();

    /**
     * description TODO
     *
     * @return java.util.List<AuthResource>
     */
    List<AuthResource> getApiList();

    /**
     * description TODO
     *
     * @param teamId 1
     * @return java.util.List<AuthResource>
     */
    List<AuthResource> getApiListByTeamId(Integer teamId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthResource>
     */
    List<AuthResource> getAuthorityApisByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthResource>
     */
    List<AuthResource> getAuthorityMenusByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthResource>
     */
    List<AuthResource> getNotAuthorityApisByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthResource>
     */
    List<AuthResource> getNotAuthorityMenusByRoleId(Integer roleId);
}
