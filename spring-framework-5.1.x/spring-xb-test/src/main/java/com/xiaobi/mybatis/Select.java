package com.xiaobi.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//FIELD：注解到属性上面 TYPE：注解到类上面 METHOD：注解在方法上
@Retention(RetentionPolicy.RUNTIME)//让Spring识别注解
public @interface Select {
	String[] value();
}
