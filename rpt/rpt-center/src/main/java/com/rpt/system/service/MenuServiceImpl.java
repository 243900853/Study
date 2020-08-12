package com.rpt.system.service;


import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.rpt.system.bean.Menu;
import com.rpt.system.dao.MenuDao;
import com.rpt.system.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//注意这里的service不是Spring的是dubbo的
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuDao;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryMenuByLevel(String level) {
        return menuDao.queryMenuByLevel(level);
    }

    public List<Menu> queryMenuByLevelToMybatis(String level) {
        return menuMapper.queryMenuByLevel(level);
    }

    public String queryMenuById(String menuId) {
        System.out.println("执行了");
        URL url = RpcContext.getContext().getUrl();
        return String.format("%s:%s,Hello,%s",url.getProtocol(),url.getPort(),menuId);
    }
}
