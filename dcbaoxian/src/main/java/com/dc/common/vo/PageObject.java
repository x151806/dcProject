package com.dc.common.vo;

import java.io.Serializable;
import java.util.List;
/**
 * VO:Value Object (值对象):用于封装值
 * 1)封装当前页的记录信息
 * 2)封装当前页的分页信息
 * @param <T> 参数化类型(泛型)
 * 当将<T>应用到类或接口时表示此类为一个泛型类
 * 而类上定义的泛型用于约束
 * 1)类中属性类型，
 * 2)方法参数类型，
 * 3)方法的返回值类型
 */
public class PageObject<T> implements Serializable{
	private static final long serialVersionUID = -7368493786259905794L;
	/**总记录数*/
	private Integer rowCount=0;//null
	/**页面大小*/
	private Integer pageSize=3;
	/**当前页码值*/
	private Integer pageCurrent=1;
	/**总页数*/
	private Integer pageCount=0;
	/**当前页记录*/
	private List<T> records;
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
}
