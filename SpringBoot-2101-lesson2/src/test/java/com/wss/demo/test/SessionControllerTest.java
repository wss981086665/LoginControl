package com.wss.demo.test;

import javax.servlet.http.Cookie;

import com.wss.demo.DemoApplication;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.junit.runners.MethodSorters;

// 设置测试运行器，并且指定Springboot web启动类，此外指定测试方法执行顺序为：方法名正序
@RunWith(SpringRunner.class)
@SpringBootTest(classes={DemoApplication.class})
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionControllerTest {

    // 自动注入mockMVC
    @Autowired
    private MockMvc mockMvc;

    // 用于保存从后端获取的cookie
    private static Cookie cookie;

    // 测试方法
    @Test
    public void test1SetSession() throws Exception {

        // 构造请求对象，指定get方法，URL为：/session/set
        RequestBuilder  builder = MockMvcRequestBuilders.get("/session/set");

        // 执行请求，判断是否请求成功，即返回码为200，并且获取响应
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

//        cookie = mvcResult.getResponse().getCookie("JSESSIONID");
//        System.out.println(cookie.getValue());

        // 获取响应中的所有cookie
        Cookie[] cookies = mvcResult.getResponse().getCookies();

        // 遍历每一个cookie
        for (Cookie cookie: cookies) {
            System.out.println(cookie.getName());
        }

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);

//        mockMvc.perform(builder)
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andDo(MockMvcResultHandlers.print());
    }

    // 忽略测试方法
    @Ignore
    public void test2GetSession() throws Exception {

        // 构造请求对象，指定get方法，URL为：/session/get，并且携带后端返回的cookie
        RequestBuilder  builder = MockMvcRequestBuilders.get("/session/get")
                .cookie(cookie);

        // 执行请求，判断请求是否成功，获取响应
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);
    }
}