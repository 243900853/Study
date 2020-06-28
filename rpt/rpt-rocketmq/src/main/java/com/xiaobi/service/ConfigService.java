package com.xiaobi.service;

import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.util.Map;

public interface ConfigService {
    /**
     * 操作事务
     */
    RocketMQLocalTransactionState insert(Message message);

    /**
     * 检查事务
     */
    int checkTransaction(String id);

    /**
     * 发送MQ事务消息
     */
    void  sendTransactionMessage(Map<String,Object> map);
}
