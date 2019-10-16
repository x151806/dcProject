package com.dc.pro.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dc.common.vo.JsonResult;
import com.dc.common.vo.PageObject;
import com.dc.common.vo.PicUploadResult;
import com.dc.pro.entity.ProProduct;
import com.dc.pro.entity.ProProductUpload;
import com.dc.pro.service.ProProductService;
import com.dc.sys.entity.SysReportModComment;



@Controller
@RequestMapping("/product/")
public class proProductController implements HttpSessionListener{


	@Autowired
	private ProProductService proProductService;



	@RequestMapping("doProductUI01")
	public String doDevelopUI01(){
    		return "pro/product01";
		
	}
	@RequestMapping("doProductUI")
	public String doDevelopUI(){
		
			return "pro/product";
	
	}
	
	@RequestMapping("doProductParticulars01")
	public String doProductParticulars01(){
		
    		return "pro/product_particulars01";
    	
	}
	@RequestMapping("doProductParticulars")
	public String doProductParticulars(){
		
			return "pro/product_particulars";
		
		
	}
	
	
	@RequestMapping("doProductEditUI")
	public String doProductEditUI(){
		return "pro/product_edit";
	}
	
	@RequestMapping("doProductUpdate")
	public String doProductUpdate(){
		return "pro/product_update";
	}
	@RequestMapping("doProductAmend")
	public String doProductAmend(){
		return "pro/product_amend";
	}
	
	
	
	
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ProProduct entity){
		proProductService.saveObject(entity);
		return new JsonResult("添加成功！");
	}
	
	@RequestMapping("doSaveUpObject")
	@ResponseBody
	public JsonResult doSaveUpObject(ProProduct entity,String upId){
		proProductService.saveUpObject(entity,upId);
		return new JsonResult("更新成功！");
	}
	
	
	@RequestMapping("updateProduct")
	@ResponseBody
	public JsonResult updateProduct(ProProduct entity){
		
		int rows=proProductService.updateProduct(entity);
		return new JsonResult("修改成功！");
	}
	@RequestMapping("updateOut")
	@ResponseBody
	public JsonResult updateOut(ProProduct entity,String productId){
		entity.setProductId(productId);
		int rows=proProductService.updateOut(entity);
		return new JsonResult("修改成功！");
	}
	
	@RequestMapping("findObjects")
	@ResponseBody
	public JsonResult findObjects(String username, String insurance, String status, String insuranceType,String classify, String cooperator,
			Date updateTime, Integer pageCurrent){
		PageObject<ProProduct> pageObject=proProductService.findPageObjects(username, insurance, status, insuranceType, classify, cooperator,updateTime,pageCurrent);
		
		return new JsonResult(pageObject);
	}
	
	@RequestMapping("findObjectById")
	@ResponseBody
	public JsonResult findObjectById(String productId){
		ProProduct pageObject=proProductService.findObjectById(productId);
		
		return new JsonResult(pageObject);
	}
	
	@RequestMapping("findUploads")
	@ResponseBody
	public JsonResult findUploads(String insurancePlan){
		List<ProProductUpload> list = proProductService.findUploads(insurancePlan);
		return new JsonResult(list);
	}
	
	@RequestMapping("findOldObjects")
	@ResponseBody
	public JsonResult findOldObjects(String relation, String productId){
		List<ProProduct> list = proProductService.findOldObjects(relation,productId);
		return new JsonResult(list);
	}
	
	
	
	
	//实现文件上传
	@RequestMapping("upload")
	@ResponseBody
	public PicUploadResult fileUpload (MultipartFile[] uploadFile,ProProductUpload entity,HttpSession session) {
			
		String handlers = session.getAttribute("username").toString();
		entity.setCreatedUser(handlers);
			
			
		return proProductService.upload(uploadFile, entity);
	}
	
	//更新文件上传
	@RequestMapping("uploadUpdate")
	@ResponseBody
	public PicUploadResult uploadUpdate (MultipartFile[] uploadFile,ProProductUpload entity,HttpSession session) {
		
		String handlers = session.getAttribute("username").toString();
		entity.setCreatedUser(handlers);
		
		
		return proProductService.uploadUpdate(uploadFile, entity);
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
	
	
		@InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    }

	
	
	
}







