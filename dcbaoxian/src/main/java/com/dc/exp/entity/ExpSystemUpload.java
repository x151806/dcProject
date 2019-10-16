package com.dc.exp.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ExpSystemUpload implements Serializable{
	
	
	
	private static final long serialVersionUID = 93951768993731835L;
	
	
	private String uploadId;	//主键id
	private String disId;
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
