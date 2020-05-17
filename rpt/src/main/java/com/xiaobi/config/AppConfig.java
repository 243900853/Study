package com.xiaobi.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

//开启代理
@EnableAspectJAutoProxy
@Configuration
public class AppConfig implements WebMvcConfigurer{



    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
        return new FastJsonHttpMessageConverter();
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
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster(BeanFactory beanFactory, ThreadPoolTaskExecutor poolTaskExecutor){
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        simpleApplicationEventMulticaster.setTaskExecutor(poolTaskExecutor);
        return simpleApplicationEventMulticaster;
    }

    //配置内嵌tomcat 源码：在spring-boot-autoconfigure.jar/META-INF/spring.factories找到ServletWebServerFactoryAutoConfiguration这个类
    //具体在ServletWebServerFactoryAutoConfiguration.EmbeddedTomcat里面
    //SpringBoot默认有tomcat依赖，同时tomcat作为最先加载的web容器（顺序参考ServletWebServerFactoryAutoConfiguration.@Import），所以就算加了jetty依赖包，也是不会生效的，需要剔除tomcat依赖，才能使用其他web容器
    //yml配置参考ServletWebServerFactoryAutoConfiguration的@EnableConfigurationProperties({ServerProperties.class})
    @Bean
    public TomcatServletWebServerFactory tomcat(){
        //getServletWebServerFactoryBean 如果数组长度大于1 抛异常，也就是注入TomcatServletWebServerFactory之后，如果还存在其他web容器的话就会报错
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(8080);
        return tomcat;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }


    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    //视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //设置跳转页面的前缀、后缀
        registry.jsp("/page/",".html");
    }

    //添加自定义适配器
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ArgumentResolver());
    }


    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }

}
