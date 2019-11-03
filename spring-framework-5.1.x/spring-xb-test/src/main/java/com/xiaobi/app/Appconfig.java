package com.xiaobi.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@ComponentScan("com.xiaobi")
@ImportResource("classpath:spring-config.xml")
@EnableAspectJAutoProxy
public class Appconfig {

}
