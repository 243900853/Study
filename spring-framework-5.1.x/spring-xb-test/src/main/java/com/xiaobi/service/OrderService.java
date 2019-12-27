package com.xiaobi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderService {
	@Autowired
	XB XBService;//自动注入先根据类型XB找，此时可以找到2个，在根据名字找，找到一个XBService注入
	@Autowired
	List<XB> xbServices;
	@Lazy
	@Autowired
	UserService userService;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	private String age;

	public void setUserService(UserService userService){
		System.out.println("自动执行Set方法");
	}


//	public OrderService() {
//		System.out.println("默认无参");
//	}

	public OrderService(UserService userService) {
		System.out.println("1个参数构造方法--userService");
	}
	public OrderService(IndexService indexService) {
		System.out.println("1个参数构造方法--indexService");
	}

//	public OrderService(UserService userService,IndexService indexService) {
//		System.out.println("2个构造方法");
//	}
//	@Autowired
//	public OrderService(Class clazz) {
//		System.out.println("程序员有指定参数"+clazz.getSimpleName());
//	}

}
