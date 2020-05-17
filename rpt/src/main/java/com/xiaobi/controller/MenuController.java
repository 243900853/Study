package com.xiaobi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    @RequestMapping("/test.do")
    public Object test(){
        return "test";
    }
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
