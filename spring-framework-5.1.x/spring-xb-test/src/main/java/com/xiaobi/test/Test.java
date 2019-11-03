package com.xiaobi.test;

import com.xiaobi.app.Appconfig;
import com.xiaobi.bean.IndexBean;
import com.xiaobi.dao.UserDao;
import com.xiaobi.mybatis.FamilyFactory;
import com.xiaobi.service.FamilyService;
import com.xiaobi.service.XBService;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
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
		ac.refresh();

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
		UserDao userDao = (UserDao) FamilyFactory.getMapper(UserDao.class);
		List<String> list = userDao.queryUser("xiaobi");
	}
}
