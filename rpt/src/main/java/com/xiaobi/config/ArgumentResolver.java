package com.xiaobi.config;

import com.xiaobi.annotation.StaffParam;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;

@Component
public class ArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    //判断类的参数是否需要当前适配器来处理
    public boolean supportsParameter(MethodParameter methodParameter) {
        //判断当前类参数是否加了@StaffParam注解
        return methodParameter.hasParameterAnnotation(StaffParam.class);
    }

    @Override
    //给这个参数赋值
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //这里可以做当前用户的判断 比如：赋值 判断用户是否失效 判断用户是否登录 都可以在这里做
        System.out.println("用户判断逻辑");
        Map map = new HashMap();
        map.put("name","登陆者");
        return map;
    }
}
