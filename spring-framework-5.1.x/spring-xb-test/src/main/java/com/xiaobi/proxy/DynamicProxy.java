package com.xiaobi.proxy;

public class DynamicProxy {
	public static Object getInsance(Object target){
		Class clazz = target.getClass().getInterfaces()[0];
		System.out.println(clazz);
		return null;
	}
}
