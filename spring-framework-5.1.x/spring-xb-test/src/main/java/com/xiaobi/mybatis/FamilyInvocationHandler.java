package com.xiaobi.mybatis;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class FamilyInvocationHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("链接数据库");
		Select annotation = method.getAnnotation(Select.class);
		String sql = annotation.value()[0];
		String userName = args[0].toString();
		sql = sql.replace("?",userName);
		System.out.println(sql);
		Class<?> returnType = method.getReturnType();
		System.out.println(returnType);
		return null;
	}
}
