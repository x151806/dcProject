package com.dc.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.common.vo.JsonResult;
import com.dc.sys.entity.SysMenu;
import com.dc.sys.service.SysMenuService;

@Controller
@RequestMapping("/menu/")
public class SysMenuController {

	  @Autowired
	  private SysMenuService sysMenuService;
	  
	  @RequestMapping("doMenuListUI")
	  public String doMenuListUI(){
		  return "sys/menu_list";
	  }
	  
	  @RequestMapping("doMenuEditUI")
	  public String doMenuEditUI(){
		  return "sys/menu_edit";
	  }
	  
	  
	  
	  @RequestMapping("doUpdateObject")
	  @ResponseBody
	  public JsonResult doUpdateObject(SysMenu entity){
		  sysMenuService.updateObject(entity);
		  return new JsonResult("update ok");
	  }
	  
	  /**
	   * 关注点：请求参数映射
	   * 1)请求参数名必须与方法参数对象的set方法一致。
	   * @param entity
	   * @return
	   */
	  @RequestMapping("doSaveObject")
	  @ResponseBody
	  public JsonResult doSaveObject(SysMenu entity){
		  sysMenuService.saveObject(entity);
		  return new JsonResult("save ok");
	  }

	  @RequestMapping("doFindZtreeMenuNodes")
	  @ResponseBody
	  public JsonResult doFindZtreeMenuNodes(){
		  return new JsonResult(
		  sysMenuService.findZtreeMenuNodes());
	  }
	  
	  @RequestMapping("doDeleteObject")
	  @ResponseBody 
	  public JsonResult doDeleteObject(
			  Integer id){
		  //sysMenuService.deleteObject(id);
		  return new JsonResult("delete ok");
	  }
	  
	  //../menu/doFindObjects.do
	  @RequestMapping("doFindObjects")
	  @ResponseBody
	  public JsonResult doFindObjects(){
		  
		  List<Map<String,Object>> list=
		  sysMenuService.findObjects();
		  return new JsonResult(list);
		  
	  }//ctrl+shit+o 快速导入包中类
  
}
