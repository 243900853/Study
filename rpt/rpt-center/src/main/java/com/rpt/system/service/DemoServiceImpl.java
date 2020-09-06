package com.rpt.system.service;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

@Service(timeout = 3000)
public class DemoServiceImpl implements DemoService {
    @Override
    public void timeout() {
        System.out.println("开始执行了");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行结束了");
    }

    @Override
    public String sayHello(String name) {
        System.out.println("开始执行了");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行结束了");
        return name;
    }
}
