package com.xiaobi.test;

import com.xiaobi.app.Appconfig;
import com.xiaobi.service.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(Appconfig.class);
		ac.scan("com.xiaobi.service");
		ac.refresh();
		System.out.println(ac.getBean(IndexService.class));
	}
}
