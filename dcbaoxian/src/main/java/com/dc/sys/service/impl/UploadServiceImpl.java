package com.dc.sys.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dc.common.vo.PicUploadResult;
import com.dc.sys.dao.SysReportDao;
import com.dc.sys.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService{
	@Autowired
	private SysReportDao sysReportDao;
	
	//该数据从spring容器中动态获取数据
		@Value("${image.localpath}")
		private String localPath;  //"E:/jt-upload/";
	
	/**
	 * 文件上传实现思路
	 * 1.校验文件类型 jpg|png|gif....
	 * 2.校验是否为恶意程序
	 * 3.为了防止图片检索速度慢,采用分文件存储  yyyy/MM/dd/
	 * 4.防止文件重名  UUID + 随机数(3)
	 * 5.实现文件上传 	  
	 */
		@Override
		public PicUploadResult upload(MultipartFile[] uploadFile,String reportId) {
			PicUploadResult result = new PicUploadResult();
			StringBuffer sb = new StringBuffer();
				
				try {
					for(int i=0;i<uploadFile.length;i++) {
						String localPathReal=upMethod(uploadFile[i]);
						
						//实现文件上传
						uploadFile[i].transferTo(new File(localPathReal));
						String fileName = uploadFile[i].getOriginalFilename();
						sb.append(fileName).append(",");
						
						sysReportDao.updateAvatar(reportId,localPathReal);
						
						
						//定义url
//						String url = "https://img14.360buyimg.com/n0/jfs/t1/7301/36/10557/363153/5c231de0E0a5565dd/2e8054392374dc29.jpg";
					}
					String url = sb.deleteCharAt(sb.length() - 1).toString();
					result.setUrl(url);
				} catch (Exception e) {
					e.printStackTrace();
					result.setError(1);	//文件上传失败
					return result;
				}
			
			
			
			//将字符全部小写
		
//		  fileName = fileName.toLowerCase();
//		  if(!fileName.matches("^.+\\.(jpg|png|gif)$")) { result.setError(1); //不是图片
//		  return result; }
		 
			
			
			return result;
		}
		
		
		
		
		
		
		public void updateAvatar(@Param("reportId") String reportId, 
			    @Param("avatar") String avatar) {
			sysReportDao.updateAvatar(reportId, avatar);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public String upMethod(MultipartFile uploadFile) {
			//1.获取文件名称  
			String fileName = uploadFile.getOriginalFilename();
			String nameOne = fileName;
			
			//3.实现分文件存储
			String dateDir = 
					new SimpleDateFormat("yyyy/MM/dd")
					.format(new Date());
			//  E:/jt-upload/2019/01/29
			String localPathDir = localPath + dateDir;
			//判断文件夹是否存在
			File fileDir = new File(localPathDir);
			if(!fileDir.exists()) {
				fileDir.mkdirs();	//创建文件夹
			}
			
			//4.定义文件名称
			long milis = System.currentTimeMillis();
			String a  =String.valueOf(milis); 
			String num = a.substring(a.length()-8,a.length());
			
			String fileType = 
					fileName.substring
					(fileName.lastIndexOf("."));
			//形成文件名称
			String realName = num + "-" +nameOne;
			
			//E:/jt-upload/2019/01/29/abc.jpg
			String localPathReal = localPathDir + "/" + realName;
			return localPathReal;
		}

}
