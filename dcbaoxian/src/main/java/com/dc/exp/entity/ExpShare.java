package com.dc.exp.entity;

import java.io.Serializable;
import java.util.Date;

public class ExpShare implements Serializable{
	
	
	
	
	private static final long serialVersionUID = 63936897316635571L;
	
	private String shareId;	
	private String sort;	
	private String topic;	
	private String author;	
	private Date createdTime;
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
		

	
	
}
