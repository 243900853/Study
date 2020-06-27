package com.xiaobi.controller;

import com.xiaobi.service.ConfigService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RocketmqController {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/sendOrderMQ.do")
    public Object sendOrderMQ(){
        //config是topic
        rocketMQTemplate.convertAndSend("config","测试消息2");
         return "操作成功";
    }

    @Autowired
    ConfigService configService;

    //第一步：Controller发送MQ消息
    @RequestMapping("/insert.do")
    public Object insert(@RequestParam Map<String,Object> map) {
        configService.sendTransactionMessage(map);
        return "操作成功";
    }
}
