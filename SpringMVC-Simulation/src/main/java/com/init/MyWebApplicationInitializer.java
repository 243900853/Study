package com.init;

import com.config.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//SpringMVC零XML启动
//这里的代码相当于web.xml的基础配置
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    //web容器启动的时候会调用onStartup这个方法,为什么？因为tomcat等web容器实现了Servlet3.0规范
    //Servlet3.0规范（简称SPI，这个规范是java定义的）：
    //1、在指定目录下生成指定文件：META-INF\services\javax.servlet.ServletContainerInitializer
    //2、文件内容是自定义类的路径：org.springframework.web.SpringServletContainerInitializer
    //3、自定义类SpringServletContainerInitializer继承ServletContainerInitializer类，重写onStartup方法

    //Spring为开发者也实现了SPI规范：下面是Spring的实现文件路径
    //org\springframework\spring-web\5.0.8.RELEASE\spring-web-5.0.8.RELEASE.jar!\META-INF\services\javax.servlet.ServletContainerInitializer
    //SpringServletContainerInitializer使用了HandlesTypes注解，所以以后直接实现WebApplicationInitializer接口，Spring就可以调用onStartup方法

    //Web调用Spring onStartup方法流程：
    // 1、tomcat找到ServletContainerInitializer的实现类，并调用onStartup方法
    // 2、Spring的ServletContainerInitializer类继承了ServletContainerInitializer类，重写onStartup方法
    // 3、tomcat调用Spring的ServletContainerInitializer.onStartup方法
    //ServletContext：Web上下文，简单理解为web.xml能做的事情，ServletContext都能做
    public void onStartup(ServletContext servletContext) throws ServletException {
        //以注解方式初始化spring容器
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);
        //将DispatcherServlet装载到web容器
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("xbServlet", servlet);
//        registration.setInitParameter("xmlPathLocal","springMVC.xml");
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }
}
