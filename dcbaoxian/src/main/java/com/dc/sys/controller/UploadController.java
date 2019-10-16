package com.dc.sys.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dc.common.exception.ServiceException;
import com.dc.common.vo.PicUploadResult;
import com.dc.sys.service.SysReportService;
import com.dc.sys.service.UploadService;

@Controller
@RequestMapping("/load/")
public class UploadController {
	@Autowired
	private UploadService uploadService;
	/**
     * 单个文件上传
     * @param request
     * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
     */
	
	
	
	
	//实现文件上传
		@RequestMapping("upload")
		@ResponseBody
		public PicUploadResult fileUpload
		(MultipartFile[] uploadFile,String reportId) {
			reportId="63";
			return uploadService.upload(uploadFile, reportId);
		}
}
	
	
	
	
	
	
	
	
	
	
	





