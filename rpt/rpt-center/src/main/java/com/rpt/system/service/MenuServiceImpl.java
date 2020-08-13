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
//服务执行5秒，服务超时时间为3秒，但是执行了5秒，服务端会把任务执行完的
//服务的超时时间，是指如果服务执行时间超过了指定的超时时间则会抛一个warn
@Service(timeout = 5000)
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

    public String roundRobin(String name) {
        System.out.println("执行了");
        URL url = RpcContext.getContext().getUrl();
        return String.format("%s:%s,Hello,%s",url.getProtocol(),url.getPort(),name);
    }
}
