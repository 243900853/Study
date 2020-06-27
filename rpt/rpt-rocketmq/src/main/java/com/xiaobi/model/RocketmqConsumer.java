package com.xiaobi.model;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragelyByCircle;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class RocketmqConsumer {

//    //多线程多队列轮循方式消费无序消息
//    //new MessageListenerConcurrently() 多线程多队列
//    //consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragelyByCircle());
//    public static void main(String[] args) throws InterruptedException, MQClientException {
//        //当生产者组内有生产者无法提供事务回调的时候就会由组内其他生产者提供事务回调，生产者A宕机，由生产者B来进行事务回调。
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
//        consumer.setNamesrvAddr("localhost:9876");
//        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragelyByCircle());
//        //注意这里的topic要和消费者的topic一致
//        consumer.subscribe("TopicTest", "*");
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                for (MessageExt msg : msgs) {
//                    System.out.printf("%s Receive New Messages: %s Queue：%s Tag：%s %n", Thread.currentThread().getName(), new String(msg.getBody()),msg.getQueueId(),msg.getTags());
//                }
////                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//        consumer.start();
//        System.out.printf("Consumer Started.%n");
//    }

//    //单线程单队列消费有序消息
//    //new MessageListenerOrderly()
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
//    //consumer.subscribe("TopicTest", MessageSelector.bySql("i=3 OR i=4"));
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

//    //广播模式：多线程多队列消费无序消息
//    //consumer.setMessageModel(MessageModel.BROADCASTING);
//    public static void main(String[] args) throws InterruptedException, MQClientException {
//        //当生产者组内有生产者无法提供事务回调的时候就会由组内其他生产者提供事务回调，生产者A宕机，由生产者B来进行事务回调。
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
//        //设置成广播模式，各个消费者消费所有生产者数据 默认是集群模式MessageModel.CLUSTERING
//        consumer.setMessageModel(MessageModel.BROADCASTING);
//        consumer.setNamesrvAddr("localhost:9876");
//        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragelyByCircle());
//        //注意这里的topic要和消费者的topic一致
//        consumer.subscribe("TopicTest", "*");
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                for (MessageExt msg : msgs) {
//                    System.out.printf("%s Receive New Messages: %s Queue：%s Tag：%s %n", Thread.currentThread().getName(), new String(msg.getBody()),msg.getQueueId(),msg.getTags());
//                }
////                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//        consumer.start();
//        System.out.printf("Consumer Started.%n");
//    }

    //消息重发：多线程多队列消费无序消息
    //ConsumeConcurrentlyStatus.RECONSUME_LATER消息重发，不支持广播模式
    public static void main(String[] args) throws InterruptedException, MQClientException {
        //当生产者组内有生产者无法提供事务回调的时候就会由组内其他生产者提供事务回调，生产者A宕机，由生产者B来进行事务回调。
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragelyByCircle());
        //设置重试次数
        consumer.setMaxReconsumeTimes(3);
        //注意这里的topic要和消费者的topic一致
        consumer.subscribe("TopicTest", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.printf("%s Receive New Messages: %s Queue：%s Tag：%s %n", Thread.currentThread().getName(), new String(msg.getBody()),msg.getQueueId(),msg.getTags());
                }
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }

}
