package com.rpt.system.mq;

import org.apache.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragelyByCircle;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RocketmqConsumer {

    //多线程多队列消费无序消息
    public static void main(String[] args) throws InterruptedException, MQClientException {
        //当生产者组内有生产者无法提供事务回调的时候就会由组内其他生产者提供事务回调，生产者A宕机，由生产者B来进行事务回调。
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragelyByCircle());
        //注意这里的topic要和消费者的topic一致
        consumer.subscribe("TopicTest", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.printf("%s Receive New Messages: %s Queue：%s Tag：%s %n", Thread.currentThread().getName(), new String(msg.getBody()),msg.getQueueId(),msg.getTags());
                }
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }

//    //单线程单队列消费有序消息
//    public static void main(String[] args) throws InterruptedException, MQClientException {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
//        consumer.setNamesrvAddr("localhost:9876");
//        //注意这里的topic要和消费者的topic一致
//        consumer.subscribe("TopicTest", "*");
//        consumer.registerMessageListener(new MessageListenerOrderly() {
//            @Override
//            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext consumeOrderlyContext) {
//                for (MessageExt msg : msgs) {
//                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), new String(msg.getBody()));
//                }
////                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
//                return ConsumeOrderlyStatus.SUCCESS;
//            }
//        });
//        consumer.start();
//        System.out.printf("Consumer Started.%n");
//    }

//    //多线程多队列过滤消费无序消息
//    public static void main(String[] args) throws InterruptedException, MQClientException {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
//        consumer.setNamesrvAddr("localhost:9876");
//        //注意这里的topic要和消费者的topic一致，这里的第二个参数是筛选过滤，消费Tag0或者Tag1的消息
//        //默认不支持Sql过滤，需要在broker.conf配置里面加enablePropertyFilter=true开启支持Sql过滤
//        consumer.subscribe("TopicTest", "Tag0||Tag1");
//        consumer.subscribe("TopicTest", MessageSelector.bySql("i=3 OR i=4"));
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                for (MessageExt msg : msgs) {
//                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), new String(msg.getBody()));
//                }
////                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//        consumer.start();
//        System.out.printf("Consumer Started.%n");
//    }

}
