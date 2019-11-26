package com.xiaobi.CGLib;

import com.xiaobi.mybatis.CGLibMethodInterceptor;
import com.xiaobi.service.FamilyService;
import com.xiaobi.service.IndexService;
import com.xiaobi.service.XBService1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
//在Appconfig里面用@Import导入，所以Spring才扫描出FamilyAopBeanPostProcessor
//不能用@Component来注入,因为如果以后这个后置处理器给其他程序员扩展使用,要让程序员扫描这个后置处理器所在的包才能使用
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
