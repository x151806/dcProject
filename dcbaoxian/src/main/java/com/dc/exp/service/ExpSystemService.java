package com.dc.exp.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.dc.common.vo.PageObject;
import com.dc.common.vo.PicUploadResult;
import com.dc.exp.entity.ExpMission;
import com.dc.exp.entity.ExpProgress;
import com.dc.exp.entity.ExpSystem;
import com.dc.exp.entity.ExpSystemUpload;
import com.dc.sys.vo.SysUserDeptVo;

public interface ExpSystemService {
	/**
	 * 上传
	 * @param uploadFile
	 * @param reportId
	 * @return
	 */
	PicUploadResult upload(MultipartFile[] uploadFile,ExpSystemUpload entity);
	
	

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertObject(ExpSystem entity);
	
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
	
	
	
	int deleteByUploadId(String uploadId,String site);
	
	
	
	int doUpdateObjects(String ids,ExpSystem entity);
	
	
	int doUpdateStatus(ExpSystem entity);
	
	
	int doUpdateRemark(ExpSystem entity);
	
	
	int doUpdateAll(ExpSystem entity);
	
	
	
	
	/**
	 * 查询信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	PageObject<ExpSystem> findPageSystem(String username,Integer pageCurrent);
	/**
	 * 查询信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	PageObject<ExpSystem> findPageAllocating(String username,Integer pageCurrent);
	
	List<ExpProgress> findProgressObject(String disId);
	
	List<SysUserDeptVo> findUserNames();
	
	List<ExpSystemUpload> findSystemObjects(String disId);
	
	ExpSystem findObjectsByDid(String problemId);

}
