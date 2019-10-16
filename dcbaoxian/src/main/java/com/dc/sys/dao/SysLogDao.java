package com.dc.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dc.sys.entity.SysLog;

public interface SysLogDao {
	int insertObject(SysLog entity);
	List<SysLog> findObjects();
	
	/**
	 * 基于多个id删除日志信息
	 * @param ids 封装了多个日志记录的id
	 * @return 删除的记录行数
	 */
	int deleteObjects(@Param("ids")Integer ...ids);

    /**
     * 基于查询条件查询数据库中总记录数
     * @param username 查询条件
     * @return 总记录数
     */
	int getRowCount(@Param("username")String username);
	/**
	 * 基于查询条件查询当前页要呈现的数据
	 * @param username
	 * @param startIndex
	 * @param pageSize
	 * @return 当前页数据(日志信息)
	 */
	List<SysLog> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
}
