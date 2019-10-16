package com.dc.exp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.exp.entity.ExpMission;
import com.dc.exp.entity.ExpProgress;
import com.dc.sys.vo.AllCommon;
import com.dc.sys.vo.SysUserDeptVo;

public interface ExpMissionDao {
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertCommon(AllCommon entity);
	/**
	 *查找的信息
	 * @param 
	 * @return
	 */
	List<AllCommon> findCommon(@Param("type")Integer type);
	/**
	 *查找的信息
	 * @param 
	 * @return
	 */
	int findCommonComb(@Param("type")Integer type,@Param("details")String details);
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertobject(ExpMission entity);
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertProgress(ExpProgress entity);
	
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	int deleteObjects(@Param("ids")String ...ids);
	

	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	int deleteProgress(@Param("ids")String ...ids);


	/**
	 * 更新用户自身信息
	 * @param entity
	 * @return
	 */
	int updateMission(ExpMission entity);
	
	
	/**
	 * 修改信息
	 * @param entity
	 * @return
	 */
	int updateObjects(@Param("ids")String ...ids);
	
	/**
	 * 修改信息
	 * @param entity
	 * @return
	 */
	int updateRemark(@Param("missionId")String missionId,
			@Param("remarkContent")String remarkContent,
			@Param("remarkSatrap")String remarkSatrap);
	
	/**
	 * 修改信息
	 * @param entity
	 * @return
	 */
	int updateStatus(@Param("missionId")String missionId,
			@Param("status")Integer status);
	/**
	 * 基于列表id查找用户修改的信息
	 * @param username
	 * @return
	 */
	List<ExpProgress> findProgressById(@Param("disId")String disId);
	
	/**
	 * 查询信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<ExpMission> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 查询信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<ExpMission> findPageAcquire(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 查-------询信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<ExpMission> findPageByExecutor(@Param("username")String username,
			@Param("meeting")String meeting,@Param("satrap")String satrap,
			@Param("executor")String executor,@Param("status")Integer status,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 基于用户id查询信息
	 * @param id
	 * @return
	 */
	ExpMission findObjectById(String missionId);
	
	
	/**
	 * 查询条数
	 * @param username
	 * @return
	 */
	int getRowCount(@Param("username")String username);
	
	/**
	 * 查询条数
	 * @param username
	 * @return
	 */
	int getAcquireCount(@Param("username")String username);
	
	/**
	 * 查-------询条数
	 * @param username
	 * @return
	 */
	int getAcquireByExecutor(@Param("username")String username,
			@Param("meeting")String meeting,@Param("satrap")String satrap,
			@Param("executor")String executor,@Param("status")Integer status);
	
	/**
	 * 查询
	 * @param username
	 * @return
	 */
	List<ExpMission> findStatus(@Param("meeting")String meeting,@Param("timeHead")String timeHead,@Param("timeFoot")String timeFoot);
	
}
