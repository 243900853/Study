package com.xiaobi.mybatis;

import com.xiaobi.proxy.DynamicProxy;
import com.xiaobi.proxy.IndexService;
import com.xiaobi.proxy.Service;

import java.lang.reflect.Proxy;

public class FamilyFactory {
	/*
	实现动态代理
	  ClassLoader loader,将java文件编译成class文件
	  Class<?>[] interfaces,接口对象，可以理解为要实现这个接口的类
	  InvocationHandler h,重写接口方法，执行逻辑
	*/
	public static Object getMapper(Class clazz){
		Class[] classes = new Class[]{clazz};
		Object o = Proxy.newProxyInstance(FamilyFactory.class.getClassLoader(),classes,new FamilyInvocationHandler());

		return o;
	}

	public static Object getMapper(Object object){
		Object o = DynamicProxy.getInsance(object,new FamilyInvocationHandler());
		return o;
	}
}
