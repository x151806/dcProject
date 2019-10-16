package com.dc.exp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.exp.entity.ExpShare;

public interface ExpShareDao {
	
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertobject(ExpShare entity);
	
	
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	int deleteObjects(@Param("shareId")String shareId);
	
	
	/**
	 * 更新用户自身信息
	 * @param entity
	 * @return
	 */
	int updateObjects(ExpShare entity);
	
	/**
	 * 通过Id查找信息
	 * @param shareId
	 * @return
	 */
	ExpShare findObjectsById(@Param("shareId")String shareId);
	
	/**
	 * 查询信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<ExpShare> findPageObjects(
			@Param("sort")String sort,
			@Param("topic")String topic,
			@Param("author")String author,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 查询分享人
	 * @return
	 */
	List<ExpShare> findAuthor();
	
	
	/**
	 * 查询条数
	 * @param username
	 * @return
	 */
	int getRowCount(
			@Param("sort")String sort,
			@Param("topic")String topic,
			@Param("author")String author);



	
	
	
	
	
	
	

}
