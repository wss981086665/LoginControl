package com.wss.demo.config;

import com.wss.demo.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 标识本类为配置类
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> registFilter() {
        // 创建注册器
        FilterRegistrationBean<LoginFilter> registration = new FilterRegistrationBean<LoginFilter>();
        // 设置过滤器
        registration.setFilter(new LoginFilter());

        // 设置属性exclusions，不需要过滤的URL
        registration.addInitParameter("exclusions", "/user/login");

        // 添加过滤规则
        registration.addUrlPatterns("/*");
        // 过滤器设置名称
        registration.setName("LoginFilter");
        // 设置过滤器顺序，值越小优先级越高
        registration.setOrder(1);

        return registration;
    }
}