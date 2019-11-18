package com.xiaobi.test;

import com.xiaobi.CGLib.A;
import com.xiaobi.CGLib.CGLibAppconfig;
import com.xiaobi.mybatis.CGLibMethodInterceptor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CGLibTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CGLibAppconfig.class);
		System.out.println(ac.getBean(CGLibAppconfig.class));
		System.out.println(ac.getBeanDefinition("a").getBeanClassName());

		Enhancer enhancer = new Enhancer();
		//设置原对象
		enhancer.setSuperclass(A.class);
		enhancer.setCallback(new CGLibMethodInterceptor());
		A a = (A)enhancer.create();
		String name = a.cglibMethod("你好");
		System.out.println(name);
	}
}
