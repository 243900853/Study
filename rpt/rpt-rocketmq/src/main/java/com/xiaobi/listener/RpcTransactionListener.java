package com.xiaobi.listener;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.rpt.system.service.ConfigService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.util.Map;

@RocketMQTransactionListener
public class RpcTransactionListener implements RocketMQLocalTransactionListener{

    @Reference
    ConfigService configService;

    //执行本地事务
//    @Transactional
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        RocketMQLocalTransactionState rocketMQLocalTransactionState = RocketMQLocalTransactionState.ROLLBACK;
        try {
            System.out.println("MQ通过监听器执行消息");
            System.out.println(message.getHeaders().get("rocketmq_TOPIC"));
            if (message.getHeaders().get("rocketmq_TOPIC").equals("rpcConfig")) {
                System.out.println("执行程序逻辑");
                Map<String, Object> map = JSON.parseObject(new String((byte[]) message.getPayload()), Map.class);
                rocketMQLocalTransactionState = configService.insert(map) ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
                //map.put(id,结果)
                if (String.valueOf(map.get("id")).equals("100")){
                    System.out.println("程序报错");
                    int i  = 1/0;
                }else  if(String.valueOf(map.get("id")).equals("101")){
                    System.out.println("MQ消息挂起-等待监听器回调");
                    rocketMQLocalTransactionState = RocketMQLocalTransactionState.UNKNOWN;
                }
            }
        }catch (Exception e){
            System.out.println("程序异常-Spring本地事务回滚-MQ销毁消息");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return rocketMQLocalTransactionState;
    }

    //MQ挂起后，一分钟回调
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        Map map = JSON.parseObject(new String((byte[]) message.getPayload()), Map.class);
        //检查生产者操作是否成功
        if (configService.checkTransaction(String.valueOf(map.get("id")))>0) {
            System.out.println("MQ挂起-回调成功-生产者本地事务正常-数据库提交事务-等待消费者消费消息");
            return RocketMQLocalTransactionState.COMMIT;
        }
        System.out.println("MQ挂起-回调失败-生产者本地事务异常-数据库回滚事务-MQ销毁消息");
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
