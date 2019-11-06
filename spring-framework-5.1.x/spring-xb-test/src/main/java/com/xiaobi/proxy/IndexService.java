package com.xiaobi.proxy;

public class IndexService implements Service {
	@Override
	public String query(String name) {
		System.out.println("IndexService逻辑");
		return null;
	}
}
