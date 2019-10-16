package com.dc.sys.service;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.common.vo.PageObject;
import com.dc.sys.entity.SysReport;
import com.dc.sys.entity.SysReportModComment;
import com.dc.sys.entity.SysReportModWork;
import com.dc.sys.vo.SysReportNameVo;
import com.dc.sys.vo.SysUserDeptVo;

public interface SysReportService {
	
	
	/**
	 * 更新个人头像
	 * @param avatar 头像路径
	 * @throws UserNotFoundException 用户数据不存在
	 * @throws UpdateException 更新数据异常
	 */
	int changeAvatar(String reportId, String avatar);

	


	/**
	 * 将对象信息持久化到数据库
	 * @param entity
	 * @return
	 */
	int saveObject(SysReport entity);
	/**
	 * 将对象信息持久化到数据库
	 * @param entity
	 * @return
	 */
	int saveModWorkObject(SysReportModWork entity,String handlers,String master);
	/**
	 * 将对象信息持久化到数据库
	 * @param entity
	 * @return
	 */
	int saveModCommentObject(SysReportModComment entity);

	/**
	 * 将对象信息删除
	 * @param entity
	 * @return
	 */
	int deleteObject(String reportId);
	


	/**
	 * 查询用户日志
	 * @param username 查询条件(用户名)
	 * @param pageCurrent 当前页码值
	 * @return
	 */
	PageObject<SysReportNameVo> findPageObjects(
			Integer userId,
			Integer pageCurrent);
	/**
	 * 通过日期查询用户信息
	 * @param userId
	 * @return
	 */
	 List<SysReportNameVo> findByTimeObjects(Integer userId,Date workTime);
	 /**
	  * 查询下属用户信息
	  * @param userId
	  * @return
	  */
	 List<SysUserDeptVo> findDownObjects(Integer userId);
	 /**
	  * 查询用户信息
	  * @param userId
	  * @return
	  */
	 List<SysReportNameVo> findReportObjects(String reportId);
	 /**
	  * 查询用户修改
	  * @param userId
	  * @return
	  */
	 List<SysReportModWork> findModWorkObjects(String reportId);
	/**
	 * 查询主管点评
	 * @param reportId
	 * @return
	 */
	 List<SysReportModComment> findModCommentObjects(String reportId);

}
