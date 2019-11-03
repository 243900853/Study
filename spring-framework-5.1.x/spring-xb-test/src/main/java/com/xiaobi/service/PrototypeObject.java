package com.xiaobi.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//单例对象
@Component
@Scope("prototype")
public class PrototypeObject {
}
