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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// 设置测试运行器，并且指定Springboot web启动类
@RunWith(SpringRunner.class)
@SpringBootTest(classes={DemoApplication.class})
@AutoConfigureMockMvc
public class UserControllerTest {

    // 自动注入mockMVC
    @Autowired
    private MockMvc mockMvc;

    // 测试方法
    @Test
    public void testAdd() throws Exception {

        // 构造json字符串
        String params = "{\"id\":1,\"name\":\"jacky\",\"gender\":1,\"age\":24}";

        // 构造请求对象，请求方法post，URL：/user/add，content-type：application/json
        RequestBuilder  builder = MockMvcRequestBuilders.post("/user/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(params.getBytes());

        // 执行请求，判断请求是否成功
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("msg").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.1.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.1.name").value("jacky"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.1.age").value("24"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.1.gender").value("1"))
                .andReturn();

        // 执行请求，并判断请求是否成功，打印完整响应
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);
        // 获取响应状态码
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    // 测试方法
    @Test
    public void testFindByRequestParam() throws Exception {

        // 构造请求，设置请求方法类型为get，url:/user/findByRequestParam，content-type：application/json
        // 参数 id = 1
        RequestBuilder  builder = MockMvcRequestBuilders.get("/user/findByRequestParam")
                .param("id", "1");

        // 执行请求，判断请求状态是否成功，获取响应
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);
        // 获取响应状态码
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    // 测试方法
    @Test
    public void testModify() throws Exception {

        // 构造json字符串
        String params = "{\"id\":1,\"name\":\"mary\",\"gender\":0,\"age\":23}";

        // 构造请求，指定请求方法为put，content-type：application/json，携带json字符串为参数
        RequestBuilder  builder = MockMvcRequestBuilders.put("/user/modify")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(params.getBytes());

        // 执行请求
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("0"))
                .andReturn();

//        mockMvc.perform(builder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());

        // 获取响应
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);

        // 获取响应状态码
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    // 测试方法
    @Test
    public void testFindByPathVariable() throws Exception {

        // 构造请求，指定请求方法为get，URL：/user/findByPathVariable/1
        RequestBuilder  builder = MockMvcRequestBuilders.get("/user/findByPathVariable/1");

        // 执行请求，判断请求状态，获取响应
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);

        // 获取响应状态码
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    // 测试方法
    @Test
    public void testDelete() throws Exception {

        // 构造请求，指定请求方法为get，URL：/user/findByPathVariable/1
        RequestBuilder  builder = MockMvcRequestBuilders.delete("/user/delete/1");

        // 执行请求，判断请求状态，获取响应
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);

        // 获取响应状态码
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    // 测试方法
    @Test
    public void testGetHeader() throws Exception {

        // 构造请求，指定请求方法为get，URL：/user/findByPathVariable/1，设置header
        RequestBuilder  builder = MockMvcRequestBuilders.get("/user/getHeader")
                .header("token", "abcdefg");

        // 执行请求，判断请求状态，获取响应
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);

        // 获取响应状态码
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }

    // 测试方法
    @Test
    public void testGetRequest() throws Exception {

        // 构造请求，指定请求方法为get，URL：/user/findByPathVariable/1，参数为id=123456
        RequestBuilder  builder = MockMvcRequestBuilders.get("/user/getRequest")
                .param("id", "123456");

        // 执行请求，判断请求状态，获取响应
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // 获取响应消息体
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);

        // 获取响应状态码
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
    }
}