package com.dc.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 * 1.JDK1.5 推出的一种元数据(描述数据的数据)
 * 2.本质上是一个特殊的Class
 * 3.主要用于描述类,方法，属性，参数等对象
 * 
 * 注解的应用
 * 1)编译时,例如@Override，仅在编译时有效
 * 2)运行时,例如@Controller,....,这些注解会结合反射一起应用。
 * 
 * 自定义注解？
 * 1)框架中定义?(重应用)
 * 2)自己定义?
 * a)借助@interface关键字进行定义
 * b)使用@Target描述注解可以修饰哪些对象
 * c)使用@Retention描述注解何时有效。
 * 
 * 思考：spring中描述bean对象方式
 * 1)xml <bean id="" class="">
 * 2)注解 @Service,@Controller,....
 */
@Retention(RetentionPolicy.RUNTIME)//运行时有效
@Target(ElementType.METHOD)//只能修饰方法
public @interface RequiredLog {
	String value() default "";
}















