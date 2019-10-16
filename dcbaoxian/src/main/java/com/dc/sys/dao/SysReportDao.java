package com.dc.sys.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.sys.entity.SysMenu;
import com.dc.sys.entity.SysReport;
import com.dc.sys.entity.SysReportModComment;
import com.dc.sys.entity.SysReportModWork;
import com.dc.sys.entity.SysUser;
import com.dc.sys.vo.SysReportNameVo;
import com.dc.sys.vo.SysUserDeptVo;


public interface SysReportDao {
	
	/**
	 * 向数据库中写入菜单信息
	 * @param entity 
	 * @return
	 */
	int insertObject(SysReport entity);
	
	/**
	 * 添加日志备注
	 * @param entity
	 * @return
	 */
	int insertModWork(SysReportModWork entity);
	/**
	 * 添加点评备注
	 * @param entity
	 * @return
	 */
	int insertModComment(SysReportModComment entity);
	
	/**
	 * 删除菜单信息
	 * @param entity 
	 * @return
	 */
	int deleteObject(String reportId);
	
	/**
	 * 上传图片
	 * @param reportId
	 * @param avatar
	 * @return
	 */
	int updateAvatar(
		    @Param("reportId") String reportId, 
		    @Param("avatar") String avatar);
	
	
	/**
	 * 信息去回车换行
	 * @param entity 
	 * @return
	 */
	int updateContent(String reportId);
	/**
	 * 信息去回车换行
	 * @param entity 
	 * @return
	 */
	int updateExplain(String reportId);
	
	
	
	/**
     * 基于用户名id查找用户信息
     * @param username
     * @return
     */
	List<SysReportNameVo> findUserByUserId(@Param("userId")Integer userId,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 基于用列表id查找信息
	 * @param username
	 * @return
	 */
	List<SysUserDeptVo> findDown(@Param("userId")Integer userId);
	/**
	 * 基于日期查找用户信息
	 * @param username
	 * @return
	 */
	List<SysReportNameVo> findReportByTime(@Param("userId")Integer userId,@Param("workTime")Date workTime);
	/**
	 * 基于用列表id查找信息
	 * @param username
	 * @return
	 */
	List<SysReportNameVo> findReportById(@Param("reportId")String reportId);
	
	/**
	 * 基于列表id查找用户修改的备注信息
	 * @param username
	 * @return
	 */
	List<SysReportModWork> findModWorkById(@Param("reportId")String reportId);
	
	
	/**
	 * 基于列表id查找主管的点评信息
	 * @param username
	 * @return
	 */
	List<SysReportModComment> findModCommentById(@Param("reportId")String reportId);
	
	
	
	/**
	 *  基于用户名查找信息数量
	 * @param userId
	 * @return
	 */
	int getRowCount(Integer userId);




}
