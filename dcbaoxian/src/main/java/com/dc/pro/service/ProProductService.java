package com.dc.pro.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.dc.common.vo.PageObject;
import com.dc.common.vo.PicUploadResult;
import com.dc.pro.entity.ProProduct;
import com.dc.pro.entity.ProProductUpload;

public interface ProProductService {
	
	/**
	 * 上传
	 * @param uploadFile
	 * @param reportId
	 * @return
	 */
	PicUploadResult upload(MultipartFile[] uploadFile,ProProductUpload entity);
	/**
	 * 更新上传
	 * @param uploadFile
	 * @param reportId
	 * @return
	 */
	PicUploadResult uploadUpdate(MultipartFile[] uploadFile,ProProductUpload entity);

	/**
	 * 保存用户自身信息以及用户和角色关系数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObject(ProProduct entity);
	/**
	 * 更新数据时添加
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveUpObject(ProProduct entity,String upId);

	


	/**
	 * 更新用户自身信息以及用户和角色关系数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updateProduct(ProProduct entity);
	/**
	 * 下架
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updateOut(ProProduct entity);
	

	/**
	 * 
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<ProProduct> findPageObjects(
			String username,String insurance,String status,String insuranceType,String classify,
			String cooperator,Date updateTime,
			Integer pageCurrent);
	
	List<ProProductUpload> findUploads(String insurancePlan);
	
	
	ProProduct findObjectById(String productId);
	
	
	List<ProProduct> findOldObjects(String relation,String productId);

	
}
