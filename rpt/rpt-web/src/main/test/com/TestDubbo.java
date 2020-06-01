package com;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rpt.system.bean.Menu;
import com.rpt.system.service.MenuService;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = ConsumerApp.class)
public class TestDubbo {

    @Reference
    private MenuService menuService;

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
}
