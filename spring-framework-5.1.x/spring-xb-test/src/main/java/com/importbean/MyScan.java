package com.importbean;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)//让Spring识别注解
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface MyScan {
}
