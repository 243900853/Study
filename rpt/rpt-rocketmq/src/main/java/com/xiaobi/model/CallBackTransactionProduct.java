package com.xiaobi.model;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;

public class CallBackTransactionProduct {
    //发送事务消息的生产者宕机，这个生产者只提供回调，供消费者能正常的消费消息
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionListener transactionListener = new MyTransactionListener();
        //这里的组名要和生产者一样
        TransactionMQProducer producer = new TransactionMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setTransactionListener(transactionListener);
        producer.start();
    }
}
