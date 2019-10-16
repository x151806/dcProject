package com.dc.pro.entity;

import java.io.Serializable;
import java.util.Date;


public class ProProduct implements Serializable{
	
	private static final long serialVersionUID = 5000968872821564934L;
	
	
	private String productId;	
	private String insuranceType;	
	private String insurance;	
	private String classify;	
	private String status;	
	private String underwrite;	
	private String cooperator;	
	private String agentCharge;	
	private String dCCharge;
	private Date putawayTime;	
	private Date soldOutTime;	
	private Date updateTime;	
	private String updateContent;	
	private String insurancePlan;	
	private String insureDemand;	
	private String amendsDemand;	
	private String remarks;
	
	private Integer valid;
	private Date validTime;
	private String relation;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUnderwrite() {
		return underwrite;
	}
	public void setUnderwrite(String underwrite) {
		this.underwrite = underwrite;
	}
	public String getCooperator() {
		return cooperator;
	}
	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
	}
	public String getAgentCharge() {
		return agentCharge;
	}
	public void setAgentCharge(String agentCharge) {
		this.agentCharge = agentCharge;
	}
	public String getdCCharge() {
		return dCCharge;
	}
	public void setdCCharge(String dCCharge) {
		this.dCCharge = dCCharge;
	}
	public Date getPutawayTime() {
		return putawayTime;
	}
	public void setPutawayTime(Date putawayTime) {
		this.putawayTime = putawayTime;
	}
	public Date getSoldOutTime() {
		return soldOutTime;
	}
	public void setSoldOutTime(Date soldOutTime) {
		this.soldOutTime = soldOutTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateContent() {
		return updateContent;
	}
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}
	public String getInsurancePlan() {
		return insurancePlan;
	}
	public void setInsurancePlan(String insurancePlan) {
		this.insurancePlan = insurancePlan;
	}
	public String getInsureDemand() {
		return insureDemand;
	}
	public void setInsureDemand(String insureDemand) {
		this.insureDemand = insureDemand;
	}
	public String getAmendsDemand() {
		return amendsDemand;
	}
	public void setAmendsDemand(String amendsDemand) {
		this.amendsDemand = amendsDemand;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Date getValidTime() {
		return validTime;
	}
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	
	
	
	
	

	
}
