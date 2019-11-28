package com.xiaobi.test;

import com.xiaobi.app.Appconfig;
import com.xiaobi.beanDefinition.FamilyScanner;
import com.xiaobi.beanDefinition.IndexScanner;
import com.xiaobi.mapper.XbBeanDefinitionRegisterPostProcessor;
import com.xiaobi.mapper.XbPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class ProcessTest {
	public static void main(String[] args) {
		//自定义注解让后置处理器扫描出来
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(Appconfig.class);
		ac.scan("com.xiaobi.beanDefinition");
		ac.addBeanFactoryPostProcessor(new XbBeanDefinitionRegisterPostProcessor());
		ClassPathBeanDefinitionScanner cp = new ClassPathBeanDefinitionScanner(ac);
		cp.addIncludeFilter(new AnnotationTypeFilter(FamilyScanner.class));
		int scan = cp.scan("com.xiaobi.beanDefinition");//添加了新的注解需要重新扫描
		System.out.println(scan);
		ac.refresh();
	}
}
