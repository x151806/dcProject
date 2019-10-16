package com.dc.common.aspect;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.common.annotation.RequiredLog;
import com.dc.common.utils.IPUtils;
import com.dc.common.utils.ShiroUtils;
import com.dc.sys.dao.SysLogDao;
import com.dc.sys.entity.SysLog;
import com.dc.sys.entity.SysUser;
/**
 * 日志切面类型(基于此类的对象进行用户日志行为记录)
 * @Aspect 用于描述类，定义此类为切面类型
 */
@Aspect
@Service
public class SysLogAspect {	
	 @Autowired
	 private SysLogDao sysLogDao;
	 /**
	  * 借助@Pointcut定义切入点表达式,切入点
	  * 表示用于告诉底层系统，哪些方法执行时
	  * 要切入扩展功能(例如@Around通知)
	  */
	  //bean()表示为一种粗粒度的切入点表达式定义
	  //@Pointcut("bean(sysUserServiceImpl)")
	  //@Pointcut("bean(*ServiceImpl)")
	
	 //@annotation为注解方式的切入点表达式，此表达可以进行细粒度访问定义
	 @Pointcut("@annotation(com.dc.common.annotation.RequiredLog)")
	 public void doLogPoint(){}
	 /**
	  * 使用@Around修饰的方法一般为一个环绕通知方法：
	  * 当方法参数为ProceedingJoinPoint类型时，
	  * 并且希望在目标方法之前，之后都有功能
	  * 功能扩展，一般可以借助@Around注解进行
	  * 修饰。
	  * FAQ?此方法何时执行呢？
	  * 由@Around注解内容使用的表达时决定。
	  * 其中bean()为一个切入点表达式
	  * 例如：bean(sysUserServiceImpl)表示
	  * 当sysUserServiceImpl类中的所有方法执行
	  * 时自动执行如下around方法。
	  * @param jp 连接点
	  * @return 为目标方法的执行结果
	  * @throws Throwable 执行业务时出现的异常。
	  */
	 @Around("doLogPoint()")
	 public Object around(ProceedingJoinPoint jp)  
	 throws Throwable{
		 long t1=System.currentTimeMillis();
		 Object result=jp.proceed();//执行目标方法
		 long t2=System.currentTimeMillis();
		 System.out.println("对用户行为进行日志记录");
		 //将日志信息(用户行为日志)写入到数据库
		 saveLog(jp,(t2-t1));
	
		 return result;
	 }
	 private void saveLog(ProceedingJoinPoint jp,long time)
	 throws Exception{
		 //1.获取日志信息
		 //1.1获取方法签名信息(记录了目标方法信息)
		 MethodSignature s=(MethodSignature)jp.getSignature();
		 //1.2获取目标方法参数类型
		 Class<?>[] parameterTypes=s.getParameterTypes();
		 //1.3获取目标方法名
		 String methodName=s.getName();
		 //1.4获取目标方法对象
		 Class<?> targetClass=jp.getTarget().getClass();
		 Method targetMethod=
				 targetClass.getDeclaredMethod(methodName, parameterTypes);
		 //1.5获取类方法信息(类名+"."+方法名)
		 String method=targetClass.getName()+"."+methodName;
         //1.6获取方法对象上注解RequiredLog
		 RequiredLog requiredLog=
		 targetMethod.getDeclaredAnnotation(RequiredLog.class);
		 //1.7获取注解上指定的操作名
		 String operation="";
		 if(requiredLog!=null){
			 operation=requiredLog.value();
		 }
		 //1.8获取登录用户名
		 SysUser user=ShiroUtils.getUser();
		 String username=user.getUsername();
		 //1.9获取ip地址
		 String ip=IPUtils.getIpAddr();//工具类暂且不需要理解
		 //2.封装日志信息
		 SysLog log=new SysLog();
		 log.setUsername(username);
		 log.setIp(ip);
		 log.setOperation(operation);
		 log.setMethod(method);
		 log.setParams(Arrays.toString(jp.getArgs()));
		 log.setTime(time);
		 log.setCreatedTime(new Date());
		 //3.持久化日志信息
		 sysLogDao.insertObject(log);
	 }
}







