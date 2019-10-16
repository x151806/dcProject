package com.dc.sys.service;

import java.util.List;
import java.util.Map;

import com.dc.common.vo.PageObject;
import com.dc.sys.entity.SysUser;
import com.dc.sys.vo.SysUserDeptVo;

public interface SysUserService {
	/**
     * 基于用户名模糊查找用户信息
     * @param username
     * @return
     */
	List<SysUser> findLikeNameObject();
	/**
	 * 基于id查询用户以及用户对应的部门，角色信息
	 * @param userId
	 * @return
	 */
	Map<String, Object> findObjectById(
			Integer userId);
	/**
	 * 基于名字查询用户角色信息
	 * @param userId
	 * @return
	 */
	SysUser findObjectByName(
			String userName);

	/**
	 * 更新用户自身信息以及用户和角色关系数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updateObject(SysUser entity,
			Integer[]roleIds);
	/**
	 * 修改密码
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updatePassword(SysUser entity);
	 /**
	  * 保存用户自身信息以及用户和角色关系数据
	  * @param entity
	  * @param roleIds
	  * @return
	  */
	 int saveObject(SysUser entity,
			 Integer[]roleIds);
	
	 int validById(Integer id,
			       Integer valid,
			       String modifiedUser);
	 

	 PageObject<SysUserDeptVo> findPageObjects(
			 String username,
			 Integer pageCurrent);
}
