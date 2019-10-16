package com.dc.exp.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CreatIDUtil;
import com.dc.common.vo.PageObject;
import com.dc.exp.dao.ExpExploitDocumentDao;
import com.dc.exp.dao.ExpMissionDao;
import com.dc.exp.dao.ExpShareDao;
import com.dc.exp.entity.ExpExploit;
import com.dc.exp.entity.ExpExploitDocument;
import com.dc.exp.entity.ExpMission;
import com.dc.exp.entity.ExpProgress;
import com.dc.exp.entity.ExpShare;
import com.dc.exp.entity.ExpStatistics;
import com.dc.exp.service.ExpMissionService;
import com.dc.exp.service.ExpShareService;
import com.dc.sys.vo.AllCommon;
@Service
public class ExpShareServiceImpl implements ExpShareService{

	@Autowired
	private ExpShareDao expShareDao;
	@Autowired
	private ExpMissionDao expMissionDao;
	@Autowired
	private ExpExploitDocumentDao expExploitDocumentDao;

	@Override
	public int saveShare(ExpShare entity) {
		//1.对参数进行校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getTopic()))
			throw new IllegalArgumentException("名称不能为空");
		
		CreatIDUtil uid = new CreatIDUtil();
		String details = entity.getSort();
		Integer type = 3;
		int sum = expMissionDao.findCommonComb(type,details);
		if(sum==0) {
			AllCommon ent = new AllCommon();
			String cid = uid.getNextId();
			ent.setId(cid);
			ent.setDetails(details);
			ent.setType(3);
			int a = expMissionDao.insertCommon(ent);
		}



		String repId = uid.getNextId();
		entity.setShareId(repId);
		//2.将数据持久化到数据库
		int rows=expShareDao.insertobject(entity);
		//3.返回结果
		return rows;
	}

	@Override
	public int deleteShare(String shareId) {
		int num = expExploitDocumentDao.getFindByDid(shareId);
		if(num>0)
			throw new IllegalArgumentException("该数据在系统文档内还有文档，为了避免误删，请先删除文档后才能删除数据！");

		int row=expShareDao.deleteObjects(shareId);

		//3.对删除结果进行校验
		if(row==0)
			throw new ServiceException("记录可能已经不存在");

		return row;
	}

	@Override
	public int updateShare(ExpShare entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getTopic()))
			throw new IllegalArgumentException("名称不能为空");

		//3.保存用户与角色关系数据
		int rows =expShareDao.updateObjects(entity); 
		//4.返回结果
		return rows;
	}

	@Override
	public PageObject<ExpShare> findShare(String sort,String topic,String author, Integer pageCurrent) {
		//1.对参数进行有效验证
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数
		int rowCount=expShareDao.getRowCount(sort,topic,author);
		//3.对总记录数进行校验
		if(rowCount==0)
			throw new ServiceException("没有对应记录");
		//4.基于用户名,当前页码等信息查询当前页记录
		int pageSize=10;
		int startIndex=(pageCurrent-1)*pageSize;
		List<ExpShare> records=
				expShareDao.findPageObjects(sort,topic,author,startIndex,pageSize); 
		//5.封装查询结果。
		PageObject<ExpShare> po=new PageObject<ExpShare>();
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
	public ExpShare findShareById(String shareId) {
		return expShareDao.findObjectsById(shareId);
	}

	@Override
	public List<ExpShare> findAuthor() {

		return expShareDao.findAuthor();
	}




}
