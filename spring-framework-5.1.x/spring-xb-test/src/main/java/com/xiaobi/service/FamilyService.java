package com.xiaobi.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FamilyService implements InitializingBean {

	public FamilyService(){
		System.out.println("print FamilyService");
	}

	public FamilyService(XBService xbService){
		System.out.println(xbService);
	}

	//注解Bean生命周期回调
	@PostConstruct
	public void liftAfterInit(){
		System.out.println("注解Bean生命周期回调");
	}

	@Autowired
	XB XBService;//自动注入先根据类型XB找，此时可以找到2个，在根据名字找，找到一个XBService注入
//	@Autowired
//	XBService xbService;

	//接口Bean生命周期回调
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("接口Bean生命周期回调");
	}

	public void xmlInit(){
		System.out.println("XML的Bean生命周期回调");
	}

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
	@Lookup
	public PrototypeObject prototype(){
		return null;
	};

	public void xx(){
		//单例对象方法想要有原型的效果
		PrototypeObject x = prototype();
		System.out.println(x.hashCode());
	}
}
