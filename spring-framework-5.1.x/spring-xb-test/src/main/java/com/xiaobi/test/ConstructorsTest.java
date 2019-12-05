package com.xiaobi.test;

import com.xiaobi.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

public class ConstructorsTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.scan("com.xiaobi.service");
		ac.scan("com.xiaobi.mapper");
		ac.refresh();

		System.out.println(ac.getBean(OrderService.class));

	}
}
