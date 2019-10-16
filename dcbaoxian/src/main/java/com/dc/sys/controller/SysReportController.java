package com.dc.sys.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.common.vo.JsonResult;
import com.dc.common.vo.PageObject;
import com.dc.sys.entity.SysReport;
import com.dc.sys.entity.SysReportModComment;
import com.dc.sys.entity.SysReportModWork;
import com.dc.sys.service.SysReportService;
import com.dc.sys.vo.SysReportNameVo;
import com.dc.sys.vo.SysUserDeptVo;

@Controller
@RequestMapping("/report/")
public class SysReportController{
	@Autowired
	private SysReportService sysReportService;

	/*@Autowired
	@Qualifier("sysLogService")
	public void setSysLogService(SysLogService aaa) {
		this.sysLogService = aaa;
	}*/


	@GetMapping("doReportListUI")
	public String doLogListUI(){
		return "sys/report";
	}
	@GetMapping("doReportEditUI")
	public String doLogEditUI(){
		return "sys/report_edit";
	}
	@GetMapping("doReportUI.do")
	public String doReportUI(){
		return "sys/report_detailed";
	}
	@GetMapping("doReportModWorkUI.do")
	public String doReportModWorkUI(){
		return "sys/report_modWork";
	}
	@GetMapping("doReportModCommentUI.do")
	public String doReportModCommentUI(){
		return "sys/report_modComment";
	}


	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysReport entity,HttpSession session){
		Integer userId = Integer.valueOf(session.getAttribute("id").toString());
		String createdUser = session.getAttribute("username").toString();
		entity.setUserId(userId);
		entity.setCreatedUser(createdUser);
		sysReportService.saveObject(entity);
		return new JsonResult("添加成功");
	}

	@RequestMapping("doSaveModWork")
	@ResponseBody
	public JsonResult doSaveModWork(SysReportModWork entity,String handlers,String master,HttpSession session){
		handlers = session.getAttribute("username").toString();
		entity.setCreatedUser(handlers);
		sysReportService.saveModWorkObject(entity,handlers,master);
		return new JsonResult("添加成功");
	}

	@RequestMapping("doSaveModComment")
	@ResponseBody
	public JsonResult doSaveModComment(SysReportModComment entity,HttpSession session){
		String createdUser = session.getAttribute("username").toString();
		entity.setCreatedUser(createdUser);
		sysReportService.saveModCommentObject(entity);
		return new JsonResult("添加成功");
	}




	@RequestMapping("doDeleteObject")
	@ResponseBody 
	public JsonResult doDeleteObject(
			String  reportId){
		sysReportService.deleteObject(reportId);
		return new JsonResult("删除成功");
	}







	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer userId,
			Integer pageCurrent,HttpSession session){

		userId = Integer.valueOf(session.getAttribute("id").toString());
		PageObject<SysReportNameVo> po=
				sysReportService.findPageObjects(   
						userId,pageCurrent);
		return new JsonResult(po);
	}

	@RequestMapping("doFindDownPageObjects")
	@ResponseBody
	public JsonResult doFindDownPageObjects(Integer userId,
			Integer pageCurrent){

		PageObject<SysReportNameVo> po=
				sysReportService.findPageObjects(   
						userId,pageCurrent);
		return new JsonResult(po);
	}

	@RequestMapping("findByTimeObjects")
	@ResponseBody
	public JsonResult findByTimeObjects(Integer userId, Date workTime,HttpSession session){
		userId = Integer.valueOf(session.getAttribute("id").toString());
		
		List<SysReportNameVo> list = sysReportService.findByTimeObjects(userId,workTime);

		return new JsonResult(list);
	}
	@RequestMapping("doFindDown")
	@ResponseBody
	public JsonResult doFindDown(Integer userId,HttpSession session){
		userId = Integer.valueOf(session.getAttribute("id").toString());

		List<SysUserDeptVo> list = sysReportService.findDownObjects(userId);

		return new JsonResult(list);
	}
	@RequestMapping("doFindReport")
	@ResponseBody
	public JsonResult doFindReport(String reportId){

		List<SysReportNameVo> list = sysReportService.findReportObjects(reportId);

		return new JsonResult(list);
	}

	@RequestMapping("doFindModWork")
	@ResponseBody
	public JsonResult doFindModWork(String reportId){

		List<SysReportModWork> list = sysReportService.findModWorkObjects(reportId);

		return new JsonResult(list);
	}

	@RequestMapping("doFindModComment")
	@ResponseBody
	public JsonResult doFindModComment(String reportId){
		List<SysReportModComment> list = sysReportService.findModCommentObjects(reportId);

		return new JsonResult(list);
	}

	
	
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }



}





