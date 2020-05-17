package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//这一步是供mybatis扫描mapper
@MapperScan("com.xiaobi.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
