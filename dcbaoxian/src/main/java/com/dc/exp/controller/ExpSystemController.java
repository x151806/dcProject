package com.dc.exp.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dc.common.vo.JsonResult;
import com.dc.common.vo.PageObject;
import com.dc.common.vo.PicUploadResult;
import com.dc.exp.entity.ExpProgress;
import com.dc.exp.entity.ExpSystem;
import com.dc.exp.entity.ExpSystemUpload;
import com.dc.exp.service.ExpSystemService;
import com.dc.sys.vo.SysUserDeptVo;



@Controller
@RequestMapping("/submit/")
public class ExpSystemController implements HttpSessionListener{

	@Autowired
	private ExpSystemService expSystemService;




	@RequestMapping("doBugSubmitUI")
	public String dobugSubmitUI(){
		return "exp/system_claimed";
	}
	@RequestMapping("doBugSubmitNoUI")
	public String dobugSubmitNoUI(){
		return "exp/system_unclaimed";
	}

	@RequestMapping("doBugSubmitEditUI")
	public String doBugSubmitEditUI(){
		return "exp/system_edit";
	}
	@RequestMapping("doBugSubmitUpdateUI")
	public String doBugSubmitUpdateUI(){
		return "exp/system_update";
	}
	
	@RequestMapping("doBugSubmitFileUI")
	public String doBugSubmitFileUI(){
		return "exp/system_file";
	}
	
	
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ExpSystem entity,HttpSession session){
		String name = session.getAttribute("username").toString();
		entity.setCreatedUser(name);
		expSystemService.insertObject(entity);
		return new JsonResult("添加成功！");
	}
	
	@RequestMapping("doSaveProgress")
	@ResponseBody
	public JsonResult doSaveProgress(ExpProgress entity,HttpSession session){
		String handlers = session.getAttribute("username").toString();
		entity.setCreatedUser(handlers);
		expSystemService.insertProgressObject(entity);
		return new JsonResult("添加成功");
	}
	
	
	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(
			String...ids){
		int rows=expSystemService.deleteObjects(ids);
		return new JsonResult("删除成功,"+rows+"条被删除！");
	}
	@RequestMapping("deleteByUploadId")
	@ResponseBody
	public JsonResult deleteByUploadId(String uploadId,String site){
		int rows=expSystemService.deleteByUploadId(uploadId,site);
		return new JsonResult("删除成功");
	}
	@RequestMapping("doUpdateObjects")
	@ResponseBody
	public JsonResult doUpdateObjects(
			String ids,String executor,ExpSystem entity){

		entity.setExecutor(executor);
		int rows=expSystemService.doUpdateObjects(ids,entity);
		
		return new JsonResult("分配成功,已分配"+rows+"条记录！");
	}
	@RequestMapping("doUpdateObjectsIt")
	@ResponseBody
	public JsonResult doUpdateObjectsIt(
			String ids,String executor,ExpSystem entity){
		Date d1 = new Date();
		entity.setFixedTime(d1);
		entity.setStatus(2);
		int rows=expSystemService.doUpdateObjects(ids,entity);
		
		return new JsonResult("分配成功,已分配"+rows+"条记录！");
	}
	
	@RequestMapping("doUpdateStatus")
	@ResponseBody
	public JsonResult doUpdateStatus(
			String problemId,Integer status,ExpSystem entity){
		entity.setStatus(status);
		entity.setProblemId(problemId);
		
		int rows=expSystemService.doUpdateStatus(entity);
		
		return new JsonResult("修改成功,"+rows+"条记录被修改！");
	}
	@RequestMapping("updateStatusUp")
	@ResponseBody
	public JsonResult updateStatusUp(
			String problemId,Integer status,ExpSystem entity){
		entity.setStatus(1);
		entity.setProblemId(problemId);
		
		int rows=expSystemService.doUpdateStatus(entity);
		
		return new JsonResult("修改成功,"+rows+"条记录被修改！");
	}
	
	@RequestMapping("doUpdateRemark")
	@ResponseBody
	public JsonResult doUpdateRemark(
			String problemId,String remarkContent,ExpSystem entity,HttpSession session){
		String handlers = session.getAttribute("username").toString();
		entity.setRemarkSatrap(handlers);
		entity.setRemarkContent(remarkContent);
		entity.setProblemId(problemId);
		
		int rows=expSystemService.doUpdateRemark(entity);
		
		return new JsonResult("点评成功");
	}
	@RequestMapping("doUpdateAll")
	@ResponseBody
	public JsonResult doUpdateAll(ExpSystem entity){
		
		int rows=expSystemService.doUpdateAll(entity);
		
		return new JsonResult("修改成功！");
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult findPageObjects(HttpSession session, Integer pageCurrent){
		String username = session.getAttribute("username").toString();
		PageObject<ExpSystem> pageObject=
				expSystemService.findPageSystem(username,
						pageCurrent);
		return new JsonResult(pageObject);
	}
	
	@RequestMapping("findPageAllocating")
	@ResponseBody
	public JsonResult findPageAllocating(HttpSession session, Integer pageCurrent){
		String username = session.getAttribute("username").toString();
		PageObject<ExpSystem> pageObject=
				expSystemService.findPageAllocating(username,
						pageCurrent);
		return new JsonResult(pageObject);
	}
	
	@RequestMapping("findUserNames")
	@ResponseBody
	public JsonResult findUserNames(){
		List<SysUserDeptVo> list = expSystemService.findUserNames();

		return new JsonResult(list);
	}
	@RequestMapping("findSystemObjects")
	@ResponseBody
	public JsonResult findSystemObjects(String disId){
		List<ExpSystemUpload> list = expSystemService.findSystemObjects(disId);
		
		return new JsonResult(list);
	}
	@RequestMapping("doFindProgress")
	@ResponseBody
	public JsonResult doFindProgress(String disId){

		List<ExpProgress> list = expSystemService.findProgressObject(disId);

		return new JsonResult(list);
	}
	
	
	@RequestMapping("findObjectsByDid")
	@ResponseBody
	public JsonResult findObjectsByDid(String problemId){
		return new JsonResult(
				expSystemService.findObjectsByDid(problemId));
	}
	

	



	//实现文件上传
	@RequestMapping("upload")
	@ResponseBody
	public PicUploadResult fileUpload (MultipartFile[] uploadFile,ExpSystemUpload entity,HttpSession session,String disId) {
		
		String handlers = session.getAttribute("username").toString();
		entity.setCreatedUser(handlers);
		entity.setDisId(disId);
		
		
		return expSystemService.upload(uploadFile, entity);
	}

	
	//实现文件下载
	@RequestMapping("download")
	@ResponseBody
    public void download(String filename,String site,
            HttpServletResponse response) throws IOException {
		
		filename=URLDecoder.decode(filename,"utf-8");
		site=URLDecoder.decode(site,"utf-8");

        //模拟文件，myfile.txt为需要下载的文件  
		String path = site;
		 //得到要下载的文件
        File file = new File(path);
        if (!file.exists()) {
        	response.setContentType("text/html; charset=UTF-8");//注意text/html，和application/html
        	response.getWriter().print("<html><body><script type='text/javascript'>alert('您要下载的资源已被删除！');</script></body></html>");
        	response.getWriter().close(); 
            System.out.println("您要下载的资源已被删除！！");  
		}
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data"); 
        // 读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path);
        // 创建输出流
        OutputStream out = response.getOutputStream();
        // 创建缓冲区
        byte buffer[] = new byte[1024]; 
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len = in.read(buffer)) > 0){
        	out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        // 关闭输出流
        out.close();
        
 
    }







}







