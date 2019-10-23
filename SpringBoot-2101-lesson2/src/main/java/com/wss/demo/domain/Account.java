package com.wss.demo.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account{

    // 账户id，只有在非null的情况下才序列化
    @JsonInclude(Include.NON_NULL)
    private int id;
    // 账户名称，重命名为name
    @JsonProperty("name")
    private String username;
    // 账户密码，关注一下getPassword、setPassword上的注解
    private String password;
    // 账户创建创建时间，格式化时间
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", locale="zh", timezone="GMT+8")
    private Date date;

    // 构造函数
    public Account() {
        super();
        // TODO Auto-generated constructor stub
    }
    // 构造函数
    public Account(String username, String password, Date date) {
        super();
        this.username = username;
        this.password = password;
        this.date = date;
    }

    // 属性的setter getter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 向前端返回响应数据的时候，忽略password，该属性不参加序列化，防止泄露密码
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    // 从前端接受参数的时候，可以正常反序列化
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + ", date=" + date + "]";
    }

}