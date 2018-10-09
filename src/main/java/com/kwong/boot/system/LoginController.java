package com.kwong.boot.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		return "/login.html";
    }
	
	/**
	 *  退出系统，重定向到登陆界面
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
		return REDIRECT + "/login";
    }

}
