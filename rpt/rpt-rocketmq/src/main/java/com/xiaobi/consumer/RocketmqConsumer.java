package com.xiaobi.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "config",consumerGroup = "rpt_rocketmq_producer")
public class RocketmqConsumer implements RocketMQListener<String> {

    public void onMessage(String s) {
        System.out.println(s);
    }
}
