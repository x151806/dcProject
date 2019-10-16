package com.dc.exp.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ExpMission implements Serializable{
	
	
	private static final long serialVersionUID = -43158241871794143L;
	
	
	private String missionId;	//主键id
	private Date createdTime;	//创建时间
	private String meeting;	//会议类型
	private String task;	//任务内容
	private String satrap;	//下达主管
	private String department;	//执部门
	private String executor;	//执行人
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date appointedTime;	//约定时间
	private Integer status=1;	//状态（默认未认领=1）
	private Date claimTime;	//员工认领时间
	
	private String remarkContent;	//主管点评
	private String remarkSatrap;	//点评主管
	private Date fixedTime;	//写入时间
	public Date getFixedTime() {
		return fixedTime;
	}
	public void setFixedTime(Date fixedTime) {
		this.fixedTime = fixedTime;
	}
	public String getMissionId() {
		return missionId;
	}
	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getMeeting() {
		return meeting;
	}
	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getSatrap() {
		return satrap;
	}
	public void setSatrap(String satrap) {
		this.satrap = satrap;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	public Date getAppointedTime() {
		return appointedTime;
	}
	public void setAppointedTime(Date appointedTime) {
		this.appointedTime = appointedTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getClaimTime() {
		return claimTime;
	}
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}
	public String getRemarkContent() {
		return remarkContent;
	}
	public void setRemarkContent(String remarkContent) {
		this.remarkContent = remarkContent;
	}
	public String getRemarkSatrap() {
		return remarkSatrap;
	}
	public void setRemarkSatrap(String remarkSatrap) {
		this.remarkSatrap = remarkSatrap;
	}
	@Override
	public String toString() {
		return "ExpMission [missionId=" + missionId + ", createdTime=" + createdTime + ", meeting=" + meeting
				+ ", task=" + task + ", satrap=" + satrap + ", department=" + department + ", executor=" + executor
				+ ", appointedTime=" + appointedTime + ", status=" + status + ", claimTime=" + claimTime
				+ ", remarkContent=" + remarkContent + ", remarkSatrap=" + remarkSatrap + ", fixedTime=" + fixedTime
				+ "]";
	}
	
	
	

	
}
