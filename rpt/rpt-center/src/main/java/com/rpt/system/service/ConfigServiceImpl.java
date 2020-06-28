package com.rpt.system.service;

import com.rpt.system.mapper.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    //@Transactional交给Spring管理事务
    @Transactional
    public boolean insert(Map<String, Object> map) {
        return configMapper.insert(map);
    }

    public int checkTransaction(String id) {
        return configMapper.selectCount(id);
    }

}
