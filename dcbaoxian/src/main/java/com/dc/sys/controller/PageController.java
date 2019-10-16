package com.dc.sys.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 在此控制层对象中提供一些对外的页面呈现。
 * @author ta
 */
@Controller
@RequestMapping("/")
public class PageController {
	
	//返回登录页面
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
		return "login";
	}
	
	@RequestMapping("doIndexUI")
	public String doIndexUI(){
		return "starter";
		//返回值会给dispatcherServlet
		//dispatcherServlet会将此值交给视图解析器
		//视图解析器会对此值进行解析获取view
		
		//Prefix=/WEB-INF/pages/
		//Suffix=.html
		//view=/WEB-INF/pages/starter.html
	}
	@RequestMapping("doAnmUI")
	@ResponseBody
	public String doIndexUI(HttpSession session){
		String username=session.getAttribute("username").toString();
		return username;
		//返回值会给dispatcherServlet
		//dispatcherServlet会将此值交给视图解析器
		//视图解析器会对此值进行解析获取view
		
		//Prefix=/WEB-INF/pages/
		//Suffix=.html
		//view=/WEB-INF/pages/starter.html
	}
	@RequestMapping("doPageUI")
	public String doPageUI(){
		return "common/page";
	}
}








