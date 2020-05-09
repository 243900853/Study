package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.swing.*;

//@SpringBootApplication(exclude = {}) exclude剔除自动装载的类
//SpringBoot是怎么扫描到META-INF/spring.factories ？
//SpringBootApplication.@EnableAutoConfiguration会注入AutoConfigurationImportSelector这个类，这个类会实现DeferredImportSelector
//并实现selectImports这个方法，getCandidateConfigurations.loadFactoryNames.loadSpringFactories.getResource就会扫描META-INF/spring.factories这个配置文件
//并将配置文件里面所有类加载到Spring容器中
@SpringBootApplication
public class App extends SpringBootServletInitializer {

    //用这个main方法去跑SpringBoot应用，如果打成war包，是用不了的，需要继承SpringBootServletInitializer，并实现configure方法
    //以jar包方式启动，使用内嵌的tomcat启动
    //这种方式是不会实现SPI规范
    public static void main(String[] args) {
        /*
        run方法作用：
        run.createApplicationContext方法会根据项目的类型来初始化Spring容器
        web项目：AnnotationConfigServletWebServerApplicationContext
        普通项目：AnnotationConfigApplicationContext
        run.refreshContext.refresh.refresh会加载Spring IOC容器
        run.refreshContext.refresh.refresh.onRefresh：ServletWebServerApplicationContext.onRefresh.createWebServer.getWebServerFactory会判断启动的web容器，如果不为1个会报错
        之后通过getWebServer启动web容器
        */
        /*
        SpringApplication作用：源码在run.new SpringApplication(primarySources)里面
        判断当前环境是web还是java还是webfulx(通过检查某个有没有class来判断，SpringApplication.deduceWebApplicationType里面)
        初始化一些组件（ApplicationListener、ApplicationContextInitializer，SpringApplication里面）
        推断组配置类，通过方法调用栈找到main方法所有（SpringApplication.deduceMainApplicationClass里面）
        */
        SpringApplication.run(App.class);
    }

    @Override
    //以war包方式启动，使用外部的tomcat启动，实现SpringBootServletInitializer，重写configure方法
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //SpringBootServletInitializer.onStartup.createRootApplicationContext.run.refreshContext.refresh.refresh.onRefresh：ServletWebServerApplicationContext.onRefresh.createWebServer
        //jar包方式启动WebServer和servletContext没有数据
        //war包方式启动WebServer和servletContext有数据
        return super.configure(builder).sources(App.class);
    }
}
