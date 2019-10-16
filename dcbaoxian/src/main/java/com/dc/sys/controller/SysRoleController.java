package com.dc.sys.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.common.vo.JsonResult;
import com.dc.sys.entity.SysRole;
import com.dc.sys.service.SysRoleService;

@RequestMapping("/role/")
@Controller
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping("doRoleListUI")
	public String doRoleListUI(){
		return "sys/role_list";
	}
	
	@RequestMapping("doRoleEditUI")
	public String doRoleEditUI(){
		return "sys/role_edit";
	}
	
	@RequestMapping("doFindRoles")
	@ResponseBody
	public JsonResult doFindObjects(){
	  	 return new JsonResult(
	     sysRoleService.findObjects());
	}

	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		return new JsonResult(
		sysRoleService.findObjectById(id));
		
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
			SysRole entity,
			Integer[] menuIds){
		sysRoleService.updateObject(entity,
				menuIds);
		return new JsonResult("update ok");
	}
	
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysRole entity,
			Integer[] menuIds,HttpSession session){
		
		String createdUser=session.getAttribute("username").toString();
		entity.setCreatedUser(createdUser);
		sysRoleService.saveObject(entity,
				menuIds);
		
		return new JsonResult("save ok");
	}
	@GetMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String name,Integer pageCurrent){
		return new JsonResult(
		sysRoleService.findPageObjects(
				name,pageCurrent));
	}
	
}
