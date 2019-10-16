package com.dc.exp.entity;

import java.io.Serializable;
import java.util.Date;

public class ExpStatistics implements Serializable{
	
	
	private static final long serialVersionUID = -43158241871794143L;
	
	
	private String stageId;	//主键id
	private Date createdTime;	//创建时间
	private Integer stageTwo;	
	private Integer stageThree;	
	private Integer stageFour;	
	private Integer stageFive;	
	private Integer stageSix;	
	private Integer stageSeven;	
	private String stageSatrap;	//统计人主管
	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getStageTwo() {
		return stageTwo;
	}
	public void setStageTwo(Integer stageTwo) {
		this.stageTwo = stageTwo;
	}
	public Integer getStageThree() {
		return stageThree;
	}
	public void setStageThree(Integer stageThree) {
		this.stageThree = stageThree;
	}
	public Integer getStageFour() {
		return stageFour;
	}
	public void setStageFour(Integer stageFour) {
		this.stageFour = stageFour;
	}
	public Integer getStageFive() {
		return stageFive;
	}
	public void setStageFive(Integer stageFive) {
		this.stageFive = stageFive;
	}
	public Integer getStageSix() {
		return stageSix;
	}
	public void setStageSix(Integer stageSix) {
		this.stageSix = stageSix;
	}
	public Integer getStageSeven() {
		return stageSeven;
	}
	public void setStageSeven(Integer stageSeven) {
		this.stageSeven = stageSeven;
	}
	public String getStageSatrap() {
		return stageSatrap;
	}
	public void setStageSatrap(String stageSatrap) {
		this.stageSatrap = stageSatrap;
	}
	@Override
	public String toString() {
		return "ExpStatistics [stageId=" + stageId + ", createdTime=" + createdTime + ", stageTwo=" + stageTwo
				+ ", stageThree=" + stageThree + ", stageFour=" + stageFour + ", stageFive=" + stageFive + ", stageSix="
				+ stageSix + ", stageSeven=" + stageSeven + ", stageSatrap=" + stageSatrap + "]";
	}

	
}
