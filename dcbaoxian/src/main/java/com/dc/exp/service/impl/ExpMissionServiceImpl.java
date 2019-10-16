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
import com.dc.exp.dao.ExpMissionDao;
import com.dc.exp.entity.ExpMission;
import com.dc.exp.entity.ExpProgress;
import com.dc.exp.entity.ExpStatistics;
import com.dc.exp.service.ExpMissionService;
import com.dc.sys.vo.AllCommon;
@Service
public class ExpMissionServiceImpl implements ExpMissionService{

	@Autowired
	private ExpMissionDao expMissionDao;


	@Override
	public int saveObject(ExpMission entity) {
		//1.对参数进行校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		
		if(StringUtils.isEmpty(entity.getMeeting()))
			throw new IllegalArgumentException("会议类型不能为空");
		if(StringUtils.isEmpty(entity.getTask()))
			throw new IllegalArgumentException("任务内容不能为空");
		//.......
		CreatIDUtil uid = new CreatIDUtil();
		String details = entity.getMeeting();
		Integer type = 1;
		int sum = expMissionDao.findCommonComb(type,details);
		if(sum==0) {
			AllCommon ent = new AllCommon();
			String cid = uid.getNextId();
			ent.setId(cid);
			ent.setDetails(details);
			ent.setType(1);
			int a = expMissionDao.insertCommon(ent);
		}

		String repId = uid.getNextId();
		String aa="";
		entity.setMissionId(repId);
		entity.setRemarkContent(aa);
		entity.setRemarkSatrap(aa);
		//2.将数据持久化到数据库
		int rows=expMissionDao.insertobject(entity);
		//3.返回结果
		return rows;
	}

