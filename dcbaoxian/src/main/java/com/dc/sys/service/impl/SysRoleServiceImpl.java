package com.dc.sys.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dc.common.exception.ServiceException;
import com.dc.common.utils.PageUtil;
import com.dc.common.vo.CheckBox;
import com.dc.common.vo.PageObject;
import com.dc.sys.dao.SysRoleDao;
import com.dc.sys.dao.SysRoleMenuDao;
import com.dc.sys.entity.SysRole;
import com.dc.sys.service.SysRoleService;
import com.dc.sys.vo.SysRoleMenuVo;
@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Override
	public List<CheckBox> findObjects() {
		List<CheckBox> roles=
				sysRoleDao.findObjects();
		if(roles==null||roles.isEmpty())
		throw new ServiceException("没有角色信息");
		return roles;
	}
	
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		//1.验证参数合法性
		if(id==null||id<1)
		throw new IllegalArgumentException("id值无效");
		//2.基于id执行查询
		SysRoleMenuVo vo=sysRoleDao.findObjectById(id);
		//3.判定查询结果
	    if(vo==null)
	    throw new ServiceException("记录可能已经不存在");
		//4.返回结果
		return vo;
	}
	@Override
	public int updateObject(SysRole entity,
			Integer[] menuIds) {
		//1.验证参数有效性
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
			throw new ServiceException("必须为角色分配权限");
		//......
		//2.保存角色自身信息
		int rows=sysRoleDao.updateObject(entity);
		//3.保存角色菜单关系数据
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		sysRoleMenuDao.insertObjects(
				entity.getId(),//此id值来自33行的写入
				menuIds);
		//4.返回结果
		return rows;
	}
	@Override
	public int saveObject(SysRole entity,
			Integer[] menuIds) {
		//1.验证参数有效性
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new ServiceException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
		throw new ServiceException("必须为角色分配权限");
		//......
		//2.保存角色自身信息
		int rows=sysRoleDao.insertObject(entity);
		//3.保存角色菜单关系数据
		sysRoleMenuDao.insertObjects(
				entity.getId(),//此id值来自33行的写入
				menuIds);
		//4.返回结果
		return rows;
	}
	
	
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		//1.参数有效性验证
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("页码值不正确");
		//2.查询总记录数并进行验证
		int rowCount=sysRoleDao.getRowCount(name);
		if(rowCount==0)
		throw new ServiceException("没有对应记录");
		//3.查询当前页要呈现的数据
		int pageSize=3;//页面大小
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> records=
		sysRoleDao.findPageObjects(name,
				startIndex, pageSize);
		//4.对查询结果进行封装,并返回
		return PageUtil.newPageObject(rowCount,
			   records, pageCurrent,pageSize);
	}//减法设计

}
