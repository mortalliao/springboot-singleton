//package com.springboot.demo.auth.controller;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.springboot.demo.auth.entity.bo.AuthUser;
//import com.springboot.demo.auth.entity.vo.Message;
//import com.springboot.demo.auth.service.UserService;
//import com.springboot.demo.auth.support.factory.LogTaskFactory;
//import com.springboot.demo.auth.support.manager.LogExeManager;
//import com.springboot.demo.auth.utils.JsonWebTokenUtil;
//import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.SecurityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * 用户相关操作
// *
// * @author Jim
// */
//@RestController
//@RequestMapping("/user")
//public class UserController extends BaseAction {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @ApiOperation(value = "获取对应用户角色", notes = "GET根据用户的appId获取对应用户的角色")
//    @GetMapping("/role/{appId}")
//    public Message getUserRoleList(@PathVariable String appId) {
//        String roles = userService.loadAccountRole(appId);
//        Set<String> roleSet = JsonWebTokenUtil.split(roles);
//        LOGGER.info(roleSet.toString());
//        return new Message().ok(6666, "return roles success").addData("roles", roleSet);
//    }
//
//    @SuppressWarnings("unchecked")
//    @ApiOperation(value = "获取用户列表", notes = "GET获取所有注册用户的信息列表")
//    @GetMapping("/list/{start}/{limit}")
//    public Message getUserList(@PathVariable Integer start, @PathVariable Integer limit) {
//
//        PageHelper.startPage(start, limit);
//        List<AuthUser> authUsers = userService.getUserList();
//        authUsers.forEach(user -> user.setPassword(null));
//        PageInfo pageInfo = new PageInfo(authUsers);
//        return new Message().ok(6666, "return user list success").addData("pageInfo", pageInfo);
//    }
//
//    @ApiOperation(value = "给用户授权添加角色", httpMethod = "POST")
//    @PostMapping("/authority/role")
//    public Message authorityUserRole(HttpServletRequest request) {
//        Map<String, String> map = getRequestBody(request);
//        String uid = map.get("uid");
//        int roleId = Integer.parseInt(map.get("roleId"));
//        boolean flag = userService.authorityUserRole(uid, roleId);
//        return flag ? new Message().ok(6666, "authority success") : new Message().failed(1111, "authority failed");
//    }
//
//    @ApiOperation(value = "删除已经授权的用户角色", httpMethod = "DELETE")
//    @DeleteMapping("/authority/role/{uid}/{roleId}")
//    public Message deleteAuthorityUserRole(@PathVariable String uid, @PathVariable Integer roleId) {
//        return userService.deleteAuthorityUserRole(uid, roleId) ? new Message().ok(6666, "delete success") : new Message().failed(1111, "delete fail");
//    }
//
//    @ApiOperation(value = "用户登出", httpMethod = "POST")
//    @PostMapping("/exit")
//    public Message accountExit(HttpServletRequest request) {
//        SecurityUtils.getSubject().logout();
//        Map<String, String> map = getRequestHeader(request);
//        String appId = map.get("appId");
//        if (StringUtils.isEmpty(appId)) {
//            return new Message().failed(1111, "用户未登录无法登出");
//        }
//        String jwt = redisTemplate.opsForValue().get("JWT-SESSION-" + appId);
//        if (StringUtils.isEmpty(jwt)) {
//            return new Message().failed(1111, "用户未登录无法登出");
//        }
//        redisTemplate.opsForValue().getOperations().delete("JWT-SESSION-" + appId);
//        LogExeManager.getInstance().executeLogTask(LogTaskFactory.exitLog(appId, request.getRemoteAddr(), (short) 1, ""));
//
//        return new Message().ok(6666, "用户退出成功");
//    }
//
//}
