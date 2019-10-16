package com.dc.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.common.vo.CheckBox;
import com.dc.sys.entity.SysRole;
import com.dc.sys.vo.SysRoleMenuVo;

public interface SysRoleDao {
	/**
	 * 查询系统的角色id和name信息
	 * @return
	 */
	 List<CheckBox> findObjects();
	 
	 
	/**
	 * 基于角色id查询角色以及菜单数据
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);
	
	/**
	 * 将角色自身信息更新到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity);
	/**
	 * 将角色自身信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity);
	
    /**
     * 基于条件查询总记录数
     * @param name
     * @return
     */
	int getRowCount(@Param("name")String name);
	/**
	 * 基于条件查询当前页的角色信息
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysRole> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
}
