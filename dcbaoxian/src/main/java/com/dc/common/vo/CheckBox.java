package com.dc.common.vo;
import java.io.Serializable;
/**
 * 一般借助此对象封装页面checkbox对象需要的数据
 * @author ta
 */
public class CheckBox implements Serializable{
	private static final long serialVersionUID = 5127226184862470973L;
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
