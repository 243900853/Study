package com.xiaobi.servlet;

import com.ConsumerApp;
import com.xiaobi.config.AppConfig;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//0 XML配置Spring MVC
//实现自定义SPI
@Component
public class MyServletContainerInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("实现ServletContextInitializer这个接口，内嵌的tomcat才能初始化Servlet上下文");
        //以注解方式初始化spring容器
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);
        //如果AppConfig extends WebMvcConfigurationSupport，并且加了ac.refresh();这段代码，启动的时候会报No ServletContext set错
        //解决办法AppConfig extends WebMvcConfigurationSupport，启用ac.setServletContext(servletContext);和ac.refresh();
//        ac.setServletContext(servletContext);
//        ac.refresh();
        //将DispatcherServlet装载到web容器
//        SpringMVC是把Spring容器放到DispatcherServlet中
//        SpringBoot是把DispatcherServlet放到Spring容器中。
//        具体源码：DispatcherServletAutoConfiguration.dispatcherServlet().DispatcherServlet.FrameworkServlet.ApplicationContextAware.setApplicationContext
//        源码实现过程：当Spring容器中有ApplicationContextAware接口的实现类的话就会调用实现类的setApplicationContext方法，并把初始化之后的Spring容器对象传进去
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("xbServlet", servlet);
        //Spring MVC的地址
//        registration.setInitParameter("xmlPathLocal","springMVC.xml");
        registration.setLoadOnStartup(1);
        registration.addMapping("*.do");
    }
}
