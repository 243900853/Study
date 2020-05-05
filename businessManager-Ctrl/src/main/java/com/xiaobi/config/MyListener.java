package com.xiaobi.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationListenerMethodAdapter;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//Spring会调用EventListenerMethodProcessor这个后置处理器，并执行afterSingletonsInstantiated这个方法
//使用注解方式监听事件，其实就是找到@EventListener的方法，然后将当前类包装成ApplicationListenerMethodAdapter这个监听类
//然后将当前的监听类通过ConfigurableApplicationContext.addApplicationListener注册到事件管理器里面
//AbstractApplicationContext是ConfigurableApplicationContext的子类

@Component
public class MyListener {

    //ContextRefreshedEvent:Spring容器Refreshed了事件
    //SpringBoot监听器
    //当参数是Object的时候，对任意事件感兴趣
    //AbstractApplicationContext.publishEvent.ApplicationEventMulticaster这个就是SpringBoot事件监听器
    // ApplicationEventMulticaster这个是所有监听器的父类
    @EventListener
    public void listenerService(ContextRefreshedEvent contextRefreshedEvent){
        System.out.println("SpringBoot注解方式监听服务");
        ApplicationContext applicationContext = (ApplicationContext) contextRefreshedEvent.getSource();
        System.out.println("Spring容器Refreshed了"+applicationContext);
    }
}
