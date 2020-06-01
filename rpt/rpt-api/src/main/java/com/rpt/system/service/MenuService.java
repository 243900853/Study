package com.rpt.system.service;

//import com.rpt.system.bean.Menu;

import java.util.List;

public interface MenuService<T> {

    public List<T> queryMenuByLevel(String level);
    public List<T> queryMenuByLevelToMybatis(String level);
}
