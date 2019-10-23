package com.wss.demo.domain;

public class User{

    // 用户名称
    private String username;
    // 用户密码
    private String password;

    // 构造函数
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    // 构造函数
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    // 属性的setter、getter方法
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "Book [username=" + username + ", password=" + password + "]";
    }

    // 重写hashCode方法
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    // 重写equals方法，便于直接比较user对象
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
}