package com.dc.exp.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.exp.entity.ExpMission;
import com.dc.exp.entity.ExpProgress;
import com.dc.exp.entity.ExpSystem;
import com.dc.exp.entity.ExpSystemUpload;
import com.dc.sys.vo.SysUserDeptVo;

public interface ExpSystemDao {
	
	/**
	 * 上传图片
	 * @param reportId
	 * @param avatar
	 * @return
	 */
	int updateAvatar(ExpSystemUpload entity);
	
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertSystem(ExpSystem entity);
	
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
	int deleteUpload(@Param("uploadId")String uploadId);
	
	int deleteUploadByDid(@Param("disId")String disId);
	/**
	 *  修改信息
	 * @param entity
	 * @return
	 */
	int updateFinishTime(@Param("problemId")String problemId);
	
	/**
	 * 基于列表id查找用户修改的信息
	 * @param username
	 * @return
	 */
	List<ExpProgress> findProgressById(@Param("disId")String disId);
	
	List<ExpSystemUpload> findPageUpload(@Param("disId")String disId);
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	int deleteSystem(@Param("ids")String ...ids);
	

	/**
	 * 更新用户自身信息
	 * @param entity
	 * @return
	 */
	int updateSystem(ExpSystem entity);
	
	ExpSystem findByDid(@Param("problemId")String problemId);
	
	List<ExpSystemUpload> findUpload(@Param("uploadId")String uploadId);
	
	List<ExpSystemUpload> findUploadByDid(@Param("disId")String disId);
	
	/**
	 * 查询信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<ExpSystem> findPageSystem(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	

	/**
	 * 查询条数
	 * @param username
	 * @return
	 */
	int getRowCount(@Param("username")String username);
	
	List<SysUserDeptVo> findUserName();
	/**
	 * 查询信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<ExpSystem> findPageAllocating(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 查询条数
	 * @param username
	 * @return
	 */
	int getAllocatingCount(@Param("username")String username);
	

}
