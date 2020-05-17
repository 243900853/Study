package com.xiaobi.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

@Component
public class FamilyLifecycle implements SmartLifecycle {
	// 将自定义适配器放到第一位，为了防止适配参数的时候，匹配到Spring自带的适配器
	// 比如getUser1(HttpServletRequest request,@StaffParam Map map)这种情况，如果不加initArgumentResolvers的话，
	// 系统就不会自动匹配自定义适配器，他会匹配MapMethodProcessor这个适配器，所以需要将自定义的适配器提前。
	//方法名可以随便定义，但必须要用@Autowired来进行自动装配，如果用@Bean则会报错，因为requestMappingHandlerAdapter此时还没有数据
	//这里有个很奇怪的问题：如果放到AppConfig下面会在WebMvcConfigurationSupport.resourceHandlerMapping下面报WebMvcConfigurationSupport.servletContext为空错误
	//感觉和Spring先后执行的时机有关
	@Autowired
	public void initArgumentResolvers(RequestMappingHandlerAdapter requestMappingHandlerAdapter){
		List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>(requestMappingHandlerAdapter.getArgumentResolvers());
		List<HandlerMethodArgumentResolver> customResolvers = requestMappingHandlerAdapter.getCustomArgumentResolvers();
		argumentResolvers.removeAll(customResolvers);
		argumentResolvers.addAll(0,customResolvers);
		requestMappingHandlerAdapter.setArgumentResolvers(argumentResolvers);
	}

	@Override
	public void start() {
		System.out.println("========================SmartLifecycle Start========================");
		System.out.println("容器启动");
	}

	@Override
	public void stop() {
		System.out.println("========================Lifecycle End========================");
		System.out.println("容器停止");
	}

	@Override
	public boolean isRunning() {
		System.out.println("容器是否正在运行");
		return false;
	}

	@Override
	public boolean isAutoStartup() {
		System.out.println("显式调用，如果设置为false,也就是通过ac.start()进行启动，true，容器启动自动调用start()方法");
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		System.out.println("如果实现SmartLifecycle，停止方法只调用这个。");
		System.out.println("========================SmartLifecycle End========================");
	}

	@Override
	public int getPhase() {
		System.out.println("设置优先等级，多个类实现SmartLifecycle时用到，值越小越先调用，停止反之。");
		return 0;
	}
}
