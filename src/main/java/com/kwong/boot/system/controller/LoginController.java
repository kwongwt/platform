package com.kwong.boot.system.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kwong.boot.shiro.ShiroKit;

/** 
* @Description: 登陆控制器
* @author: kwong
* @date: Oct 9, 2018
*/
@Controller
public class LoginController extends BaseController {
    
	/**
	 *  跳转到登陆界面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
		if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }
	
	/**
	 *   普通登陆操作
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginVail() {
		
		String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        Boolean rememberMe = Boolean.valueOf(super.getPara("rememberMe"));
        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        if (rememberMe) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        currentUser.login(token);
        
        return REDIRECT + "/back/index";
	}
	
	/**
	 *   ajax登陆操作
	 */
	@RequestMapping(value = "/rest/login", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxLoginVail() {
		
		String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        Boolean rememberMe = Boolean.valueOf(super.getPara("rememberMe"));
        
        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        if (rememberMe) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        currentUser.login(token);
        
        return SUCCESS_TIP;
	}
	
	
	/**
	 *  退出系统，重定向到登陆界面
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
		ShiroKit.getSubject().logout();
        deleteAllCookie();
		return REDIRECT + "/login";
    }

}
