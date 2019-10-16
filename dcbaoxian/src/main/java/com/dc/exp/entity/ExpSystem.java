package com.dc.exp.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ExpSystem implements Serializable{
	
	
	private static final long serialVersionUID = 6899735945744160688L;
	
	private String problemId;	//主键id
	private Date createdTime;
	private String createdUser;
	private String system;
	private String type;
	private String port;
	private String sequence;
	
	private String systemRole;
	private String menu;
	private String problem;
	private String executor;
	private Integer status;
	private Date fixedTime;
	private String remarkContent;
	private String remarkSatrap;
	private Date finishTime;
	
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getProblemId() {
		return problemId;
	}
	public void setProblemId(String problemId) {
		this.problemId = problemId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getSystemRole() {
		return systemRole;
	}
	public void setSystemRole(String systemRole) {
		this.systemRole = systemRole;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getFixedTime() {
		return fixedTime;
	}
	public void setFixedTime(Date fixedTime) {
		this.fixedTime = fixedTime;
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
	
	
}
