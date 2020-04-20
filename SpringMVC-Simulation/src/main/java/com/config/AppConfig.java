package com.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
//0 XML配置Spring MVC：https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-config
@Configuration
@ComponentScan("com")
@EnableWebMvc //EnableWebMvc：相当于<annotation:driver>这串代码，这串代码的含义是开启Spring MVC注解
//WebMvcConfigurer：这个类里面的所有接口贯穿了所有Spring MVC的配置
//WebMvcConfigurer和EnableWebMvc必须一起使用，或则public class AppConfig extends WebMvcConfigurationSupport这样就不用@EnableWebMvc注解
public class AppConfig implements WebMvcConfigurer {


    // 将自定义适配器放到第一位，为了防止适配参数的时候，匹配到Spring自带的适配器
    // 比如getUser1(HttpServletRequest request,@StaffParam Map map)这种情况，如果不加initArgumentResolvers的话，
    // 系统就不会自动匹配自定义适配器，他会匹配MapMethodProcessor这个适配器，所以需要将自定义的适配器提前。
    //方法名可以随便定义，但必须要用@Autowired来进行自动装配，如果用@Bean则会报错，因为requestMappingHandlerAdapter此时还没有数据。
    @Autowired
    public void initArgumentResolvers(RequestMappingHandlerAdapter requestMappingHandlerAdapter){
        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>(requestMappingHandlerAdapter.getArgumentResolvers());
        List<HandlerMethodArgumentResolver> customResolvers = requestMappingHandlerAdapter.getCustomArgumentResolvers();
        argumentResolvers.removeAll(customResolvers);
        argumentResolvers.addAll(0,customResolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(argumentResolvers);
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
    //拦截器
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

    @Override
    //添加自定义适配器
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ArgumentResolver());
    }

    @Override
    //添加视图解析器  解析Controller的返回值，比如返回值是ModelAndView、重定向视图
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    //配置消息转换器，也就是解析返回的数据是json还是Map还是String等，加了@ResponseBody
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new FastJsonHttpMessageConverter());
    }

    //剔除消息转换器
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
