package com.kwong.boot.system.controller;

import com.kwong.base.tips.SuccessTip;

/**
 * @Description: 基础控制器
 * @author: kwong
 * @date: Oct 9, 2018
 */
public class BaseController {

	protected static String SUCCESS = "SUCCESS";
	protected static String ERROR = "ERROR";

	protected static String REDIRECT = "redirect:";
	protected static String FORWARD = "forward:";

	protected static SuccessTip SUCCESS_TIP = new SuccessTip();
}
