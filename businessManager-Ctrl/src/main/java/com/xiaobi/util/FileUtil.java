package com.xiaobi.util;

import com.xiaobi.event.FileUploadEvent;
import com.xiaobi.listener.FileListener;
import com.xiaobi.listener.FileUploadListener;
import com.xiaobi.listener.ApplicationListenerManage;

import java.io.*;

//公司要开发文件操作帮助类
//定义文件读写方法，读取某个文件，写到某个类里面去
//但是需要记录文件读取进度条
//有时候调用文件读取需要进度条，有时候不需要
//简单理解就是：文件上传，有时候需要返回进度条，有时候不需要
public class FileUtil {

    public static int READ_SIZE= 100;

    public  static  void fileWrite(InputStream is, OutputStream os) throws Exception{
        fileWrite(is,os,null);
    }

    public static void fileWrite(InputStream is, OutputStream os, FileListener fileListener) throws Exception{
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos  = new BufferedOutputStream(os);
        //文件总大小
        int fileSize = is.available();
        //一共读取了多少
        int readSize = 0;
        byte[] b = new byte[READ_SIZE];
        boolean f = true;
        while (f){
            //文件实在小于第一次读的时候
            if (fileSize<READ_SIZE){
                byte[] bytes = new byte[fileSize];
                bis.read(bytes);
                bos.write(bytes);
                readSize = fileSize;
                f = false;
                //当你是最后一次读的时候
            }else if(fileSize<readSize+READ_SIZE){
                byte[] bytes = new byte[fileSize-readSize];
                readSize = fileSize;
                bis.read(bytes);
                bos.write(bytes);
                f = false;
            }else{
                bis.read(b);
                readSize +=READ_SIZE;
                bos.write(b);
            }

            if (fileListener!=null){
                fileListener.updateLoad(fileSize,readSize);
            }

            //执行
            ApplicationListenerManage.pushEvent(new FileUploadEvent(fileSize,readSize));
        }
        bis.close();
        bos.close();
    }


//    public static void main(String[] args) throws Exception {
//        ListenerManage.addListener(new FileUploadListener());
//        ListenerManage.addListener(new AListener());
//        ListenerManage.addListener(new BListener());
//        ListenerManage.addListener(new AAListener());
//
//        Scanner scanner = new Scanner(System.in);
//
//
//        while (true){
//            System.out.println("你要发布什么事件:");
//            String scan = scanner.next();
//            if (scan.equals("A")){
//                ListenerManage.pushEvent(new AEvent());
//            }else{
//                ListenerManage.pushEvent(new BEvent());
//            }
//        }
//
//
////        File file = new File("c://写我.txt");
////        if (!file.exists()) {
////            file.createNewFile();
////        }
////        fileWrite(new FileInputStream(new File("c://读我！！！！！.txt")), new FileOutputStream(file));
//    }

    public static void main(String[] args) throws Exception {
        //为事件管理器添加FileUploadListener这个事件监听，FileUploadListener这个监听器监听FileUploadEvent这个类发生的变化
        ApplicationListenerManage.addListener(new FileUploadListener());
        File file = new File("d://运维工作日报统计_肖毕_xie.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        fileWrite(new FileInputStream(new File("C:\\Users\\XiaoBi\\Desktop\\运维工作日报统计_肖毕.xlsx")), new FileOutputStream(file));
    }


}
