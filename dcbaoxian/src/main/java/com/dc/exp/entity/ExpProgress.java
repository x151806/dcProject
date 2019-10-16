package com.dc.exp.entity;

import java.io.Serializable;
import java.util.Date;

public class ExpProgress implements Serializable{
	
	private static final long serialVersionUID = 1972177570264759421L;
	
	private String progressId;	
	private String disId;	
	private String progress;	
	private Date createdTime;	
	private String createdUser;
	public String getProgressId() {
		return progressId;
	}
	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}
	public String getDisId() {
		return disId;
	}
	public void setDisId(String disId) {
		this.disId = disId;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
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
	@Override
	public String toString() {
		return "ExpProgress [progressId=" + progressId + ", disId=" + disId + ", progress=" + progress
				+ ", createdTime=" + createdTime + ", createdUser=" + createdUser + "]";
	}
	
}
