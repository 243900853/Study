package com.xiaobi.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "rpcConfig",consumerGroup = "rpt_rocketmq_producer")
public class RpcRocketmqConsumer implements RocketMQListener<String> {

    public void onMessage(String s) {
        try {
            System.out.println(s);

        }catch (Exception e){

        }

    }
}
