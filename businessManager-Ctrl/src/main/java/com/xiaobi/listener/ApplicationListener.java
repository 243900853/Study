package com.xiaobi.listener;


import com.xiaobi.event.ApplicationEvent;

public interface ApplicationListener< E extends ApplicationEvent> {
    void onEvent(E e);
}
