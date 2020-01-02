package com.servlet;

import com.annotation.ControllerX;
import com.annotation.RequestMappingX;
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
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class XbDispatcherServlet extends HttpServlet{
    //XML名称
    private static String XML_PATH_LOCAL = "xmlPathLocal";
    //项目根路径  D:\生活和学习\学习资料\腾讯课堂\SpringMVC-Simulation\target\classes\
    private static String PROJECT_PATH = XbDispatcherServlet.class.getResource("/").getPath();
    //扫描包的路径
    private static String COMPONENT_SCAN_ELEM_NAME = "componentScan";
    private static String COMPONENT_SCAN_ELEM_PACKAGE_NAME = "package";
    //Map集合：存放Url对应的方法
    private static Map<String,Method> map = new HashMap<>();

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
        //找到componentScan元素下package值
        String value = componentScanElem.attribute(COMPONENT_SCAN_ELEM_PACKAGE_NAME).getValue();
        //扫描项目
        scanProjectByPath(PROJECT_PATH+File.separator+value);
    }

    public void scanProjectByPath(String path){
        File file = new File(path);
        //递归解析项目所有Class文件
        scanFile(file);
    }

    public void scanFile(File file){
        //递归解析，判断是不是文件夹
        if(file.isDirectory()){
            for (File listFile : file.listFiles()) {
                scanFile(listFile);
            }
        }else{//如果不是文件夹，则通过Class.forName动态加载Class文件
            //获取class文件输出路径
            String filePath = file.getPath();
            //只处理class文件
            if(filePath.endsWith(".class")){
                /**
                 * 将D:\生活和学习\学习资料\腾讯课堂\SpringMVC-Simulation\target\classes\com\controller\UserController.class
                 * 替换成com.controller.UserController
                 */
                String projectPath = new File(PROJECT_PATH).getPath();
                String classPath = filePath.replace(projectPath,"")
                        .replace(File.separator,".")
                        .substring(1)
                        .replace(".class","");
                try {
                    //动态加载Class文件
                    Class<?> clazz = Class.forName(classPath);
                    //判断有没有加Controller注解
                    if (clazz.isAnnotationPresent(ControllerX.class)) {
                        //判断RequestMapping注解有没有value值
                        RequestMappingX clazzRequestMappingX = clazz.getAnnotation(RequestMappingX.class);
                        String clazzRequestMappingUrl = "";
                        //获取类的请求路径
                        if(clazzRequestMappingX != null){
                            clazzRequestMappingUrl = clazzRequestMappingX.value();
                        }
                        //循环判断加了RequestMapping注解的方法
                        for (Method method : clazz.getDeclaredMethods()) {
                            //不是合成类
                            if (!method.isSynthetic()) {
                                //获取到方法的注解信息
                                RequestMappingX methodRequestMappingX = method.getAnnotation(RequestMappingX.class);
                                if(methodRequestMappingX != null){
                                    String methodRequestMappingUrl = "";
                                    //获取到方法的请求路径
                                    methodRequestMappingUrl = methodRequestMappingX.value();
                                    System.out.println("类:"+clazz.getName()+"的"+method.getName()+"方法被映射到了"+clazzRequestMappingUrl+methodRequestMappingUrl+"上面");
                                    //添加到url对应的方法集合中
                                    map.put(clazzRequestMappingUrl+methodRequestMappingUrl,method);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
        String requestURI = req.getRequestURI();
        Method method = map.get(requestURI);
        if (method != null) {
            //java8以前  直接那参数名称 拿不到
            //需要修改3个地方
            //1、File--Settings--Java Compiler--additional command line parameters增加-parameters
            //2、File--Settings--Java Compiler--1.7改为1.8
            //3、File--Project Structure--Language level改为8
            Parameter[] parameters = method.getParameters();
            Object[] objects = new Object[parameters.length];
            //为参数赋值
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                String name = parameter.getName();
                Class type = parameter.getType();
                if(type.equals(String.class)){
                    objects[i] = req.getParameter(name);
                }else if (type.equals(HttpServletRequest.class)){
                    objects[i] = req;
                }else if (type.equals(HttpServletResponse.class)){
                    objects[i] = resp;
                }else {
                    
                }
            }
        }else {
            resp.setStatus(404);
        }
        super.doPost(req, resp);
    }

}
