package com.springboot.demo.auth.dao;

import com.springboot.demo.auth.entity.bo.AuthResource;
import com.springboot.demo.auth.shiro.rule.RolePermRule;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author Jim
 */
public interface AuthResourceMapper {
    /**
     * description TODO
     *
     * @param id 1
     * @return int
     * @throws DataAccessException when
     */
    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int insert(AuthResource record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int insertSelective(AuthResource record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param id 1
     * @return AuthResource
     * @throws DataAccessException when
     */
    AuthResource selectByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int updateByPrimaryKeySelective(AuthResource record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int updateByPrimaryKey(AuthResource record) throws DataAccessException;

    /**
     * description TODO
     *
     * @return java.util.List<com.usthe.bootshiro.shiro.rule.RolePermRule>
     * @throws DataAccessException when
     */
    List<RolePermRule> selectRoleRules() throws DataAccessException;

    /**
     * description TODO
     *
     * @param appId 1
     * @return java.util.List<AuthResource>
     * @throws DataAccessException when
     */
    List<AuthResource> selectAuthorityMenusByUid(String appId) throws DataAccessException;

    /**
     * description TODO
     *
     * @return java.util.List<AuthResource>
     * @throws DataAccessException when
     */
    List<AuthResource> selectMenus() throws DataAccessException;

    /**
     * description TODO
     *
     * @return java.util.List<AuthResource>
     * @throws DataAccessException when
     */
    List<AuthResource> selectApiTeamList() throws DataAccessException;

    /**
     * description TODO
     *
     * @return java.util.List<AuthResource>
     * @throws DataAccessException when
     */
    List<AuthResource> selectApiList() throws DataAccessException;

    /**
     * description TODO
     *
     * @param teamId 1
     * @return java.util.List<AuthResource>
     * @throws DataAccessException when
     */
    List<AuthResource> selectApiListByTeamId(Integer teamId) throws DataAccessException;

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthResource>
     * @throws DataAccessException when
     */
    List<AuthResource> selectApisByRoleId(Integer roleId) throws DataAccessException;

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthResource>
     * @throws DataAccessException when
     */
    List<AuthResource> selectMenusByRoleId(Integer roleId) throws DataAccessException;

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthResource>
     * @throws DataAccessException when
     */
    List<AuthResource> selectNotAuthorityApisByRoleId(Integer roleId) throws DataAccessException;

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<AuthResource>
     * @throws DataAccessException when
     */
    List<AuthResource> selectNotAuthorityMenusByRoleId(Integer roleId) throws DataAccessException;
}