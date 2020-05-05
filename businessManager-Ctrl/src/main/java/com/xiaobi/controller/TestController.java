package com.xiaobi.controller;

import com.xiaobi.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {
    @Autowired
    Student student;

    @Value("${pass}")
    String pass;

    @RequestMapping("test")
    public Object test(){
        System.out.println(student);
        System.out.println(pass);
        return "test";
    }

    @RequestMapping("test1")
    public Object test1(Date date){
        System.out.println(student);
        System.out.println(pass);
        return "test";
    }
}
