package com.kwong.boot.shiro.factory;

import java.util.List;

import org.apache.shiro.authc.SimpleAuthenticationInfo;

import com.kwong.boot.shiro.ShiroUser;
import com.kwong.boot.system.model.User;

/** 
* @Description: 定义shirorealm所需数据的接口
* @author: kwong
* @date: Oct 11, 2018
*/ 
public interface IShiro {

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    User user(String username);

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param user 系统用户
     */
    ShiroUser shiroUser(User user);

    /**
     * 获取权限列表通过角色id
     *
     * @param roleId 角色id
     */
    List<Integer> findResourcesByRoldId(Integer roleId);
    
    /**
     * 根据角色id获取角色名称
     *
     * @param roleId 角色id
     */
    String findRoleNameByRoleId(Integer roleId);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String name);

	

}
