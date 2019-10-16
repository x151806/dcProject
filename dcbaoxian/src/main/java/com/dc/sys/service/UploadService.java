package com.dc.sys.service;

import org.springframework.web.multipart.MultipartFile;

import com.dc.common.vo.PicUploadResult;

public interface UploadService {
	
	
	PicUploadResult upload(MultipartFile[] uploadFile,String reportId);

}
