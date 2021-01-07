package com.importbean;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		//通过UserDao构建一个通用的bd
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserDao.class);
		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) builder.getBeanDefinition();
		System.out.println(genericBeanDefinition.getBeanClassName());
		//Spring将com.importbean.UserDao转换成对象后，传入到UserDao代理对象构造方法中
		genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.importbean.UserDao");
		//修改UserDao的BeanClass类型为MyFactoryBean，目的是为了在MyFactoryBean中返回代理对象赋值给UserDao接口
		genericBeanDefinition.setBeanClass(MyFactoryBean.class);
		//往Spring容器中注册BeanDefinition，name:userDao BeanDefinition:genericBeanDefinition type:MyFactoryBean
		registry.registerBeanDefinition("userDao",genericBeanDefinition);
	}
}
