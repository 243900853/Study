package com.xiaobi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	@Autowired
	IndexService indexService;
	public UserService(){
		System.out.println("UserService无参构造方法"+indexService);
	}
}
