package com.dc.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SysReportModComment implements Serializable{
	
	
	
	private static final long serialVersionUID = 2248968858448866717L;
	private String modCommentId;
	private String reportId;
	private String modifterComment;
	private Date createdTime;
	private String createdUser;
	public String getModCommentId() {
		return modCommentId;
	}
	public void setModCommentId(String modCommentId) {
		this.modCommentId = modCommentId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getModifterComment() {
		return modifterComment;
	}
	public void setModifterComment(String modifterComment) {
		this.modifterComment = modifterComment;
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
		return "SysReportModComment [modCommentId=" + modCommentId + ", reportId=" + reportId + ", modifterComment="
				+ modifterComment + ", createdTime=" + createdTime + ", createdUser=" + createdUser + "]";
	}
	
	
	
	

}
