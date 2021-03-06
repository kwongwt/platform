package com.kwong.boot.shiro;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.kwong.boot.shiro.factory.IShiro;
import com.kwong.boot.shiro.factory.ShiroFactroy;
import com.kwong.boot.system.model.User;
import com.kwong.boot.system.repository.UserRepository;

public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * 登陆认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		System.err.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
		IShiro shiroFactory = ShiroFactroy.me();
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = shiroFactory.user(token.getUsername());
		ShiroUser shiroUser = shiroFactory.shiroUser(user);
		user.setLastLoginTime(new Date());
		userRepository.save(user);
		return shiroFactory.info(shiroUser, user, super.getName());
	}

	/**
	 *  权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
		User token = (User)SecurityUtils.getSubject().getPrincipal();
		Long userId = token.getId();
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//根据用户ID查询角色（role），放入到Authorization里。
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		List<SysRole> roleList = sysRoleService.selectByMap(map);
		Set<String> roleSet = new HashSet<String>();
		for(SysRole role : roleList){
			roleSet.add(role.getType());
		}*/
		//实际开发，当前登录用户的角色和权限信息是从数据库来获取的，我这里写死是为了方便测试
		Set<String> roleSet = new HashSet<String>();
		roleSet.add("100002");
		info.setRoles(roleSet);
		//根据用户ID查询权限（permission），放入到Authorization里。
		/*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
		Set<String> permissionSet = new HashSet<String>();
		for(SysPermission Permission : permissionList){
			permissionSet.add(Permission.getName());
		}*/
		Set<String> permissionSet = new HashSet<String>();
		permissionSet.add("权限添加");
		info.setStringPermissions(permissionSet);
	    return info;
	}

}
