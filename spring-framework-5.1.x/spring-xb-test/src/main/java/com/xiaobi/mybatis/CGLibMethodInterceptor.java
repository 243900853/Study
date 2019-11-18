package com.xiaobi.mybatis;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibMethodInterceptor implements MethodInterceptor {
	@Override
	//Object o 父类
	//Method method 调用的父类方法
	//Object[] objects	调用父类方法，传进来的值
	//MethodProxy methodProxy	代理方法
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("cglib代理对象，执行自己的逻辑");
		System.out.println(method.toGenericString());
		//执行父类方法   父类没有参数
		Object targetObject = methodProxy.invokeSuper(o,objects);
		return targetObject;
	}
}
