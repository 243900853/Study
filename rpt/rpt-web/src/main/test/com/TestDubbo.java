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
    //轮询负载均衡
    //服务调用超时时间为1秒，默认为3秒，如果这1秒内没收到服务器结果，则会报错，不会继续接收请求的结果
    @Reference(loadbalance = "roundrobin",timeout = 1000)
    private MenuService menuService;
    @Reference
    private ConfigService configService;
    @Reference(timeout = 1000)
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
