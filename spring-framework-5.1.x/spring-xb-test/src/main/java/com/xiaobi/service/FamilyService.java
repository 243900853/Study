package com.xiaobi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FamilyService {

	public FamilyService(){
		System.out.println("print init");
	}

	@PostConstruct
	public void liftAfterInit(){
		System.out.println("初始化之后立马回调");
	}

//	@Autowired
//	XB XBService;
	@Autowired
	XBService xbService;

//	public void FamilyService(){
//		System.out.println("print Family1");
//	}
//	public FamilyService(XBService xbService){
//		this.xbService = xbService;
//		System.out.println("print Family2");
//	}

//	public XBService getXBService() {
//		return xbService;
//	}
//
//	public void setXBService(XBService xbService) {
//		this.xbService = xbService;
//	}
//
//	@Lookup
//	public XBService prototype(){
//		return null;
//	};
//
//	public void xx(){
//		XBService x = prototype();
//		System.out.println(x.hashCode());
//	}
}
