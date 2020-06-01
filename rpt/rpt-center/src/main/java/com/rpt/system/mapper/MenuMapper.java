package com.rpt.system.mapper;

import com.rpt.system.bean.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    public List<Menu> queryMenuByLevel(String level);
}
