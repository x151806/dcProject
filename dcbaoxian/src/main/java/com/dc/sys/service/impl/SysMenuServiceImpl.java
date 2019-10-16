package com.dc.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dc.common.exception.ServiceException;
import com.dc.common.vo.Node;
import com.dc.sys.dao.SysMenuDao;
import com.dc.sys.dao.SysRoleMenuDao;
import com.dc.sys.entity.SysMenu;
import com.dc.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired//自动装配
	private SysMenuDao sysMenuDao;
    
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    
    @Override
    public int updateObject(SysMenu entity) {
    	//1.对参数进行校验
    	if(entity==null)
    		throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    		throw new IllegalArgumentException("菜单名不能为空");
    	//.......
    	//2.将数据持久化到数据库
    	int rows=sysMenuDao.updateObject(entity);
    	//3.返回结果
    	return rows;
    }
    @Override
    public int saveObject(SysMenu entity) {
    	//1.对参数进行校验
        if(entity==null)
        throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
        throw new IllegalArgumentException("菜单名不能为空");
        //.......
    	//2.将数据持久化到数据库
        int rows=sysMenuDao.insertObject(entity);
    	//3.返回结果
    	return rows;
    }
    
    @Override
    public List<Node> findZtreeMenuNodes() {
    	List<Node> list=sysMenuDao.findZtreeMenuNodes();
    	if(list==null||list.size()==0)
    	throw new ServiceException("还没有其它菜单");
    	return list;
    }
    
    @Override
    public int deleteObject(Integer id) {
        //1.参数有效性验证
    	if(id==null||id<1)
    	throw new IllegalArgumentException("id值无效");
    	//2.基于id查询统计子菜单并进行校验
    	int childCount=sysMenuDao.getChildCount(id);
    	if(childCount>0)
    	throw new ServiceException("请先删除子菜单");
    	//3.删除菜单自身信息
    	int rowCount=sysMenuDao.deleteObject(id);
    	if(rowCount==0)
    	throw new ServiceException("记录可能已经不存在");
    	//4.删除菜单和角色关系数据
    	sysRoleMenuDao.deleteObjectsByMenuId(id);
    	//5.返回结果结果信息
    	return rowCount;
    }
    
    
	@Override
	public List<Map<String, Object>> findObjects() {
		//查询菜单信息
		List<Map<String,Object>> list=
		sysMenuDao.findObjects();
		//验证查询结果
		if(list==null||list.size()==0)
		throw new ServiceException("没有找到对应记录");
		//返回查询结果
		return list;
	}

}
