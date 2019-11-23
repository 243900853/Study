package com.xiaobi.test;

import com.xiaobi.app.Appconfig;
import com.xiaobi.bean.IndexBean;
import com.xiaobi.dao.UserDao;
import com.xiaobi.mybatis.FamilyFactory;
import com.xiaobi.proxy.LogService;
import com.xiaobi.proxy.PowerService;
import com.xiaobi.service.FamilyService;
import com.xiaobi.service.XBService;
import com.xiaobi.service.XBService1;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		//初始化容器
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext();
		//关闭循环依赖
//		ac.setAllowCircularReferences(false);
		ac.register(Appconfig.class);
//		ac.getBeanFactory().registerSingleton("indexBean", IndexBean.class);
		//扫描包
		ac.scan("com.xiaobi");
		

		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setAutowireMode(2);
		genericBeanDefinition.setBeanClass(XBService1.class);
		genericBeanDefinition.setScope("singleton");
		ac.registerBeanDefinition("xb1",genericBeanDefinition);

		ac.refresh();

		System.out.println(ac.getBean("xb1"));

		System.out.println(ac.getBean(FamilyService.class));
		System.out.println(ac.getBean("indexBean"));
		System.out.println(ac.getBean("&indexBean"));
//		System.out.println(ac.getBean(XBService.class));
		System.out.println("========================单例实现原型功能 Start========================");
		ac.getBean(FamilyService.class).xx();
		ac.getBean(FamilyService.class).xx();
		ac.getBean(FamilyService.class).xx();
		System.out.println("========================单例实现原型功能 End========================");
//		ClassPathXmlApplicationContext cc
//				= new ClassPathXmlApplicationContext("classpath:spring-config.xml");
//		cc.getBean(FamilyService.class).xx();




	}
}