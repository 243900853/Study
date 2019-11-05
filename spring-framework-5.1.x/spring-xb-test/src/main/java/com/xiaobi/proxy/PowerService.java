package com.xiaobi.proxy;

public class PowerService implements Service {

	Service target;
	public PowerService(Service service){
		this.target = service;
	}

	@Override
	public void query() {
		System.out.println("打印权限");
		target.query();
	}
}
