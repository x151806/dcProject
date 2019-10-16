package com.dc.sys.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CreatIDUtil;
import com.dc.common.vo.PageObject;
import com.dc.sys.dao.SysReportDao;
import com.dc.sys.entity.SysMenu;
import com.dc.sys.entity.SysReport;
import com.dc.sys.entity.SysReportModComment;
import com.dc.sys.entity.SysReportModWork;
import com.dc.sys.service.SysReportService;
import com.dc.sys.vo.SysReportNameVo;
import com.dc.sys.vo.SysUserDeptVo;
@Service
public class SysReportServiceImpl implements SysReportService {
	@Autowired
	private SysReportDao sysReportDao;

	@Override
	public PageObject<SysReportNameVo> findPageObjects(Integer userId, Integer pageCurrent) {
		//1.对参数进行有效验证
				if(pageCurrent==null||pageCurrent<1)
				throw new IllegalArgumentException("当前页码值不正确");
				//2.基于用户名查询总记录数
				int rowCount=sysReportDao.getRowCount(userId);
				//3.对总记录数进行校验
				if(rowCount==0)
				throw new ServiceException("没有对应记录");
				//4.基于用户名,当前页码等信息查询当前页记录
				int pageSize=10;
				int startIndex=(pageCurrent-1)*pageSize;
				List<SysReportNameVo> records=
						sysReportDao.findUserByUserId(userId,
						  startIndex,
						  pageSize);
				//5.封装查询结果。
				PageObject<SysReportNameVo> po=new PageObject<SysReportNameVo>();
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

	@Override
	public int saveObject(SysReport entity) {
		//1.对参数进行校验
        if(entity==null)
        throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getWorkToday()))
    		throw new IllegalArgumentException("今日工作内容不能为空");
        if(StringUtils.isEmpty(entity.getWorkNextDay()))
        	throw new IllegalArgumentException("之后工作计划不能为空");
        CreatIDUtil uid = new CreatIDUtil();
        String repId = uid.getNextId();
        entity.setReportId(repId);
        int rows=sysReportDao.insertObject(entity);   
        if(rows==1) {
        	sysReportDao.updateContent(repId);
            sysReportDao.updateExplain(repId);
        }
        
		return rows;
	}

	

	@Override
	public int deleteObject(String reportId) {
		//参数有效性验证
    	if(reportId==null)
    	throw new IllegalArgumentException("id值无效");
    	//删除菜单自身信息
    	int rowCount=sysReportDao.deleteObject(reportId);
    	if(rowCount==0)
    	throw new ServiceException("记录可能已经不存在");
    	//返回结果结果信息
    	return rowCount;
	}

	@Override
	public int changeAvatar(String reportId, String avatar) {
		 int rows = sysReportDao.updateAvatar(reportId, avatar);
		return rows;
	}
	
	

	@Override
	public List<SysReportModWork> findModWorkObjects(String reportId) {
		return sysReportDao.findModWorkById(reportId);
	}

	@Override
	public int saveModWorkObject(SysReportModWork entity,String handlers,String master) {
		//1.对参数进行校验
		if(!handlers.equals(master))
	        throw new ServiceException("对不起，你不能修改其他人的日志！");
        if(entity==null)
        throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getModifterWork()))
    		throw new IllegalArgumentException("内容不能为空");
        CreatIDUtil uid = new CreatIDUtil();
        String repId = uid.getNextId();
        entity.setModifierId(repId);
        int rows=sysReportDao.insertModWork(entity);
        
		return rows;
	}

	@Override
	public int saveModCommentObject(SysReportModComment entity) {
		//1.对参数进行校验
        if(entity==null)
        throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getModifterComment()))
    		throw new IllegalArgumentException("内容不能为空");
        CreatIDUtil uid = new CreatIDUtil();
        String repId = uid.getNextId();
        entity.setModCommentId(repId);
        int rows=sysReportDao.insertModComment(entity);
        
		return rows;
	}

	@Override
	public List<SysReportModComment> findModCommentObjects(String reportId) {
		return sysReportDao.findModCommentById(reportId);
	}

	@Override
	public List<SysReportNameVo> findReportObjects(String reportId) {
		return sysReportDao.findReportById(reportId);
	}

	@Override
	public List<SysUserDeptVo> findDownObjects(Integer userId) {
		
		return sysReportDao.findDown(userId);
	}

	@Override
	public List<SysReportNameVo> findByTimeObjects(Integer userId, Date workTime) {
		
		
		
		
		
		return sysReportDao.findReportByTime(userId,workTime);
	}
	
	
	

}
