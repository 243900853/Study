package com.xiaobi.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//切面
@Component
@Aspect
class FamilyAspect {
	//切点
	@Pointcut("execution(* com.xiaobi.service..*(..))")//service包下面的所有方法，所有参数
	public void anyPublicMethod(){}

	@Pointcut("args(java.lang.String)")//只有一个参数是String类型
	public void pointCutArgs(){}

	@Pointcut("within(com.xiaobi.service.IndexService)")//只作用到IndexService类
	public void pointCutWithin(){}

	@Pointcut("this(com.xiaobi.service.XBService1)")//继承XBService1的代理类
	public void pointCutThis(){}

	@Before("pointCutThis()&&pointCutArgs()")
	public void doAccessCheck(){
		System.out.println("----------------在方法调用之前AOP拦截----------------");
	}
}
