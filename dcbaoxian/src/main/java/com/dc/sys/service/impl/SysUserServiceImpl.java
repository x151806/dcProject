package com.dc.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dc.common.annotation.RequiredLog;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.PageUtil;
import com.dc.common.vo.PageObject;
import com.dc.sys.dao.SysUserDao;
import com.dc.sys.dao.SysUserRoleDao;
import com.dc.sys.entity.SysUser;
import com.dc.sys.service.SysUserService;
import com.dc.sys.vo.SysUserDeptVo;
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public Map<String, Object> findObjectById(Integer id) {
		//1.参数有效性验证
		if(id==null||id<1)
			throw new IllegalArgumentException("id值不合法");
		//2.基于用户id查询用户以及对应的部门、上级信息
		SysUserDeptVo userDeptVo=
				sysUserDao.findObjectById(id);
		//3.基于用户id查询角色信息
		List<Integer> roleIds=
				sysUserRoleDao.findRoleIdsByUserId(id);
		//4.对查询结果进行封装
		Map<String,Object> map=new HashMap<>();
		map.put("user", userDeptVo);
		map.put("roleIds", roleIds);
		return map;
	}

	@Override
	public int updateObject(SysUser entity,Integer[] roleIds) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(roleIds==null||roleIds.length==0)
			throw new IllegalArgumentException("必须为其指定角色");
		if(StringUtils.isEmpty(entity.getParentId()))
			throw new IllegalArgumentException("必须为其指定上级");
		//其它验证自己实现，例如用户名已经存在，密码长度，...
		//2.更新用户自身信息
		int rows=sysUserDao.updateObject(entity);
		//3.保存用户与角色关系数据
		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		sysUserRoleDao.insertObjects(entity.getId(),
				roleIds);
		//4.返回结果
		return rows;
	}
	@Override
	public int saveObject(SysUser entity,Integer[] roleIds) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		if(StringUtils.isEmpty(entity.getParentId()))
			throw new IllegalArgumentException("必须为其指定部门");
		if(StringUtils.isEmpty(entity.getDeptId()))
			throw new IllegalArgumentException("必须为其指定上级");
		if(roleIds==null||roleIds.length==0)
			throw new IllegalArgumentException("必须为其指定角色");
		//其它验证自己实现，例如用户名已经存在，密码长度，...
		//2.保存用户自身信息
		//2.1生成一个salt值
		String salt=UUID.randomUUID().toString();
		//2.2对密码进行验证加密
		SimpleHash sh=new SimpleHash(//此api需要添加shiro依赖
				"MD5",//algorithmName (相同内容加密结果一样)
				entity.getPassword(),//source
				salt,1);
		entity.setSalt(salt);
		entity.setPassword(sh.toHex());
		int rows=sysUserDao.insertObject(entity);
		//3.保存用户与角色关系数据
		sysUserRoleDao.insertObjects(entity.getId(),
				roleIds);
		//4.返回结果
		return rows;
	}
	@RequiresPermissions("sys:log:delete")
	@Override
	public int validById(Integer id, 
			Integer valid, String modifiedUser) {
		//1.验证参数合法性
		if(id==null||id<1)
			throw new IllegalArgumentException("参数不正确,id="+id);
		if(valid!=0&&valid!=1)
			throw new IllegalArgumentException("状态值不正确,valid="+valid);
		if(StringUtils.isEmpty(modifiedUser))
			throw new ServiceException("请先登录,修改用户不能为空");
		//2.执行禁用或启用操作

		int rows=sysUserDao.validById(id, valid, modifiedUser);
		if(rows==0)
			throw new ServiceException("记录可能已经存在");
		//3.对结果进行验证并返回
		return rows;
	}
	@RequiredLog("分页查询")
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		//1.参数有效性验证
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("参数无效");
		//2.查询总记录数并进行验证
		int rowCount=sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("没有找到对应记录");
		//3.查询当前页数据
		Integer pageSize=5;
		Integer startIndex=(pageCurrent-1)*pageSize;
		List<SysUserDeptVo> records=
				sysUserDao.findPageObjects(username,
						startIndex, pageSize);
		//4.封装查询结果并返回
		return PageUtil.newPageObject(rowCount,
				records, pageCurrent, pageSize);
	}



	@Override
	public SysUser findObjectByName(String userName) {
		SysUser user=
				sysUserDao.findUserByUserName(userName);
		return user;
	}



	@Override
	public List<SysUser> findLikeNameObject() {
		List<SysUser> likeName= sysUserDao.findLikeName();
		return likeName;
	}

	@Override
	public int updatePassword(SysUser entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		
		String salt=UUID.randomUUID().toString();
		//2.2对密码进行验证加密
		SimpleHash sh=new SimpleHash(//此api需要添加shiro依赖
				"MD5",//algorithmName (相同内容加密结果一样)
				entity.getPassword(),//source
				salt,1);
		entity.setSalt(salt);
		entity.setPassword(sh.toHex());
		
		
		int rows=sysUserDao.updatePassword(entity);
		
		return rows;
	}

















}




