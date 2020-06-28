package com.xiaobi.model;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;


public class MyTransactionListener implements TransactionListener {
    //本地事务在此处执行
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("执行事务");
    //本地事务执行过长的时候 可以挂起他
        //本地事务执行结果依赖别的事务执行的结果的时候可以挂起

       if (msg.getTags().equals("t1")){
//           connection.commit()
           return LocalTransactionState.COMMIT_MESSAGE;
       } else if (msg.getTags().equals("t2")) {
//           try {
//
//           }catch (){
//
//           }
//           connection.rollback()
           return LocalTransactionState.ROLLBACK_MESSAGE;
       }
        return LocalTransactionState.UNKNOW;
    }

    //A    B


    //提供给MQ的回调
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        //确认本地事务的执行情况 决定 提交还是回滚
        //代码检查B事务有没有执行完
        System.out.println("确认回调");
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
