package com.wss.demo.controller;

import com.wss.demo.domain.Account;
import com.wss.demo.domain.ResultObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 标注为返回json数据的controller，该类下的方法的URL前缀为account
@RestController
@RequestMapping("account")
public class AccountController {

    // 指定请求方法类型为post、URL为/account/add，从请求消息体中获取account对象，通过Jackson完成反序列化
    @PostMapping("add")
    public ResultObject add(@RequestBody Account account) {
        System.out.println(account);
        // 将前端发送的account参数直接返回，观察id是否存在、username的名称、password是否存在、date的格式
        return new ResultObject(account);
    }
}