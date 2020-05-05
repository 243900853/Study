package com.xiaobi.event;

import com.xiaobi.event.ApplicationEvent;

public class FileUploadEvent extends ApplicationEvent {


    private int fileSize;

    private int readSize;


    public FileUploadEvent(int fileSize, int readSize) {
        this.fileSize = fileSize;
        this.readSize = readSize;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getReadSize() {
        return readSize;
    }

    public void setReadSize(int readSize) {
        this.readSize = readSize;
    }
}
