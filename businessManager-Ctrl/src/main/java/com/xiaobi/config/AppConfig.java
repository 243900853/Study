package com.xiaobi.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AppConfig {
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
        return new FastJsonHttpMessageConverter();
    }

    @Bean
    public MyViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    @Bean
    public Converter converter(){
        return new StringConverterDate("yyyy-MM-dd HH:mm:ss");
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        //设置线程池
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //设置最大线程池
        threadPoolTaskExecutor.setMaxPoolSize(15);
        //设置默认线程池
        threadPoolTaskExecutor.setCorePoolSize(10);
        //设置队列大小
        threadPoolTaskExecutor.setQueueCapacity(30);
        //设置失败策略 当待执行任务超过了队列所设置的最大长度，则会抛出异常
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    //如果想要线程池来跑监听器的话，首先得将ApplicationEventMulticaster事件驱动器配置到Spring容器中，然后配置线程池
    //配置事件驱动器参考源码：AbstractApplicationContext.refresh().initApplicationEventMulticaster()方法
    //线程池跑事件驱动器源码：AbstractApplicationContext.publishEvent.multicastEvent
    // Executor executor = this.getTaskExecutor();这串代码就是获取线程池来跑监听器
    //这里的BeanName必须是applicationEventMulticaster，因为在"配置事件驱动器参考源码"已经定义死了
    @Bean("applicationEventMulticaster")
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster(BeanFactory beanFactory,ThreadPoolTaskExecutor poolTaskExecutor){
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        simpleApplicationEventMulticaster.setTaskExecutor(poolTaskExecutor);
        return simpleApplicationEventMulticaster;
    }

    //配置内嵌tomcat 源码：在spring-boot-autoconfigure.jar/META-INF/spring.factories找到ServletWebServerFactoryAutoConfiguration这个类
    //具体在ServletWebServerFactoryAutoConfiguration.EmbeddedTomcat里面
    //SpringBoot默认有tomcat依赖，同时tomcat作为最先加载的web容器（顺序参考ServletWebServerFactoryAutoConfiguration.@Import），所以就算加了jetty依赖包，也是不会生效的，需要剔除tomcat依赖，才能使用其他web容器
    //yml配置参考ServletWebServerFactoryAutoConfiguration的@EnableConfigurationProperties({ServerProperties.class})
//    @Bean
    public TomcatServletWebServerFactory tomcat(){
        //getServletWebServerFactoryBean 如果数组长度大于1 抛异常，也就是注入TomcatServletWebServerFactory之后，如果还存在其他web容器的话就会报错
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(80);
        return tomcat;
    }

    //自定义方式配置当前web容器
    //目前SpringBoot只支持这四种web容器：Tomcat Jetty Netty undertow
    @Bean
    public WebServerFactoryCustomizer customizer(){
        WebServerFactoryCustomizer webServerFactoryCustomizer = new WebServerFactoryCustomizer() {
            //factory当前正在使用的web容器factory对象
            @Override
            public void customize(WebServerFactory factory) {
                TomcatServletWebServerFactory factory1 = (TomcatServletWebServerFactory) factory;
                factory1.setPort(80);
            }
        };
        return webServerFactoryCustomizer;
    }

}
