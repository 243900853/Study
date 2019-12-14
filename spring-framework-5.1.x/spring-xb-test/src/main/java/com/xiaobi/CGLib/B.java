package com.xiaobi.CGLib;

public class B {
	private String type;
	private String name;

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "type:"+type+"--name"+name;
	}
}
