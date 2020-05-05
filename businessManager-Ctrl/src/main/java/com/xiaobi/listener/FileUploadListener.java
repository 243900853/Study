package com.xiaobi.listener;

import com.xiaobi.event.FileUploadEvent;

//自定义事件监听
public class FileUploadListener implements ApplicationListener<FileUploadEvent> {
    @Override
    public void onEvent(FileUploadEvent event) {
        double i1 = event.getFileSize();
        double d = event.getReadSize()/i1;
        System.out.println("当前文件上传进度百分比:"+d*100+"%");
    }
}
