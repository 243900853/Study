package com.rpt.system.service;

//服务降级
public class DemoServiceMock implements DemoService {

    @Override
    public void timeout() {
        System.out.println("调用timeout方法，出现RPC异常，进行mock");
    }

    @Override
    public String sayHello(String name) {
        return "调用sayHello方法，出现RPC异常，进行mock";
    }
}
