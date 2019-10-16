package com.dc.exp.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.common.vo.JsonResult;
import com.dc.common.vo.PageObject;
import com.dc.exp.entity.ExpMission;
import com.dc.exp.entity.ExpProgress;
import com.dc.exp.entity.ExpStatistics;
import com.dc.exp.service.ExpMissionService;
import com.dc.sys.entity.SysReportModWork;
import com.dc.sys.entity.SysUser;
import com.dc.sys.vo.AllCommon;


@Controller
@RequestMapping("/mis/")
public class ExpMissionController implements HttpSessionListener{
	@Autowired
	private ExpMissionService expMissionService;

	@GetMapping("doUnclaimedListUI")
	public String doLogListUI(){
		return "sys/unclaimed";
	}
	@GetMapping("doReceiveListUI")
	public String doLogEditUI(){
		return "sys/receive";
	}
	@GetMapping("doReportEditUI")
	public String doLogUI(){
		return "sys/unclaimed_edits";
	}
	@GetMapping("doReportUpdate")
	public String doLog(){
		return "sys/unclaimed_update";
	}
	@GetMapping("doStatisticsUI")
	public String doStatisticsUI(){
		return "sys/receive-statistics";
	}





	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ExpMission entity,HttpSession session){
		String name = session.getAttribute("username").toString();
		entity.setSatrap(name);
		expMissionService.saveObject(entity);
		return new JsonResult("添加成功！");
	}

	@RequestMapping("doSaveProgress")
	@ResponseBody
	public JsonResult doSaveProgress(ExpProgress entity,HttpSession session){
		String handlers = session.getAttribute("username").toString();
		entity.setCreatedUser(handlers);
		expMissionService.insertProgressObject(entity);
		return new JsonResult("添加成功");
	}




	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(
			String ...ids){
		int rows=expMissionService.deleteObjects(ids);
		return new JsonResult("删除成功,"+rows+"条记录被删除");
	}


	@PostMapping("updateMissionObject")
	@ResponseBody
	public JsonResult updateMissionObject(ExpMission entity,
			HttpSession session){
		String createdUser = session.getAttribute("username").toString();
		entity.setSatrap(createdUser);

		expMissionService.updateMissionObject(entity);
		return new JsonResult("修改成功！");
	}


	@RequestMapping("doUpdateObjects")
	@ResponseBody
	public JsonResult doUpdateObjects(String... ids){
		int rows=expMissionService.updateObject(ids);
		return new JsonResult("领取成功,"+rows+"条记录被领取");
	}

	@RequestMapping("doUpdateRemark")
	@ResponseBody
	public JsonResult updateRemarkObjects(String missionId, String remarkContent,HttpSession session){
		String remarkSatrap = session.getAttribute("username").toString();

		int rows=expMissionService.updateRemarkObjects(missionId,remarkContent,remarkSatrap);
		return new JsonResult("点评成功！");
	}

	@RequestMapping("doUpdateStatus")
	@ResponseBody
	public JsonResult updateStatusObjects(String missionId, Integer status){

		int rows=expMissionService.updateStatusObjects(missionId,status);
		return new JsonResult("修改成功！");
	}

	@RequestMapping("updateStatusUp")
	@ResponseBody
	public JsonResult updateStatusUp(String missionId, Integer status){
		status=1;
		int rows=expMissionService.updateStatusUp(missionId,status);
		return new JsonResult("修改成功！");
	}
	



















	@RequestMapping("findByIdObject")
	@ResponseBody
	public JsonResult findByIdObject(String missionId){
		return new JsonResult(
				expMissionService.findByIdObject(missionId));
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult findPageObjects(HttpSession session, Integer pageCurrent){
		String username = session.getAttribute("username").toString();
		PageObject<ExpMission> pageObject=
				expMissionService.findPageObjects(username,
						pageCurrent);
		return new JsonResult(pageObject);
	}

	@RequestMapping("doFindPageAcquires")
	@ResponseBody
	public JsonResult doFindPageAcquires(HttpSession session, Integer pageCurrent){
		String username = session.getAttribute("username").toString();

		PageObject<ExpMission> pageObject=
				expMissionService.findPageAcquires(username,
						pageCurrent);
		return new JsonResult(pageObject);
	}
	@RequestMapping("doFindPageByExecutor")
	@ResponseBody
	public JsonResult findPageByExecutor(HttpSession session,Integer status,String meeting,String satrap,String executor, Integer pageCurrent){
		String username = session.getAttribute("username").toString();
		PageObject<ExpMission> pageObject=expMissionService.findPageByExecutor(username,status,meeting,satrap,executor,pageCurrent);
		return new JsonResult(pageObject);
	}

	@RequestMapping("doFindProgress")
	@ResponseBody
	public JsonResult doFindProgress(String disId){

		List<ExpProgress> list = expMissionService.findProgressObject(disId);

		return new JsonResult(list);
	}

	@RequestMapping("doFindStatistics")
	@ResponseBody
	public JsonResult doFindStatistics(String meeting,String timeHead, String timeFoot){

		ExpStatistics statistics = expMissionService.findStatisticsObject(meeting,timeHead,timeFoot) ;

		return new JsonResult(statistics);
	}


	@RequestMapping("findCommonObject")
	@ResponseBody
	public JsonResult findCommonObject(Integer type){

		List<AllCommon> list = expMissionService.findCommonObject(type);

		return new JsonResult(list);
	}



}







