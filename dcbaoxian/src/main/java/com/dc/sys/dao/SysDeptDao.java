package com.dc.sys.dao;
import java.util.List;
import java.util.Map;

import com.dc.common.vo.Node;
import com.dc.sys.entity.SysDept;
public interface SysDeptDao {
	int updateObject(SysDept entity);
	int insertObject(SysDept entity);
	List<Node> findZTreeNodes();
	List<Map<String,Object>> findObjects();
	int getChildCount(Integer id);
	int deleteObject(Integer id);
}
