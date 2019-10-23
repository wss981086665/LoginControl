package com.wss.demo.config;

import com.wss.demo.listener.OnlineListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 标识本类为配置类
@Configuration
public class ListenerConfig {

    @Bean
    public OnlineListener init() {
        return new OnlineListener();
    }
}