package com.xiaobi.test;

import com.xiaobi.CGLib.A;
import com.xiaobi.CGLib.B;
import com.xiaobi.service.OrderService;
import com.xiaobi.service.UserService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

public class ConstructorsTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.scan("com.xiaobi.service");
		ac.scan("com.xiaobi.mapper");

		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(OrderService.class);
		//为构造方法传入参数赋值
		genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.xiaobi.service.UserService");
		ac.registerBeanDefinition("orderService",genericBeanDefinition);
		//为属性赋值
		genericBeanDefinition.getPropertyValues().add("age","18岁");

		//合并bd--父bd
		RootBeanDefinition root = new RootBeanDefinition();
		root.setBeanClass(A.class);
		root.getPropertyValues().add("type","类型");
		root.getPropertyValues().add("name","名字");
		ac.registerBeanDefinition("root",root);
		//合并bd--子bd
		GenericBeanDefinition child = new GenericBeanDefinition();
		child.setBeanClass(B.class);
		child.getPropertyValues().add("name","新名字");
		child.setParentName("root");
		ac.registerBeanDefinition("child",child);

		ac.refresh();

		System.out.println(ac.getBean(OrderService.class));
		System.out.println(ac.getBean(B.class));
		System.out.println(ac.getBean(OrderService.class).getAge());

	}
}
