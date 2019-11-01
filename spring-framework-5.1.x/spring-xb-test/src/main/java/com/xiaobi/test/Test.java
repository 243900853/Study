package com.xiaobi.test;

import com.xiaobi.app.Appconfig;
import com.xiaobi.service.FamilyService;
import com.xiaobi.service.XBService;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		//初始化容器
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext();
		//关闭循环依赖
//		ac.setAllowCircularReferences(false);
		ac.register(Appconfig.class);
		ac.refresh();

		System.out.println(ac.getBean(FamilyService.class));
//		System.out.println(ac.getBean(XBService.class));
//		ac.getBean(FamilyService.class).xx();
//		ac.getBean(FamilyService.class).xx();
//		ac.getBean(FamilyService.class).xx();
//		ClassPathXmlApplicationContext cc
//				= new ClassPathXmlApplicationContext("classpath:spring-config.xml");
//		cc.getBean(FamilyService.class).xx();
		//System.out.println();
	}
}