package com.dc.common.vo;

import java.io.Serializable;

/**
 * 值对象：借助此对象封装控制层方法
 * 要向客户端输出的数据
 * @author ta
 */
public class JsonResult implements Serializable{
	private static final long serialVersionUID = 5427856250973673757L;
	/** 响应状态 1 ok,0 error*/
	private int state=1;
	/** 响应信息*/
	private String message="ok";
	/** 响应对象数据*/
	private Object data;
	
	public JsonResult() {
	}
	public JsonResult(String message){
		this.message=message;
	}
	public JsonResult(Object data){
		this.data=data;
	}
	/**出现异常时调用*/
	public JsonResult(Throwable e){
		this.state=0;
		this.message=e.getMessage();
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	} 
}
