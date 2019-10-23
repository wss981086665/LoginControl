package com.wss.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wss.demo.domain.ResultObject;
import com.wss.demo.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 标注为返回json数据的controller，该类下的方法的URL前缀为/user
@RestController
@RequestMapping("user")
public class UserController {

    // 保存用户信息，模拟数据库的作用
    private static Map<Integer, User> userMap = new HashMap<>();

    // 创建用户，指定请求方法类型为post、URL为/user/add，从请求消息体中获取user，通过Jackson反序列化为user对象
    @PostMapping("add")
    public ResultObject add(@RequestBody User user) {
        userMap.put(user.getId(), user);
        return new ResultObject(userMap);
    }

    // 删除指定id用户，指定请求方法类型为delete、URL为/user/delete/{id}，从url路径中获取id参数
    @DeleteMapping("delete/{id}")
    public ResultObject delete(@PathVariable int id) {
        userMap.remove(id);
        return new ResultObject(userMap);
    }

    // 修改用户，指定请求方法类型为put、URL为/user/modify，通过Jackson反序列化为user对象
    @PutMapping("modify")
    public ResultObject modify(@RequestBody User user) {
        userMap.put(user.getId(), user);
        return new ResultObject(userMap);
    }

    // 获取指定id用户，指定请求方法类型为get、URL为/user/findByPathVariable/{id}，从url路径中获取id参数
    @GetMapping("findByPathVariable/{id}")
    public ResultObject findByPathVariable(@PathVariable int id) {
        User user = userMap.get(id);
        return new ResultObject(user);
    }

    // 获取指定id用户，指定请求方法类型为get、URL为/user/findByRequestParam，从url路径中获取id参数
    @GetMapping("findByRequestParam")
    public ResultObject findByRequestParam(@RequestParam int id) {
        User user = userMap.get(id);
        return new ResultObject(user);
    }

    // 获取请求中的header信息，指定请求方法类型为get、URL为/user/getHeader
    @GetMapping("getHeader")
    public ResultObject getHeader(@RequestHeader String token) {
        return new ResultObject(token);
    }

    // 获取请求中的参数，指定请求方法类型为get、URL为/user/getRequest
    @GetMapping("getRequest")
    public ResultObject getRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        return new ResultObject(id);
    }
}