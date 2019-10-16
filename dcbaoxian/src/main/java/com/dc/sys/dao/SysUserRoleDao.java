package com.dc.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDao {
	

	/**
	 * 基于用户id删除用户以及对应角色关系数据
	 * @param userId
	 * @return
	 */
	int deleteObjectsByUserId(Integer userId);
	
	/**
	 * 查询用户对应的角色信息
	 * @param id
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer id);
	
	/**
	 * 保存用户和角色关系数据
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	int insertObjects(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[]roleIds);

}
