package com.xiaobi.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@Component
//ConditionalOnClass 判断当前项目里面有没有某个Class 如果有 就注入当前Bean
@ConditionalOnBean(B.class)
public class A {
}
