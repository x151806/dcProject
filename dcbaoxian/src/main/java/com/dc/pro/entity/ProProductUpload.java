package com.dc.pro.entity;

import java.io.Serializable;
import java.util.Date;

public class ProProductUpload implements Serializable{

	private static final long serialVersionUID = 8025103050039491226L;
	
	
	
	private String uploadId;	//主键id
	private String disId;	//外键id
	private String site;
	private String fileName;
	private Date createdTime;
	private String createdUser;
	
	
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	
	public String getDisId() {
		return disId;
	}
	public void setDisId(String disId) {
		this.disId = disId;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	
	
}
