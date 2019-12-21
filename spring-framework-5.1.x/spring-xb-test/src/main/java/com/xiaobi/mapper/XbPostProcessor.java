package com.xiaobi.mapper;

import com.xiaobi.service.IndexService;
import com.xiaobi.service.OrderService;
import com.xiaobi.service.UserService;
import com.xiaobi.service.XBService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
//后置处理器  修改BeanDefinition
public class XbPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition familyService = (GenericBeanDefinition)beanFactory.getBeanDefinition("familyService");
//		familyService.setBeanClass(XBService.class);
		System.out.println("familyService自动注入模型是："+familyService.getAutowireMode());
		System.out.println("后置处理器，修改BeanDefinition");

		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("familyService");
		beanDefinition.setDestroyMethodName(AbstractBeanDefinition.INFER_METHOD);

		GenericBeanDefinition orderService = (GenericBeanDefinition)beanFactory.getBeanDefinition("orderService");
		//orderService.setLenientConstructorResolution(false); //关闭宽松匹配
		//orderService.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		orderService.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		//存在多个模糊构造方法，如果不设置为构造方法自动注入，则会报错
		//但是如果这样写就不会报错，看源码createBeanInstance.obtainFromSupplier
		//他的意思是让Spring不用做后面的一系列推断构造方法操作
		orderService.setInstanceSupplier(()-> new OrderService(beanFactory.getBean(IndexService.class)));
	}
}
