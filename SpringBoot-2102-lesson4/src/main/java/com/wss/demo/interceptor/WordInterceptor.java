package com.wss.demo.interceptor;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wss.demo.domain.ResultObject;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class WordInterceptor implements HandlerInterceptor{

    // 设置敏感词汇
    private static final String[] forbitWords = {"apple", "pear", "peach", "Lemon "};

    // 处理前方法
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 获取请求中的参数word
        String word = request.getParameter("word");
        // 与敏感词汇对比
        for (String forbitWord: forbitWords) {
            if (word.equals(forbitWord)) {
                render(response, "search word " + "'" + word + "'" + " is not allowed.");
                // 返回假，中断请求
                return false;
            }
        }

        // 返回真，继续处理请求
        return true;
    }

    // 处理后方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    // 完成后方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    // 返回失败消息
    public void render(HttpServletResponse response, String msg) throws IOException {
        ResultObject resultObject = new ResultObject(-1, msg, null);

        JSONObject object = new JSONObject(resultObject);

        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        out.write(object.toString().getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}