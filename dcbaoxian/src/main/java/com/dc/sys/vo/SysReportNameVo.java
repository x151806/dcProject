package com.dc.sys.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class SysReportNameVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 767593898362455735L;
	private String reportId;
	private Date workTime;
	private String username;
	private String workToday;
	private String workNextDay;
	private String opinion;
	private String comment;
	private Date createdTime;
	private String createdUser;
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWorkToday() {
		return workToday;
	}
	public void setWorkToday(String workToday) {
		this.workToday = workToday;
	}
	public String getWorkNextDay() {
		return workNextDay;
	}
	public void setWorkNextDay(String workNextDay) {
		this.workNextDay = workNextDay;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
		return "SysReportNameVo [reportId=" + reportId + ", workTime=" + workTime + ", username=" + username
				+ ", workToday=" + workToday + ", workNextDay=" + workNextDay + ", opinion=" + opinion + ", comment="
				+ comment + ", createdTime=" + createdTime + ", createdUser=" + createdUser + "]";
	}
	
	

}