	@Override
	public int deleteObjects(String... ids) {
		//1.参数合法性校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("请先选择");
		//2.执行删除操作
		int rows=-1;
		try{
			rows=expMissionDao.deleteObjects(ids);
			expMissionDao.deleteProgress(ids);
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
	public int updateObject(String... ids) {
		//1.参数合法性校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("请先选择");
		//2.执行删除操作
		int rows=-1;
		rows=expMissionDao.updateObjects(ids);
		//3.对删除结果进行校验
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		//4.返回结果
		return rows;
	}

	@Override
	public PageObject<ExpMission> findPageObjects(String username,Integer pageCurrent) {
		//1.对参数进行有效验证
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数
		int rowCount=expMissionDao.getRowCount(username);
		//3.对总记录数进行校验
		if(rowCount==0)
			throw new ServiceException("没有对应记录");
		//4.基于用户名,当前页码等信息查询当前页记录
		int pageSize=15;
		int startIndex=(pageCurrent-1)*pageSize;
		List<ExpMission> records=
				expMissionDao.findPageObjects(username,startIndex,pageSize);
		//5.封装查询结果。
		PageObject<ExpMission> po=new PageObject<ExpMission>();
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
	public PageObject<ExpMission> findPageAcquires(String username,Integer pageCurrent) {
		//1.对参数进行有效验证
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数
		int rowCount=expMissionDao.getAcquireCount(username);
		//3.对总记录数进行校验
		if(rowCount==0)
			throw new ServiceException("没有对应记录");
		//4.基于用户名,当前页码等信息查询当前页记录
		int pageSize=15;
		int startIndex=(pageCurrent-1)*pageSize;
		List<ExpMission> records=
				expMissionDao.findPageAcquire(username,startIndex,pageSize);
		//5.封装查询结果。
		PageObject<ExpMission> po=new PageObject<ExpMission>();
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
	public int insertProgressObject(ExpProgress entity) {
		//1.对参数进行校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		CreatIDUtil uid = new CreatIDUtil();
		String repId = uid.getNextId();
		entity.setProgressId(repId);
		int rows=expMissionDao.insertProgress(entity);

		return rows;
	}

	@Override
	public List<ExpProgress> findProgressObject(String disId) {
		List<ExpProgress> list = expMissionDao.findProgressById(disId);
		if(list.size()<1)
			throw new IllegalArgumentException("没有对应记录");
		return list;
	}

	@Override
	public int updateRemarkObjects(String missionId, String remarkContent, String remarkSatrap) {
		//1.参数合法性校验
		if(StringUtils.isEmpty(remarkContent))
			throw new IllegalArgumentException("点评内容不能为空");

		CreatIDUtil uid = new CreatIDUtil();
		String details = remarkContent;
		Integer type = 2;
		int sum = expMissionDao.findCommonComb(type,details);
		if(sum==0) {
			AllCommon ent = new AllCommon();
			String cid = uid.getNextId();
			ent.setId(cid);
			ent.setDetails(details);
			ent.setType(2);
			expMissionDao.insertCommon(ent);
		}


		int rows=-1;
		rows=expMissionDao.updateRemark(missionId,remarkContent,remarkSatrap);
		//3.对删除结果进行校验
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		//4.返回结果
		return rows;
	}

	@Override
	public int updateStatusObjects(String missionId, Integer status) {
		//1.参数合法性校验
		if(StringUtils.isEmpty(status))
			throw new IllegalArgumentException("项目阶段不能为空");
		//2.执行删除操作
		int rows=-1;
		rows=expMissionDao.updateStatus(missionId,status);
		//3.对删除结果进行校验
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		//4.返回结果
		return rows;
	}
	@RequiresPermissions("exp:mission:statusup")
	@Override
	public int updateStatusUp(String missionId, Integer status) {

		//2.执行删除操作
		int rows=-1;
		rows=expMissionDao.updateStatus(missionId,status);
		//3.对删除结果进行校验
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		//4.返回结果
		return rows;
	}


	@Override
	public ExpStatistics findStatisticsObject(String meeting,String timeHead, String timeFoot) {
		if(StringUtils.isEmpty(meeting))
			throw new IllegalArgumentException("早会内容不能为空");

		List<ExpMission> list = expMissionDao.findStatus(meeting,timeHead,timeFoot);
		if(list.size()<1)
			throw new IllegalArgumentException("没有对应记录");
		ExpStatistics statistics = new ExpStatistics();
		Integer oneTwo=0;
		Integer three=0;
		Integer four=0;
		Integer five=0;
		Integer six=0;
		Integer seven=0;

		for (ExpMission exp : list) {
			switch(exp.getStatus()){
			case 1:
				oneTwo++;
				break;
			case 2:
				oneTwo++;
				break;
			case 3:
				three++;
				break;
			case 4:
				four++;
				break;
			case 5:
				five++;
				break;
			case 6:
				six++;
				break;
			case 7:
				seven++;
				break;
			default:
				throw new ServiceException("有异常数据！");
			}
		}

		statistics.setStageTwo(oneTwo);
		statistics.setStageThree(three);
		statistics.setStageFour(four);
		statistics.setStageFive(five);
		statistics.setStageSix(six);
		statistics.setStageSeven(seven);

		return statistics;
	}

	@Override
	public List<AllCommon> findCommonObject(Integer type) {
		return expMissionDao.findCommon(type);
	}

	@Override
	public int updateMissionObject(ExpMission entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getMeeting()))
			throw new IllegalArgumentException("会议类型不能为空");
		if(StringUtils.isEmpty(entity.getAppointedTime()))
			throw new IllegalArgumentException("指定时间不能为空");
		if(StringUtils.isEmpty(entity.getTask()))
			throw new IllegalArgumentException("任务内容不能为空");
		if(StringUtils.isEmpty(entity.getExecutor()))
			throw new IllegalArgumentException("执行人不能为空");
		Date d1 = new Date();
		entity.setCreatedTime(d1);
		//2.更新用户自身信息
		int rows=expMissionDao.updateMission(entity);
		//3.保存用户与角色关系数据

		//4.返回结果
		return rows;
	}

	@Override
	public ExpMission findByIdObject(String missionId) {
		return expMissionDao.findObjectById(missionId);
	}

	@Override
	public PageObject<ExpMission> findPageByExecutor(String username,Integer status,String meeting,String satrap,String executor,Integer pageCurrent) {
		//1.对参数进行有效验证
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数
		int rowCount=expMissionDao.getAcquireByExecutor(username,meeting,satrap,executor,status);
		
		//3.对总记录数进行校验
		if(rowCount==0)
			throw new ServiceException("没有对应记录");
		//4.基于用户名,当前页码等信息查询当前页记录
		int pageSize=15;
		int startIndex=(pageCurrent-1)*pageSize;
		List<ExpMission> records=
				expMissionDao.findPageByExecutor(username,meeting,satrap,executor,status,
						startIndex,pageSize);
		
		//5.封装查询结果。
		PageObject<ExpMission> po=new PageObject<ExpMission>();
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
