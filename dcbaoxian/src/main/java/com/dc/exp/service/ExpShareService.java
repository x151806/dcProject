package com.dc.exp.service;

import java.util.List;

import com.dc.common.vo.PageObject;
import com.dc.exp.entity.ExpExploitDocument;
import com.dc.exp.entity.ExpShare;

public interface ExpShareService {
	
	
	

	/**
	 * 插入下载列表
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveShare(ExpShare entity);
	
	
	
	/**
	 * 基于id执行删除操作
	 * @param ids
	 * @return
	 */
	int deleteShare(String shareId);
	
	
	/**
	 * 基于id执行修改操作
	 * @param ids
	 * @return
	 */
	int updateShare(ExpShare entity);
	
	
	
	
	/**
	 * 查询分享人
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	List<ExpShare> findAuthor();
	
	/**
	 * 查询
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	ExpShare findShareById(String shareId);
	
	
	/**
	 * 查询
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<ExpShare> findShare(String sort,String topic,String author,Integer pageCurrent);
	
	

}
