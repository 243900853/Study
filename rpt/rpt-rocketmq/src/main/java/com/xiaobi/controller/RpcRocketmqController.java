package com.xiaobi.controller;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequestMapping("rpcRocketmq")
public class RpcRocketmqController {
    @Autowired
    RocketMQTemplate rocketMQTemplate;
    //第一步：Controller发送MQ消息
    @RequestMapping("/insert.do")
    public Object insert(@RequestParam Map<String,Object> map) {
        System.out.println("发送MQ消息");
        Message message = MessageBuilder.withPayload(JSON.toJSONString(map)).build();
        rocketMQTemplate.sendMessageInTransaction("rpcConfig",message,null);
        return "操作成功";
    }
}
