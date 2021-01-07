package com.importbean;

import com.xiaobi.mybatis.Select;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyFactoryBean implements FactoryBean, InvocationHandler {
	Class clazz;
	public MyFactoryBean(Class clazz){
		this.clazz = clazz;
	}

	@Override
	public Object getObject() throws Exception {
		Class[] classes = new Class[]{clazz};
		Object o = Proxy.newProxyInstance(clazz.getClassLoader(),classes,this);
		return o;
	}

	@Override
	public Class<?> getObjectType() {
		return clazz;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy");
		Method method1 = proxy.getClass().getInterfaces()[0].getMethod(method.getName(),String.class);
		Select select = method1.getDeclaredAnnotation(Select.class);
		System.out.println(select.value()[0].replaceAll("[?]",args[0].toString()));
		return null;
	}
}
