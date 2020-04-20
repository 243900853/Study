package com.controller;

import com.annotation.ControllerX;
import com.annotation.RequestMappingX;
import com.annotation.ResponseBodyX;
import com.annotation.StaffParam;
import com.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice 所有的参数赋值或者全局异常统一处理
//ControllerX、RequestMappingX：手写模拟Spring MVC
@ControllerX
@RequestMappingX("/user")
//Controller、RequestMapping：0 XML配置Spring MVC
//Controller注册方式二：@Controller
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMappingX("/getUser.do")
    @ResponseBodyX
    public Object getUser(String userName, HttpServletRequest request, HttpServletResponse response, UserEntity userEntity){
        System.out.println(userName);
        System.out.println(request);
        System.out.println(response);
        System.out.println(userEntity);
        return "test";
    }

    @RequestMapping("/getUser1.do")
    @ResponseBody
    public Object getUser1(HttpServletRequest request, HttpServletResponse response,@StaffParam Map map){
        System.out.println(map);
        return "test";
    }

    @RequestMappingX("/getUsers.do")
    @RequestMapping("/getUsers.do")
    public Object getUsers(){
        return "index";
    }

    @RequestMapping("/getUserMap.do")
    @ResponseBody
    public Object getUserMap(){
        Map hashMap = new HashMap();
        hashMap.put("name","肖毕");
        return hashMap;
    }
}
