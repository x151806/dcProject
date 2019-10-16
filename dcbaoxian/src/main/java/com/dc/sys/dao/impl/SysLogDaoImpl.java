package com.dc.sys.dao.impl;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dc.sys.dao.SysLogDao;
import com.dc.sys.entity.SysLog;

/**
 * @Repository 一般用于修饰数据层对象，
 * 并且告诉spring此对象交给spring管理，
 * 类似@Controller,@Service等注解
 * 
 * 思考：
 * 1)spring将SysLogDaoImpl存储到map容器时，可以是谁？
 * key为类名，然后首字母小写，例如"sysLogDaoImpl"
 */
@Repository 
public class SysLogDaoImpl implements SysLogDao{
	@Override
	public int insertObject(SysLog entity) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteObjects(Integer... ids) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<SysLog> findPageObjects(String username, Integer startIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getRowCount(String username) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @Autowired 表示自动装配，Spring会依据这个注解
	 * 从它的容器中按照@Autowired此注解修饰的元素类型
	 * 查找相匹配的对象，最后将这个值基于DI机制注入给我
	 * 们的变量。
	 * 
	 * SqlSessionFactory对象是如何创建的呢？
	 * Spring底层调用SqlSessionFactoryBean的
	 * getObject方法创建的，此方法内部是如何创建的呢？
	 * 借助SqlSessionFactoryBuilder的build方法创建的
	 */
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Override
	public List<SysLog> findObjects() {
		//？如何借助mybatis的api执行日志查询
		//1.获取SqlSession对象
		SqlSession session=sqlSessionFactory.openSession();
		//2.基于SqlSession对象执行查询操作
		//String statement="com.dc.sys.dao.SysLogDao.findObjects";
		//List<SysLog> list=session.selectList(statement);
		SysLogDao dao=session.getMapper(SysLogDao.class);
		System.out.println(dao.getClass().getName());
		List<SysLog> list=dao.findObjects();
		//3.释放SqlSession对象
		session.close();
		//4.返回查询结果
		return list;
	}
}





