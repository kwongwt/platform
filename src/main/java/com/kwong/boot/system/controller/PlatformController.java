package com.kwong.boot.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlatformController extends BaseController {
	
	static String BASE_URL = "/platform";
	
    /**
     * 	跳转到主页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String empty(Model model) {
        return BASE_URL + "/index.html";
    }
	
    /**
     * 	跳转到主页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return BASE_URL + "/index.html";
    }
}
