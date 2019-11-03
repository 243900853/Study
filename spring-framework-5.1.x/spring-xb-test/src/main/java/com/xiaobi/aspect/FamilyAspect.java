package com.xiaobi.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//切面
@Component
@Aspect
public class FamilyAspect {
	//切点
	@Pointcut("execution(* com.xiaobi.service..*(..))")
	public void anyPublicMethod(){

	}

	@Before("anyPublicMethod()")
	public void doAccessCheck(){
		System.out.println("AOP：指定包下面，在所有方法执行前调用这个方法");
	}
}
