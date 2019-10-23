package com.wss.demo.test;

import com.wss.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// 设置测试运行器，并且指定Springboot web启动类
@RunWith(SpringRunner.class)
@SpringBootTest(classes={DemoApplication.class})
@AutoConfigureMockMvc
public class AccountControllerTest {

    // 注入mockMVC
    @Autowired
    private MockMvc mockMvc;

    // 测试方法
    @Test
    public void testAdd() throws Exception {
        // json字符串
        String params = "{\"id\":1,\"name\":\"jacky\",\"password\":123,\"date\":\"2018-01-01 11:11:11\"}";

        // 构建请求 指定post方法、URL：/account/add、content-type：json、传入json字符串
        RequestBuilder  builder = MockMvcRequestBuilders.post("/account/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(params.getBytes());

        // 执行请求，设置返回期望值，若返回值与期望值不同，测试不通过
        // 断言 code 为0
        // 断言 msg 为success
        // 断言 result对象中id为1
        // 断言 result对象中name为jacky
        // 断言 result对象中date为2018-01-01 11:11:11
        // 断言 result对象中password不存在
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("msg").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.name").value("jacky"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.date").value("2018-01-01 11:11:11"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.password").doesNotExist())
                .andReturn();

        // 测试整个过程：
        // 1、MockMvcRequestBuilders.post("/account/add")构造请求
        // 2、mockMvc.perform执行请求
        // 3、andExpect添加执行完成后需要执行的断言，验证控制器执行完成后结果是否正确
        // 4、andDo添加结果处理器，比如调试时打印结果到控制台
        // 5、andReturn表示执行完成后返回相应的结果

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);
        // 获取状态码
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }
}