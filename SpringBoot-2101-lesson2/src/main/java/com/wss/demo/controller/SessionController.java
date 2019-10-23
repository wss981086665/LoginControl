package com.wss.demo.controller;

import com.wss.demo.domain.ResultObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 标注为返回json数据的controller，该类下的方法的URL前缀为/session
@RestController
@RequestMapping("session")
public class SessionController {

    // 功能为创建session，设置session属性，指定请求方法类型为get、URL为/session/set
    @GetMapping("set")
    public ResultObject setSession(HttpServletRequest request, HttpServletResponse response) {
        // 若session不存在则创建、若存在则获取session
        HttpSession session = request.getSession();
        // 获取sessionId
        String sessionId = session.getId();
        System.out.println(sessionId);

        // 设置session属性
        session.setAttribute("company", "shiyanlou");

        return new ResultObject(null);
    }

    // 功能为获取session属性，指定请求方法类型为post、URL为/file/upload
    @GetMapping("get")
    public ResultObject getSession(HttpServletRequest request, HttpServletResponse response) {
        // 若session不存在则创建、若存在则获取session
        HttpSession session = request.getSession();
        // 获取session属性
        String name = (String)session.getAttribute("company");
        // 返回给前端显示
        return new ResultObject(name);
    }
}