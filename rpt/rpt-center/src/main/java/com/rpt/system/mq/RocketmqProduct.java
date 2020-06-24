package com.rpt.system.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

public class RocketmqProduct {

//    //发送无序消息
//    public static void main(String[] args) throws Exception {
//        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
//        producer.setNamesrvAddr("localhost:9876");
//        producer.start();
//        for (int i = 0; i < 10; i++) {
//            Message msg = new Message("TopicTest" /* Topic */,"Tag"+i /* Tag */,
//                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//            );
//            SendResult sendResult = producer.send(msg);
//            System.out.printf("%s%n", sendResult);
//        }
//        producer.shutdown();
//    }

//    //发送有序消息
//    public static void main(String[] args) throws Exception {
//        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
//        producer.setNamesrvAddr("localhost:9876");
//        producer.start();
//        for (int i = 0; i < 10; i++) {
//            Message msg = new Message("TopicTest" /* Topic */,"TagA" /* Tag */,
//                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//            );
//            //arg需要是唯一的标识，可以存放id
//            SendResult sendResult = producer.send(msg,new SelectMessageQueueByHash(),"2");
//            System.out.printf("%s%n", sendResult);
//        }
//        producer.shutdown();
//    }

//    //发送延迟有序消息
//    public static void main(String[] args) throws Exception {
//        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
//        producer.setNamesrvAddr("localhost:9876");
//        producer.start();
//        for (int i = 0; i < 1; i++) {
//            Message msg = new Message("TopicTest" /* Topic */,"TagA" /* Tag */,
//                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//            );
//            //设置延迟级别，不能直接设置延迟多久，级别可以在控制台的配置里面看到
//            msg.setDelayTimeLevel(3);
//            //arg需要是唯一的标识，可以存放id
//            SendResult sendResult = producer.send(msg,new SelectMessageQueueByHash(),"1");
//            System.out.printf("%s%n", sendResult);
//        }
//        producer.shutdown();
//    }

//    //发送批量消息
//    public static void main(String[] args) throws Exception {
//        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
//        producer.setNamesrvAddr("localhost:9876");
//        producer.start();
//        List<Message> list = new ArrayList<Message>();
//        for (int i = 0; i < 10; i++) {
//            Message msg = new Message("TopicTest" /* Topic */,"TagA" /* Tag */,
//                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//            );
//            list.add(msg);
//        }
//        //发送批量消息,一次发送list.length条记录，不支持延迟、过滤、事务
//        //每次发送最好不超过1M（topic名称长度+消息传过去的参数+消息体大小+20byte日志数据大小=单条消息大小），超过4M会报错
//        SendResult sendResult = producer.send(list);
//        System.out.printf("%s%n", sendResult);
////        //大批量数据进行数据截取后，在发送消息
////        ListSplitter splitter = new ListSplitter(list);
////        while (splitter.hasNext()) {
////            try {
////                List<Message>  listItem = splitter.next();
////                SendResult sendResult = producer.send(listItem);
////                System.out.printf("%s%n", sendResult);
////            } catch (Exception e) {
////                e.printStackTrace();
////                //handle the error
////            }
////        }
//        producer.shutdown();
//    }

//    //发送过滤无序消息
//    public static void main(String[] args) throws Exception {
//        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
//        producer.setNamesrvAddr("localhost:9876");
//        producer.start();
//        for (int i = 0; i < 10; i++) {
//            Message msg = new Message("TopicTest" /* Topic */,"Tag"+i /* Tag */,
//                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//            );
//            msg.putUserProperty("i",i+"");
//            SendResult sendResult = producer.send(msg);
//            System.out.printf("%s%n", sendResult);
//        }
//        producer.shutdown();
//    }

    //发送事务消息
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionListener transactionListener = new MyTransactionListener();
        TransactionMQProducer producer = new TransactionMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setTransactionListener(transactionListener);
        producer.start();
        //t1提交 t2回滚 其他挂起
        String[] tags = new String[] {"t1", "t2", "t3"};
        for (int i = 0; i < 3; i++) {
            try {
                Message msg = new Message("TopicTest", tags[i],
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);
                Thread.sleep(10);
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
