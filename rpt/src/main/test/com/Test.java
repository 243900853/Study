package com;

import com.xiaobi.bean.Menu;
import com.xiaobi.dao.MenuDao;
import com.xiaobi.mapper.MenuMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = App.class)
public class Test {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private MenuMapper menuMapper;

    @org.junit.Test
    public void jdbcTest(){
        List<Menu> menus = menuDao.queryMenuByLevel("1");
        for (Menu menu : menus) {
            System.out.println(menu.getName());
        }
    };

    @org.junit.Test
    public void testMybatisQuery() {
        List<Menu> menus = menuMapper.queryMenuByLevel("1");
        for (Menu menu : menus) {
            System.out.println(menu.getName());
        }
    }
}
