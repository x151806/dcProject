package com.dc.sys.service;

import java.util.List;
import java.util.Map;

import com.dc.common.vo.Node;
import com.dc.sys.entity.SysMenu;

public interface SysMenuService {
	/**
	 * 将菜单对象信息持久化到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	/**
	 * 将菜单对象信息持久化到数据库
	 * @param entity
	 * @return
	 */
	int saveObject(SysMenu entity);
	
	List<Node> findZtreeMenuNodes();
	/**
	 * 基于菜单id删除:
	 * 1)菜单自身信息
	 * 2)菜单与角色关系数据
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	

	/**
	 * 查询所有菜单以及上一级菜单的菜单名称
	 * 1)一行记录映射为一个map 
	 *   (key为查询结果中的字段名-元数据-MetaData)
	 * 2)多行记录对应多个map
	 * 3)多个map存储到list集合
	 * @return
	 */
	List<Map<String,Object>> findObjects();
}
