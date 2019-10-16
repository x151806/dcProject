package com.dc.sys.service.impl;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.common.exception.ServiceException;
import com.dc.common.vo.PageObject;
import com.dc.sys.dao.SysLogDao;
import com.dc.sys.entity.SysLog;
import com.dc.sys.service.SysLogService;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;
	/**
	 * 通过@RequiresPermissions注解对
	 * 如下方法进行描述，表示访问此方法
	 * 需要进行授权操作，认证用户必须具备
	 * “sys:log:delete”权限标识才能访问
	 * 此方法。
	 */
	@RequiresPermissions("sys:log:delete")
	@Override
	public int deleteObjects(Integer... ids) {
		//1.参数合法性校验
		if(ids==null||ids.length==0)
		throw new IllegalArgumentException("请先选择");
		//2.执行删除操作
		int rows=-1;
		try{
		 rows=sysLogDao.deleteObjects(ids);
		}catch(Throwable e){
		 e.printStackTrace();
		 //给运维发短信
	     throw new RuntimeException("服务故障，恢复中");
		}
		//3.对删除结果进行校验
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		//4.返回结果
		return rows;
	}
	
	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		//1.对参数进行有效验证
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数
		int rowCount=sysLogDao.getRowCount(username);
		//3.对总记录数进行校验
		if(rowCount==0)
		throw new ServiceException("没有对应记录");
		//4.基于用户名,当前页码等信息查询当前页记录
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysLog> records=
		sysLogDao.findPageObjects(username,
				  startIndex,
				  pageSize);
		//5.封装查询结果。
		PageObject<SysLog> po=new PageObject<SysLog>();
		po.setRecords(records);
		po.setRowCount(rowCount);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
		/*int pageCount=rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pageCount++;
		}*/
		po.setPageCount((rowCount-1)/pageSize+1);
		
		//6.返回结果
		return po;
	}

}





