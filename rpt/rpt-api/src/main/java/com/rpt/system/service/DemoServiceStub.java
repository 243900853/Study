package com.rpt.system.service;

public class DemoServiceStub implements DemoService {

    private final DemoService demoService;
    //构造函数传入真正的远程代理对象
    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public void timeout() {
        try {
            //此代码在客户端执行，你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法等等
            demoService.timeout();
        }catch (Exception e){
            //你可以容错，可以做任何AOP拦截事项
            System.out.println("容错数据");
        }
    }

    @Override
    public String sayHello(String name) {
        //此代码在客户端执行，你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法等等
        try {
            //这里是真正的调用远程服务
            return demoService.sayHello(name);
        }catch (Exception e){
            //你可以容错，可以做任何AOP拦截事项
            return "容错数据";
        }
    }
}
