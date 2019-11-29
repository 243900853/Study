package com.xiaobi.app;

import com.xiaobi.CGLib.FamilyAopBeanPostProcessor;
import com.xiaobi.beanDefinition.FamilyScanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

//@ComponentScan("com.xiaobi")
//@ImportResource("classpath:spring-config.xml")
//@EnableAspectJAutoProxy(proxyTargetClass = true) proxyTargetClass = true强制使用CGLib代理
@EnableAspectJAutoProxy
@Configuration
@Import(FamilyAopBeanPostProcessor.class)
//@MapperScan
public class Appconfig {

}
