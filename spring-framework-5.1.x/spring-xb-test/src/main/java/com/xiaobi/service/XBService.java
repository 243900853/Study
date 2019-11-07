package com.xiaobi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class XBService implements XB{

	@Autowired
	FamilyService familyService;
	public XBService(String name1,String name2,String name3){}

	public XBService() {
		System.out.println("print XBService");
	}
}
