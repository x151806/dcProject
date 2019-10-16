package com.dc.pro.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.pro.entity.ProProduct;
import com.dc.pro.entity.ProProductUpload;

public interface ProProductDao {
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertObject(ProProduct entity);
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertUpload(ProProductUpload entity);
	
	/**
	 * 更新用户自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(ProProduct entity);
	/**
	 * 下架
	 * @param entity
	 * @return
	 */
	int updateOut(ProProduct entity);
	
	

	
	/**
	 * 查-------询信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<ProProduct> findPageObjects(@Param("username")String username,
			@Param("insurance")String insurance,@Param("status")String status,
			@Param("insuranceType")String insuranceType,@Param("classify")String classify,
			@Param("cooperator")String cooperator,@Param("updateTime")Date updateTime,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 查询更新前的信息
	 * @param satrap
	 * @param executor
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<ProProduct> findOldObjects(@Param("relation")String relation,@Param("productId")String productId);
	/**
	 * 查询更新前的信息条数
	 * @param username
	 * @return
	 */
	int getOldCount(@Param("relation")String relation,@Param("productId")String productId);
	
	/**
	 * 基于用户id查询信息
	 * @param id
	 * @return
	 */
	ProProduct findObjectById(@Param("productId")String productId);
	
	
	/**
	 * 查询条数
	 * @param username
	 * @return
	 */
	int getRowCount(@Param("username")String username,
			@Param("insurance")String insurance,@Param("status")String status,
			@Param("insuranceType")String insuranceType,@Param("classify")String classify,
			@Param("cooperator")String cooperator,@Param("updateTime")Date updateTime);
	
	
	List<ProProductUpload> findUpload(@Param("insurancePlan")String insurancePlan);
}

