package com;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rpt.system.bean.Menu;
import com.rpt.system.service.ConfigService;
import com.rpt.system.service.DemoService;
import com.rpt.system.service.MenuService;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = ConsumerApp.class)
public class TestDubbo {
    //【负载均衡】
    // loadbalance = "roundrobin" 轮询负载均衡
    //【服务超时】
    //timeout = 1000 服务调用超时时间为1秒,默认为3秒,如果这1秒内没收到服务器结果,则会报错,不会继续接收请求的结果
    //服务端超时报warn 消费端超时报错
    //【集群容错】
    //cluster = "failfast" 快速失败,只发起一次调用,失败立即报错
    //默认是failover,重试2次,retries = 2可以配置重试次数
    //服务器端和消费端都配置cluster,以消费端为主
    @Reference(loadbalance = "roundrobin",timeout = 1000,cluster = "failfast",mock = "fail: return 123")
    private MenuService menuService;
    @Reference
    private ConfigService configService;
    //【服务降级】
    //mock = "fail: return 123" 服务访问失败直接返回123
    //mock = "true" 服务访问失败调用MenuServiceMock.失败的方法;MenuServiceMock implement MenuService;也可以直接给类名
    // 【本地存根】
    //stub = "true"
    // 消费端调用远程方法的时候会先找到"接口名+Stub"的类对象，并先执行stub里面的远程方法
    // 在执行远程服务时，如果中间报错，有捕获异常则处理异常逻辑，没有则会回调mock的远程方法，来进行异常的捕获与处理
    @Reference(timeout = 1000,mock = "true",stub = "true")
    private DemoService demoService;

    @org.junit.Test
    public void jdbcTest(){
        List<Menu> menus = menuService.queryMenuByLevel("1");
        for (Menu menu : menus) {
            System.out.println(menu.getName());
        }
    };

    @org.junit.Test
    public void testMybatisQuery() {
        List<Menu> menus = menuService.queryMenuByLevelToMybatis("1");
        for (Menu menu : menus) {
            System.out.println(menu.getName());
        }
    }

    @org.junit.Test
    public void localTest(){
        for (int i=0;i<100;i++){
            String menuId = menuService.roundRobin("1");
            System.out.println(menuId);
        }
    };

    @org.junit.Test
    public void timeoutTest(){
        demoService.timeout();
    }

}
