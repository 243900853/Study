package com.xiaobi.CGLib;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CGLibAppconfig {
	@Bean
	public A a(){
		System.out.println("init a");
		return new A();
	}

	@Bean
	public B b(){
		System.out.println("init b");
		a();
		return new B();
	}
}
