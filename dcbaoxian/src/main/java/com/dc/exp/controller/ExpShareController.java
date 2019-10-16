package com.dc.exp.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.common.vo.JsonResult;
import com.dc.common.vo.PageObject;
import com.dc.exp.entity.ExpShare;
import com.dc.exp.entity.ExpSystem;
import com.dc.exp.service.ExpShareService;



@Controller
@RequestMapping("/share/")
public class ExpShareController implements HttpSessionListener{

	@Autowired
	private ExpShareService expShareService;




	@RequestMapping("doShareUI")
	public String dobugSubmitUI(){
		return "exp/share";
	}
	
	@RequestMapping("doShareDetailsUI")
	public String doShareDetailsUI(){
		return "exp/share_details";
	}
	
	
	@RequestMapping("doShareEditUI")
	public String doShareEditUI(){
		return "exp/share_edit";
	}
	
	
	@RequestMapping("doShareUpdate")
	public String doShareUpdate(){
		return "exp/share_update";
	}
	
	
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ExpShare entity,HttpSession session){
		String name = session.getAttribute("username").toString();
		entity.setAuthor(name);
		expShareService.saveShare(entity);
		return new JsonResult("添加成功！");
	}
	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(String shareId){
		int rows=expShareService.deleteShare(shareId);
		return new JsonResult("删除成功！");
	}
	@RequestMapping("updateShare")
	@ResponseBody
	public JsonResult updateShare(ExpShare entity,HttpSession session){
		String name = session.getAttribute("username").toString();
		entity.setAuthor(name);
		int rows=expShareService.updateShare(entity);
		return new JsonResult("修改成功！");
	}
	
	@RequestMapping("findObjects")
	@ResponseBody
	public JsonResult findObjects(String sort,String topic,String author,Integer pageCurrent){
		PageObject<ExpShare> pageObject=expShareService.findShare(sort,topic,author,pageCurrent);
		
		return new JsonResult(pageObject);
	}
	
	
	@RequestMapping("findShareById")
	@ResponseBody
	public JsonResult findShareById(String shareId){
		ExpShare pageObject=expShareService.findShareById(shareId);
		
		return new JsonResult(pageObject);
	}
	
	@RequestMapping("findAuthor")
	@ResponseBody
	public JsonResult findAuthor(){
		List<ExpShare> list=expShareService.findAuthor();
				
		return new JsonResult(list);
	}
	
	
	
	
	
	

	


//	//实现文件上传
//	@RequestMapping("upload")
//	@ResponseBody
//	public PicUploadResult fileUpload (MultipartFile[] uploadFile,ExpSystemUpload entity,HttpSession session,String disId) {
//		
//		String handlers = session.getAttribute("username").toString();
//		entity.setCreatedUser(handlers);
//		entity.setDisId(disId);
//		
//		
//		return expSystemService.upload(uploadFile, entity);
//	}
//
//	
//	//实现文件下载
//	@RequestMapping("download")
//	@ResponseBody
//    public void download(String filename,String site,
//            HttpServletResponse response) throws IOException {
//		
//		filename=URLDecoder.decode(filename,"utf-8");
//		site=URLDecoder.decode(site,"utf-8");
//
//        //模拟文件，myfile.txt为需要下载的文件  
//		String path = site;
//		 //得到要下载的文件
//        File file = new File(path);
//        if (!file.exists()) {
//        	response.setContentType("text/html; charset=UTF-8");//注意text/html，和application/html
//        	response.getWriter().print("<html><body><script type='text/javascript'>alert('您要下载的资源已被删除！');</script></body></html>");
//        	response.getWriter().close(); 
//            System.out.println("您要下载的资源已被删除！！");  
//		}
//        //转码，免得文件名中文乱码  
//        filename = URLEncoder.encode(filename,"UTF-8");  
//        //设置文件下载头  
//        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
//        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
//        response.setContentType("multipart/form-data"); 
//        // 读取要下载的文件，保存到文件输入流
//        FileInputStream in = new FileInputStream(path);
//        // 创建输出流
//        OutputStream out = response.getOutputStream();
//        // 创建缓冲区
//        byte buffer[] = new byte[1024]; // 缓冲区的大小设置是个迷  我也没搞明白
//        int len = 0;
//        //循环将输入流中的内容读取到缓冲区当中
//        while((len = in.read(buffer)) > 0){
//        	out.write(buffer, 0, len);
//        }
//        //关闭文件输入流
//        in.close();
//        // 关闭输出流
//        out.close();
//        
// 
//    }






}







