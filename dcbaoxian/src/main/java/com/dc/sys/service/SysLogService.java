package com.dc.sys.service;

import com.dc.common.vo.PageObject;
import com.dc.sys.entity.SysLog;

/**
 * 此接口为日志业务接口，在此接口的方法中
 * 处理相关用户行为日志信息。
 */
public interface SysLogService {
	
	  /**
	   * 基于id执行删除操作
	   * @param ids
	   * @return
	   */
	  int deleteObjects(Integer... ids);
	
      /**
       * 查询当前页记录以及总记录数并计算总页数
       * 然后对结果进行封装。
       * @param username 查询条件(用户名)
       * @param pageCurrent 当前页码值
       * @return
       */
	  PageObject<SysLog> findPageObjects(
			  String username,
			  Integer pageCurrent);
	
}







