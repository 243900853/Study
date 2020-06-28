package com.rpt.system.service;

import java.util.Map;

public interface ConfigService {
    /**
     * 操作事务
     */
    boolean insert(Map<String, Object> map);

    /**
     * 检查事务
     */
    int checkTransaction(String id);

}
