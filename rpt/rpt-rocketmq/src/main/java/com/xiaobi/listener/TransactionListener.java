package com.xiaobi.listener;

import com.alibaba.fastjson.JSON;
import com.xiaobi.service.ConfigService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import java.util.Map;

//@RocketMQTransactionListener
//public class TransactionListener  implements RocketMQLocalTransactionListener{
//
//    @Autowired
//    ConfigService configService;
//
//    //执行本地事务
////    @Transactional
//    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
//        try {
//            System.out.println("MQ通过监听器执行消息");
//            System.out.println(message.getHeaders().get("rocketmq_TOPIC"));
//            //调用多个事务 并且是异步情况  就要用挂起
//            return configService.insert(message);
//        }catch (Exception e){
//            System.out.println("程序异常-Spring本地事务回滚-MQ销毁消息");
//            return RocketMQLocalTransactionState.ROLLBACK;
//        }
//    }
//
//    //MQ挂起后，一分钟回调
//    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
//        Map map = JSON.parseObject(new String((byte[]) message.getPayload()), Map.class);
//        //检查生产者操作是否成功
//        if (configService.checkTransaction(String.valueOf(map.get("id")))>0) {
//            System.out.println("MQ挂起-回调成功-生产者本地事务正常-数据库提交事务-等待消费者消费消息");
//            return RocketMQLocalTransactionState.COMMIT;
//        }
//        System.out.println("MQ挂起-回调失败-生产者本地事务异常-数据库回滚事务-MQ销毁消息");
//        return RocketMQLocalTransactionState.ROLLBACK;
//    }
//}
