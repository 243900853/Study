package com.xiaobi.service;

import com.alibaba.fastjson.JSON;
import com.xiaobi.mapper.ConfigMapper;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private ConfigMapper configMapper;

    //@Transactional交给Spring管理事务
    @Transactional
    public RocketMQLocalTransactionState insert(Message message) {
        System.out.println("执行程序逻辑");
        Map<String, Object> map = JSON.parseObject(new String((byte[]) message.getPayload()), Map.class);
        RocketMQLocalTransactionState rocketMQLocalTransactionState = configMapper.insert(map) ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
        //map.put(id,结果)
        if (String.valueOf(map.get("id")).equals("100")){
            System.out.println("程序报错");
            int i  = 1/0;
        }else  if(String.valueOf(map.get("id")).equals("101")){
            System.out.println("MQ消息挂起-等待监听器回调");
            rocketMQLocalTransactionState = RocketMQLocalTransactionState.UNKNOWN;
        }
        return rocketMQLocalTransactionState;
    }

    public int checkTransaction(String id) {
        return configMapper.selectCount(id);
    }

    //第二步：MQ发送事务消息
    public void sendTransactionMessage(Map<String, Object> map) {
        System.out.println("发送MQ消息");
        Message message = MessageBuilder.withPayload(JSON.toJSONString(map)).build();
        rocketMQTemplate.sendMessageInTransaction("config",message,null);
    }
}
