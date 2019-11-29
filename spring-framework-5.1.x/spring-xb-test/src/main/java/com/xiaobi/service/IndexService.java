package com.xiaobi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexService{
	@Autowired
	UserService userService;

	public IndexService(){
		System.out.println("IndexService无参构造方法"+userService);
	}

	public void aopStr(String name,int i){
		System.out.println("两个参数，string，int");
	}

	public void aopStr(String name){
		System.out.println("一个参数string");
	}
}
