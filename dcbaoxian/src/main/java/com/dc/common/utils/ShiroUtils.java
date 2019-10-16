package com.dc.common.utils;

import org.apache.shiro.SecurityUtils;

import com.dc.sys.entity.SysUser;

public class ShiroUtils {

	 public static SysUser getUser(){
		 return (SysUser)SecurityUtils
		.getSubject().getPrincipal();
	 }
}
