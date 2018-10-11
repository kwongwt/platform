package com.kwong.boot.shiro.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kwong.boot.base.util.SpringContextHolder;
import com.kwong.boot.shiro.ShiroUser;
import com.kwong.boot.system.model.Resource;
import com.kwong.boot.system.model.Role;
import com.kwong.boot.system.model.User;
import com.kwong.boot.system.repository.RoleRepository;
import com.kwong.boot.system.repository.UserRepository;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public User user(String username) {

        User user = userRepository.findByUsername(username);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != 1) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());
        shiroUser.setUsername(user.getUsername());
        shiroUser.setName(user.getName());

        List<Role> roleList = user.getRoleList();
        List<Integer> roleIdList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        if(!roleList.isEmpty()) {
        	for(Role role:roleList) {
        		roleIdList.add(role.getId());
            	roleNameList.add(role.getName());
            }
        }
        shiroUser.setRoleList(roleIdList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

	@Override
	public List<Integer> findResourcesByRoldId(Integer roleId) {
		List<Integer> resourceIds = new ArrayList<Integer>();
		Role role = roleRepository.getOne(roleId);
		List<Resource> resourcesList = role.getResourceList();
		for(Resource resource:resourcesList) {
			resourceIds.add(resource.getId());
		}
		return resourceIds;
	}
    
    @Override
    public String findRoleNameByRoleId(Integer roleId) {
    	Role role = roleRepository.getOne(roleId);
        return role.getName();
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();

        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }


}
