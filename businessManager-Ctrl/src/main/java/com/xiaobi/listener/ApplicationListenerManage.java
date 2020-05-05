package com.xiaobi.listener;


import com.xiaobi.event.ApplicationEvent;
import com.xiaobi.listener.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

//事件管理器
public class ApplicationListenerManage {



    //保存所有的监听器
    static   List<ApplicationListener<?>> list = new ArrayList<>();


    //添加监听器  //如果要做得优雅一点 可以考虑扫描项目
    //定义注解
    public static void   addListener(ApplicationListener listener){
        list.add(listener);
    }

    //判断一下有哪些人对这个事件感兴趣
    //只执行需要监听的类
    public static void pushEvent(ApplicationEvent event){
        for (ApplicationListener applicationListener : list) {
            //拿泛型
            Class tClass = (Class)((ParameterizedType)applicationListener.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
            //            //判断一下泛型
//            tClass.isAssignableFrom()
            if (tClass.equals(event.getClass())) {
                //这里其实是执行继承ApplicationListener子类的onEvent方法
                applicationListener.onEvent(event);
            }
        }
    }

}
