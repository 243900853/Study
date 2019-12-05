package com.xiaobi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

	public OrderService() {
		System.out.println("默认无参");
	}

	public OrderService(UserService userService) {
		System.out.println("1个参数构造方法");
	}

	public OrderService(UserService userService,IndexService indexService) {
		System.out.println("2个构造方法");
	}

}
