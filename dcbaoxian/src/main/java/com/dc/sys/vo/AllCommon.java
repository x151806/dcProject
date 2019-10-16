package com.dc.sys.vo;

import java.io.Serializable;

public class AllCommon implements Serializable{
	
	private static final long serialVersionUID = 4446000272593158298L;
	
	private String id;
	private String details;
	private Integer type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
