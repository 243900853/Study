package com.xiaobi.CGLib;

public class A {

	public String cglibMethod(String name){
		System.out.println("CGLib动态代理A对象"+name);
		return name;
	}

	private String type;
	private String name;

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}
}
