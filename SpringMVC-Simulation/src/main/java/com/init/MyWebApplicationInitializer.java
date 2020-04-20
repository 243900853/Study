package com.init;

import com.config.AppConfig;
import com.servlet.WebInit;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//0 XML配置Spring MVC
//SpringMVC零XML启动
//这里的代码相当于web.xml的基础配置
//https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html
//implements WebApplicationInitializer Servlet的SPI
//implements WebInit 自定义SPI
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    //重点：web容器启动的时候会调用onStartup这个方法,为什么？因为tomcat等web容器实现了Servlet3.0规范
    //Servlet3.0规范（简称SPI，这个规范是java定义的）：
    //1、在指定目录下生成指定文件：META-INF\services\javax.servlet.ServletContainerInitializer（这个文件的名字就是需要实现的接口路径）
    //2、文件内容是自定义类的路径：org.springframework.web.SpringServletContainerInitializer（文件的内容就是需要实现ServletContainerInitializer接口的类名）
    //3、自定义类SpringServletContainerInitializer继承ServletContainerInitializer类，重写onStartup方法

    //Spring为开发者也实现了SPI规范：下面是Spring的实现文件路径
    //org\springframework\spring-web\5.0.8.RELEASE\spring-web-5.0.8.RELEASE.jar!\META-INF\services\javax.servlet.ServletContainerInitializer
    //SpringServletContainerInitializer使用了HandlesTypes注解，所以以后直接实现WebApplicationInitializer接口，Spring就可以调用onStartup方法

    //为什么需要WebApplicationInitializer这个接口，开发者按照不同的需求在web容器启动的时候加载不同的内容，
    //就要在META-INF\services\javax.servlet.ServletContainerInitializer文件里面指定多个实现类，
    //Spring为了让开发者更方便的操作，就定义了WebApplicationInitializer这个接口，以后直接实现这个接口，就可以直接满足开发者在web启动时，做不同的操作

    //Web调用Spring onStartup方法流程：
    // 1、tomcat找到ServletContainerInitializer的实现类，并调用onStartup方法
    // 2、Spring的ServletContainerInitializer类继承了ServletContainerInitializer类，重写onStartup方法
    // 3、tomcat调用Spring的ServletContainerInitializer.onStartup方法
    //重点：ServletContext：Web上下文，简单理解为web.xml能做的事情，ServletContext都能做
    public void onStartup(ServletContext servletContext) {
        //以注解方式初始化spring容器
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);
        //如果AppConfig extends WebMvcConfigurationSupport，并且加了ac.refresh();这段代码，启动的时候会报No ServletContext set错
        //解决办法AppConfig extends WebMvcConfigurationSupport，启用ac.setServletContext(servletContext);和ac.refresh();
//        ac.setServletContext(servletContext);
//        ac.refresh();
        //将DispatcherServlet装载到web容器
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("xbServlet", servlet);
        //Spring MVC的地址
//        registration.setInitParameter("xmlPathLocal","springMVC.xml");
        registration.setLoadOnStartup(1);
        registration.addMapping("*.do");
    }
}
