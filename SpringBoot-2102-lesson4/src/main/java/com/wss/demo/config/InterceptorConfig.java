package com.wss.demo.config;

import com.wss.demo.interceptor.WordInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

// 标识本类为配置类
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{

    @Autowired
    private WordInterceptor wordInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册敏感词汇拦截器，设置拦截路径
        registry.addInterceptor(wordInterceptor).addPathPatterns("/user/search");

        super.addInterceptors(registry);
    }
}