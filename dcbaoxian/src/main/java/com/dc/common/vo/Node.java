package com.dc.common.vo;

import java.io.Serializable;

/**
 * 借助此对象封装树节点信息(节点id，节点名称，父节点id)
 * 例如zTree中要呈现的节点数据(eclipse项目视图中的
 * 工程文件的呈现本身就是一种树结构)
 * @author ta
 */
public class Node implements Serializable{
	private static final long serialVersionUID = -4794314574141335353L;
	private Integer id;
	private String name;
	private Integer parentId;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	
}
