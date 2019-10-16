package com.dc.exp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.common.vo.PageObject;
import com.dc.exp.entity.ExpMission;
import com.dc.exp.entity.ExpProgress;
import com.dc.exp.entity.ExpStatistics;
import com.dc.sys.vo.AllCommon;

public interface ExpMissionService {

	/**
	 * 保存用户自身信息以及用户和角色关系数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObject(ExpMission entity);

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertProgressObject(ExpProgress entity);

	/**
	 * 基于id执行删除操作
	 * @param ids
	 * @return
	 */
	int deleteObjects(String... ids);


	/**
	 * 更新用户自身信息以及用户和角色关系数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updateMissionObject(ExpMission entity);
	
	int updateObject(String ...ids);
	int updateRemarkObjects(String missionId,String remarkContent,String remarkSatrap);

	int updateStatusObjects(String missionId,Integer status);
	
	int updateStatusUp(String missionId,Integer status);

	/**
	 * 
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<ExpMission> findPageObjects(
			String username,
			Integer pageCurrent);

	PageObject<ExpMission> findPageAcquires(String username,Integer pageCurrent);
	
	PageObject<ExpMission> findPageByExecutor(String username,Integer status,String meeting,String satrap,String executor, Integer pageCurrent);
	
	List<ExpProgress> findProgressObject(String disId);
	
	
	ExpStatistics findStatisticsObject(String meeting,String timeHead,String timeFoot);

	List<AllCommon> findCommonObject(Integer type);
	
	ExpMission findByIdObject(String missionId);
}
