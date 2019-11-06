package com.xiaobi.proxy;

public class LogService implements Service {

	Service target;

	public LogService(Service service){
		this.target = service;
	}

	@Override
	public String query(String name) {
		System.out.println("打印日志");
		target.query(name);
		return null;
	}
}
