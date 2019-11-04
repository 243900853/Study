package com.xiaobi.lifecycle;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class FamilyLifecycle implements SmartLifecycle {
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
