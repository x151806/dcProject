package com.dc.sys.service;

import java.util.List;

import com.dc.common.vo.CheckBox;
import com.dc.common.vo.PageObject;
import com.dc.sys.entity.SysRole;
import com.dc.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	 /**
	 * 查询系统角色id和name信息
	 * @return
	 */
	 List<CheckBox> findObjects();
	 
	 /**
	  * 修改角色信息
	  * @param entity
	  * @param menuIds
	  * @return
	  */
	 int updateObject(SysRole entity,Integer[] menuIds);
	
	 /**
	  * 基于角色id查询角色name，note以及角色对应的
	  * 菜单id
	  * @param id
	  * @return
	  */
	 SysRoleMenuVo findObjectById(Integer id);
	
	 /**
	  * 保存角色以及角色和菜单的关系数据
	  * @param entity
	  * @param menuIds
	  * @return
	  */
	 int saveObject(SysRole entity,
			 Integer[] menuIds);
	 
     /**
      * 基于条件实现角色信息的分页查询
      * @param name
      * @param pageCurrent
      * @return
      */
	 PageObject<SysRole> findPageObjects(
			 String name,
			 Integer pageCurrent);
}







