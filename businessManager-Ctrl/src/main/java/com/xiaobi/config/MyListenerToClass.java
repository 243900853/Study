package com.xiaobi.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyListenerToClass implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("SpringBoot实现监听器方式监听服务");
        ApplicationContext applicationContext = (ApplicationContext) contextRefreshedEvent.getSource();
        System.out.println("Spring容器Refreshed了"+applicationContext);
    }
}
