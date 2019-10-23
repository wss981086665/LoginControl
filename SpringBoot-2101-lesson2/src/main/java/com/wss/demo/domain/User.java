package com.wss.demo.domain;

public class User{

    // 用户id
    private int id;
    // 用户姓名
    private String name;
    // 用户性别
    private int gender;
    // 用户年龄
    private int age;
    // 构造函数
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    // 构造函数
    public User(String name, int gender, int age) {
        super();
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    // 属性的setter getter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + "]";
    }
}