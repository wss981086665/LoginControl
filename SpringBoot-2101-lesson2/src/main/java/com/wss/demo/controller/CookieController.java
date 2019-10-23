package com.wss.demo.controller;

import com.wss.demo.domain.ResultObject;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 标注为返回json数据的controller，该类下的方法的URL前缀为cookie
@RestController
@RequestMapping("cookie")
public class CookieController {

    // 功能为设置cookie，返回给前端，指定请求方法类型为get、URL为/cookie/set
    @GetMapping("set")
    public ResultObject setCookie(HttpServletRequest request, HttpServletResponse response) {
        // 获取当前时间
        String time = String.valueOf(System.currentTimeMillis());
        // 创建cookie，用来记录用户最近一次访问时间
        Cookie cookie = new Cookie("last", time);
        // 设置cookie过期时间，单位秒，当前例子为一个星期
        cookie.setMaxAge(60 * 60 * 24 * 7);
        // 将cookie添加到响应中
        response.addCookie(cookie);
        return new ResultObject(null);
    }

    // 功能为从前端请求中获取cookie，指定请求方法类型为get、URL为/cookie/get
    @GetMapping("get")
    public ResultObject getCookie(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求携带的所有cookie
        Cookie[] cookies = request.getCookies();
        // 如果一个cookie都没有，直接返回失败
        if (cookies != null) {
            // 通过循环比较获取指定cookie
            for(Cookie cookie: cookies) {
                if ("last".equals(cookie.getName())) {
                    // 获取指定cookie的值
                    System.out.println(cookie.getValue());
                    return new ResultObject(null);
                }
            }
        }
        return new ResultObject(-1, "get cookie fail");
    }

    // 功能为通过注解从前端请求中获取指定名称的cookie，指定请求方法类型为get、URL为/cookie/getByAnnotation
    @GetMapping("getByAnnotation")
    public ResultObject getCookieByAnnotation(@CookieValue("last") String last) {
        System.out.println(last);
        return new ResultObject(null);
    }
}