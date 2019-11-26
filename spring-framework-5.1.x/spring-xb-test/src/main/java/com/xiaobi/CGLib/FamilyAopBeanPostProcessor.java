package com.xiaobi.CGLib;

import com.xiaobi.mybatis.CGLibMethodInterceptor;
import com.xiaobi.service.FamilyService;
import com.xiaobi.service.IndexService;
import com.xiaobi.service.XBService1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

public class FamilyAopBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof XBService1){
			Enhancer enhancer = new Enhancer();
			//设置原对象
			enhancer.setSuperclass(XBService1.class);
			enhancer.setCallback(new CGLibMethodInterceptor());
			bean = enhancer.create();
			System.out.println("-------------------------AOP拦截动态代理--------------");
		}
		return bean;
	}
}
