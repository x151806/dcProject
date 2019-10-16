package com.dc.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysReportModWork implements Serializable{
	
	
	
	private static final long serialVersionUID = 2248968858448866717L;
	private String modifierId;
	private String reportId;
	private String modifterWork;
	private Date createdTime;
	private String createdUser;
	
	
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getModifterWork() {
		return modifterWork;
	}
	public void setModifterWork(String modifterWork) {
		this.modifterWork = modifterWork;
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
		return "SysReportModWork [modifierId=" + modifierId + ", reportId=" + reportId + ", modifterWork="
				+ modifterWork + ", createdTime=" + createdTime + ", createdUser=" + createdUser + "]";
	}
	
	
	
	

}
