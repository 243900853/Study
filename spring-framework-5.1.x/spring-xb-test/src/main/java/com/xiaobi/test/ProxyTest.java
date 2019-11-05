package com.xiaobi.test;

import com.xiaobi.dao.UserDao;
import com.xiaobi.mybatis.FamilyFactory;
import com.xiaobi.proxy.*;

import java.util.List;

public class ProxyTest {
	public static void main(String[] args) {

		//静态代理 先打印日志在打印权限在打印IndexService逻辑
		PowerService target1 = new PowerService(new IndexService());
		Service proxy1 = new LogService(target1);
		proxy1.query();
		System.out.println("=========================");
		//静态代理 先打印权限在打印日志在打印IndexService逻辑
		LogService target2 = new LogService(new IndexService());
		Service proxy2 = new PowerService(target2);
		proxy2.query();
		System.out.println("=========================");
		//打印权限在打印IndexService逻辑
		IndexService target3 = new IndexService();
		Service proxy3 = new PowerService(target3);
		proxy3.query();
		System.out.println("=========================");

		//模拟mybatis动态代理执行Sql
		UserDao userDao = (UserDao) FamilyFactory.getMapper(UserDao.class);
		List<String> list = userDao.queryUser("xiaobi");
		//模拟Proxy.newProxyInstance
		Service proxy4 = (Service) DynamicProxy.getInsance(IndexService.class);
		proxy4.query();
	}
}
