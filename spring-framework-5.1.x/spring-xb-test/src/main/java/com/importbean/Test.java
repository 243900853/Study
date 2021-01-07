package com.importbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(MyAppconfig.class);
//		configApplicationContext.getBean(UserDao.class).query("xiaobi");
		configApplicationContext.getBean(UserService.class).query();
	}
}
