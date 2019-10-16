package com.dc.common.utils;

public class CreatIDUtil {
	/**
	 * @Title: getNextId
	 * @Description: 此方法用于在添加记录时提供可用的主键
	 * @return String
	 */
	public String getNextId() {
		String nextId = "";
		UID uID = UID.getInstanse();
		nextId = uID.getUID();
		return nextId;
	}
}
