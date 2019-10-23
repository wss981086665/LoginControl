package com.wss.demo.test;

import javax.servlet.http.Cookie;

import com.wss.demo.DemoApplication;
import org.junit.FixMethodOrder;
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

// 设置测试运行器，并且指定Spring Boot Web启动类，此外指定测试方法执行顺序为：方法名正序
@RunWith(SpringRunner.class)
@SpringBootTest(classes={DemoApplication.class})
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CookieControllerTest {

    // 注入mockMVC
    @Autowired
    private MockMvc mockMvc;

    // 存储获取的cookie
    private static Cookie cookie;

    // 测试方法
    @Test
    public void test1SetCookie() throws Exception {

        // 构造请求，指定get方法，URL为：/cookie/set
        RequestBuilder  builder = MockMvcRequestBuilders.get("/cookie/set");

        // 执行已经构造好的请求，并且测试返回状态码是否为Ok(即200),last这个cookie是否存在
        // last最大生命期是否为7天
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.cookie().exists("last"))
                .andExpect(MockMvcResultMatchers.cookie().maxAge("last", 60 * 60 * 24 * 7))
                .andReturn();

        // 获取last这个cookie
        cookie = mvcResult.getResponse().getCookie("last");
        // 打印last这个cookie的值
        System.out.println(cookie.getValue());
    }

    // 测试方法
    @Test
    public void testGetCookie() throws Exception {
        // 构造请求，指定get方法，URL为：/cookie/get，并且携带后端返回的cookie
        RequestBuilder  builder = MockMvcRequestBuilders.get("/cookie/get")
                .cookie(cookie);

        // 执行请求，并且获取响应
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);
    }

    // 测试方法
    @Test
    public void testGetCookieByAnnotation() throws Exception {
        // 构造请求，指定get方法，URL为：/cookie/getByAnnotation，并且携带后端返回的cookie
        RequestBuilder  builder = MockMvcRequestBuilders.get("/cookie/getByAnnotation")
                .cookie(cookie);

        // 执行请求，并且获取响应
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);
    }
}