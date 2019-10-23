package com.wss.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wss.demo.domain.ResultObject;
import com.wss.demo.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    // 统计在线用户数，请求方法类型get，URL：/user/online
    @GetMapping("online")
    public ResultObject setSession(HttpServletRequest request,
                                   HttpServletResponse response) {
        // 获取session
        HttpSession session = request.getSession();
        // 获取sessionid，即JSESSIONID
        String sessionId = session.getId();
        System.out.println(sessionId);

        // 获取session属性
        Object onlineNumber = session.getServletContext().getAttribute("onlineNumber");
        System.out.println(onlineNumber);
        // 返回结果
        return new ResultObject(onlineNumber);
    }

    // 登录验证用户，请求方法类型post，URL：/user/login
    @PostMapping("login")
    public ResultObject login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        // 正确账号密码，模拟数据库中的信息
        User realUser = new User("jacky", "112233");
        // 判断账号、密码是否正确
        if (realUser.equals(user)) {
            // 获取session，获取不到则新建
            HttpSession session = request.getSession();

            // 获取sessionid，即JSESSIONID
            String sessionId = session.getId();
            System.out.println(sessionId);

            // 设置session属性user
            session.setAttribute("user", user);

            return new ResultObject("login success");
        } else {
            return new ResultObject(-1, "fail", "login fail");
        }
    }

    // 搜索功能，过滤敏感词汇，请求方法类型get，URL：/user/search
    @GetMapping("search")
    public ResultObject search(@RequestParam String word) {
        // 若关键字是敏感词，在拦截器中已经被拦截不可能走到这里
        return new ResultObject("search word " + "'" + word + "'" + " is valid.");
    }
}