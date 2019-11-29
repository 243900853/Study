package com.xiaobi.test;

import com.xiaobi.app.Appconfig;
import com.xiaobi.service.IndexService;
import com.xiaobi.service.XBService1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(Appconfig.class);
		ac.scan("com.xiaobi.service");
		ac.scan("com.xiaobi.aspect");
		ac.refresh();
		//System.out.println(ac.getBean(XBService1.class));
		System.out.println(ac.getBean(IndexService.class));
		ac.getBean(IndexService.class).aopStr("IndexService");
		ac.getBean(IndexService.class).aopStr("IndexService",1);
	}
}
