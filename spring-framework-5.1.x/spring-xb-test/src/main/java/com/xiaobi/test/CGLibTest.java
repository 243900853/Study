package com.xiaobi.test;

import com.xiaobi.CGLib.A;
import com.xiaobi.CGLib.CGLibAppconfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CGLibTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CGLibAppconfig.class);
		System.out.println(ac.getBean(CGLibAppconfig.class));
	}
}
