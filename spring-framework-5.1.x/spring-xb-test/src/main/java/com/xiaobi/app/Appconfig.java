package com.xiaobi.app;

import com.xiaobi.beanDefinition.FamilyScanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

//@ComponentScan("com.xiaobi")
//@ImportResource("classpath:spring-config.xml")
@EnableAspectJAutoProxy
@MapperScan
public class Appconfig {

}
