package com.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//0 XML配置Spring MVC
//实现自定义SPI
//@HandlesTypes(WebInit.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
//        List<WebInit> list = new ArrayList<WebInit>();
//        for (Class<?> aClass : c) {
//            try {
//                list.add((WebInit) aClass.newInstance());
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        for (WebInit webInit : list) {
//            webInit.start(ctx);
//        }
        System.out.println("web容器启动，实现Servlet3.0规范");
    }
}
