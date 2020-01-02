package com.controller;

import com.annotation.ControllerX;
import com.annotation.RequestMappingX;
import com.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerX
@RequestMappingX("/user")
public class UserController {

    @RequestMappingX("/getUser.do")
    public String getUser(String userNanme, HttpServletRequest request, HttpServletResponse response, UserEntity userEntity){
        System.out.println(userNanme);
        System.out.println(request);
        System.out.println(response);
        System.out.println(userEntity);
        return "";
    }
}
