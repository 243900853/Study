package com.xiaobi.test;

import com.xiaobi.service.OrderService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

public class ConstructorsTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.scan("com.xiaobi.service");
		ac.scan("com.xiaobi.mapper");

		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(OrderService.class);
		genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.xiaobi.service.UserService");
		ac.registerBeanDefinition("orderService",genericBeanDefinition);

		ac.refresh();

		System.out.println(ac.getBean(OrderService.class));

	}
}
