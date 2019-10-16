package com.dc.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuDao {
	/**
	 * 基于角色id获取对应的菜单id
	 * @param roleIds
	 * @return
	 */
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleIds);

	/**
	 * 将角色和菜单的关系数据写入到数据库
	 * @param roleId 角色id
	 * @param menuIds 菜单id
	 * @return
	 */
	int insertObjects(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	/**
	 * 基于菜单id删除角色和菜单的关系数据
	 * @param id
	 * @return
	 */
	int deleteObjectsByMenuId(Integer id);
	
	/**
	 * 基于角色id删除关系数据
	 * @param id
	 * @return
	 */
	int deleteObjectsByRoleId(Integer id);
	
	
	
	
}
