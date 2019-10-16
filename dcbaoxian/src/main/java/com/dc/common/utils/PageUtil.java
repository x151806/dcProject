package com.dc.common.utils;

import java.util.List;

import com.dc.common.vo.PageObject;

public class PageUtil {
	
	    public static <T>PageObject<T> newPageObject(
	    		      int rowCount,
	    		      List<T> records,
	    		      int pageCurrent,
	    		      int pageSize){
	    	PageObject<T> po=new PageObject<>();
	    	po.setRowCount(rowCount);
	    	po.setRecords(records);
	    	po.setPageSize(pageSize);
	    	po.setPageCurrent(pageCurrent);
	    	po.setPageCount((rowCount-1)/pageSize+1);
	    	return po;
	    }
}
