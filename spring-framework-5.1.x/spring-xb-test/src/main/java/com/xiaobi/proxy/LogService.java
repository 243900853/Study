package com.xiaobi.proxy;

public class LogService implements Service {

	Service target;

	public LogService(Service service){
		this.target = service;
	}

	@Override
	public void query() {
		System.out.println("打印日志");
		target.query();
	}
}
