package com.dc.sys.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.common.vo.JsonResult;
import com.dc.common.vo.PageObject;
import com.dc.sys.entity.SysUser;
import com.dc.sys.service.SysUserService;
import com.dc.sys.vo.SysUserDeptVo;

@Controller
@RequestMapping("/user/")
public class SysUserController implements HttpSessionListener{
	   @Autowired
	   private SysUserService sysUserService;
	   
	   @GetMapping("doUserListUI")
	   public String doUserListUI(){
		   return "sys/user_list";
	   }
	   
	   @GetMapping("doUserEditUI")
	   public String doUserEditUI(){
		   return "sys/user_edit";
	   }
	   @GetMapping("doUpPassword")
	   public String doUpPassword(){
		   return "sys/user_uppassword";
	   }
	   @RequestMapping("doLogin")
	   @ResponseBody
	   public JsonResult doLogin(String username,
			   String password,HttpSession session){
		   
		   //1.封装用户名密码信息到token对象
		   UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		   //2.获取一个Subject对象(主体对象)。
		   Subject subject=SecurityUtils.getSubject();
		   //3.通过Subject此对象提交token对象
		   subject.login(token);
		   //提交到哪里了?SecurityManager
		   //SecurityManager获取token以后会将token传递给认证管理器
		   //认证管理器负责对token中信息进行认证操作
		   SysUser user = sysUserService.findObjectByName(username);
		   session.setAttribute("id", user.getId());
			session.setAttribute("username", user.getUsername());
		   return new JsonResult("登录ok");
	   }
	   
	   @GetMapping("doFindObjectById")
	   @ResponseBody
	   public JsonResult doFindObjectById(Integer id){
		   return new JsonResult(
		   sysUserService.findObjectById(id));
	   }
	   
	   @PostMapping("doUpdateObject")
	   @ResponseBody
	   public JsonResult doUpdateObject(SysUser entity,
			   Integer[] roleIds){
		   sysUserService.updateObject(entity, roleIds);
		   return new JsonResult("update ok");
	   }
	   @PostMapping("doUpdatePassword")
	   @ResponseBody
	   public JsonResult doUpdatePassword(SysUser entity){
		   sysUserService.updatePassword(entity);
		   return new JsonResult("update ok");
	   }
	   
	   @PostMapping("doSaveObject")
	   @ResponseBody
	   public JsonResult doSaveObject(SysUser entity,
			   Integer[] roleIds){
		   sysUserService.saveObject(entity, roleIds);
		   return new JsonResult("save ok");
	   }
	   
	   @PostMapping("doValidById")
	   @ResponseBody
	   public JsonResult doValidById(
			   Integer id,Integer valid){
		   //此登录用户是在认证时传入的一个身份，
		   //可以看ShiroUserRealm中的AuthenticationInfo方法
		   SysUser user=(SysUser)
		   SecurityUtils.getSubject()
		   .getPrincipal();
		   sysUserService.validById(id,valid,user.getUsername());
		   return new JsonResult("update ok");
	   }
	   
	   @RequestMapping("doFindPageObjects")
	   @ResponseBody
	   public JsonResult doFindPageObjects(
			   String username,Integer pageCurrent){
		   PageObject<SysUserDeptVo> pageObject=
		   sysUserService.findPageObjects(username,
				   pageCurrent);
	       return new JsonResult(pageObject);
	   }
	   
	   @RequestMapping("doFindLikeNames")
	   @ResponseBody
	   public JsonResult doFindLikeNames(){
		   List<SysUser> objects=
		   sysUserService.findLikeNameObject();
		   
		   
	       return new JsonResult(objects);
	   }
}







