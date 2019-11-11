package com.xiaobi.test;

import com.xiaobi.app.Appconfig;
import com.xiaobi.beanDefinition.Family;
import com.xiaobi.beanDefinition.IndexScanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class ProcessTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(Appconfig.class);
		ac.scan("com.xiaobi");
		ClassPathBeanDefinitionScanner cp = new ClassPathBeanDefinitionScanner(ac);
		cp.addIncludeFilter(new AnnotationTypeFilter(Family.class));
		ac.refresh();
	}
}
