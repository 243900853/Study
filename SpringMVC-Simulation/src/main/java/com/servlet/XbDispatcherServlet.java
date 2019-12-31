package com.servlet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;

public class XbDispatcherServlet extends HttpServlet{
    //XML名称
    private static String XML_PATH_LOCAL = "xmlPathLocal";
    //项目根路径
    private static String PROJECT_PATH = XbDispatcherServlet.class.getResource("/").getPath();
    //扫描包路径
    private static String COMPONENT_SCAN_ELEM_NAME = "componentScan";
    private static String COMPONENT_SCAN_ELEM_PACKAGE_NAME = "package";

    @Override
    public void init(ServletConfig config) throws ServletException {
        //解析XML:解析web.xml
        //根据<param-name>xmlPathLocal</param-name>找到<param-value>springMVC.xml</param-value>
        String initParameter = config.getInitParameter(XML_PATH_LOCAL);
        //PROJECT_PATH需要进行URL转义 空格会变成%20
        PROJECT_PATH = PROJECT_PATH.replaceAll("%20"," ");
        //使用dom4j解析程序员提供的springMVC.xml
        File file = new File(PROJECT_PATH + "//" + initParameter);
        Document parse = parse(file);
        //找到componentScan元素
        Element componentScanElem = parse.getRootElement().element(COMPONENT_SCAN_ELEM_NAME);
        //找到componentScan元素下package节点值
        String stringValue = componentScanElem.attribute(COMPONENT_SCAN_ELEM_PACKAGE_NAME).getValue();
        //扫描项目
    }

    public void scanProjectByPath(String path){
        File file = new File(path);
    }

    public void scanFile(File file){
        //递归解析，判断是不是文件夹
        if(file.isDirectory()){
            for (File listFile : file.listFiles()) {
                scanFile(listFile);
            }
        }else{
            //如果不是文件夹
        }
    }

    public Document parse(File file){
        SAXReader reader = new SAXReader();
        try {
            return reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
