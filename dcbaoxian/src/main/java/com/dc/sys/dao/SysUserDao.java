package com.dc.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.sys.entity.SysUser;
import com.dc.sys.vo.SysUserDeptVo;
public interface SysUserDao {
    /**
     * 基于用户名模糊查找用户信息
     * @param username
     * @return
     */
	List<SysUser> findLikeName();
   /**
    * 基于用户名查找用户信息
    * @param username
    * @return
    */
   SysUser findUserByUserName(String username);
   /**
  
	/**
	 * 更新用户自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysUser entity);
	/**
	 * 修改移动
	 * @param entity
	 * @return
	 */
	int updatePassword(SysUser entity);
	
	/**
	 * 基于用户id查询用户和所属部门信息和上级信息
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	/**
	 * 将用户信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);

	 /**
	  * 禁用或启用用户信息
	  * @param id
	  * @param valid
	  * @param modifiedUser
	  * @return
	  */
	 int validById(
			 @Param("id")Integer id,
			 @Param("valid")Integer valid,
			 @Param("modifiedUser")String modifiedUser);
	 /**
	  * 按条件统计记录总数
	  * @param username
	  * @return
	  */
	 int getRowCount(@Param("username")String username);
	 /**
	  * 按条件查询当前页记录
	  * @param username
	  * @param startIndex
	  * @param pageSize
	  * @return
	  */
	 List<SysUserDeptVo> findPageObjects(
			 @Param("username")String username,
			 @Param("startIndex")Integer startIndex,
			 @Param("pageSize")Integer pageSize);
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
