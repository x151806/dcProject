package com.dc.sys.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dc.common.vo.Node;
import com.dc.sys.entity.SysMenu;

public interface SysMenuDao {
	/**
	 * 基于菜单id获取对应的权限标识(permission)
	 * @param menuIds
	 * @return
	 */
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);

	
	/**
	 * 将数据更新到数据库
	 * @param entity (po对象)-持久化对象
	 * @return
	 */
	int updateObject(SysMenu entity);
	
	/**
	 * 向数据库中写入菜单信息
	 * @param entity (po对象)-持久化对象
	 * @return
	 */
	int insertObject(SysMenu entity);
	
	/**
	 * 查询菜单节点信息
	 * 1)菜单id
	 * 2)菜单名称
	 * 3)上级菜单id
	 * 将查询出的节点信息封装到Node对象
	 * 1)一行记录一个node对象(行映射-rowMap)
	 * 2)多个node对象存储到list集合
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	
	/**
	 * 基于菜单id统计子菜单的个数
	 * @param id
	 * @return
	 */
	int getChildCount(Integer id);
	
	/**
	 * 基于菜单id删除菜单自身信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
	
	
    /**
     * 查询所有菜单以及上级菜单信息(上级菜单信息中
     * 至少要包含一个菜单名称)。
     */
	List<Map<String,Object>> findObjects();
	
	
	
}
