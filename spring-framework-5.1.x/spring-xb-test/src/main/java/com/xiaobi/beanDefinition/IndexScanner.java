package com.xiaobi.beanDefinition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.TypeFilter;

//模拟mybatis  让Srping扫描自定义注解
//调用ClassPathScanningCandidateComponentProvider提供的添加自定义注解方法
public class IndexScanner extends ClassPathBeanDefinitionScanner {
	public IndexScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	@Override
	public void addIncludeFilter(TypeFilter includeFilter) {
		super.addIncludeFilter(includeFilter);
	}
}
