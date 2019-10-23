package com.wss.demo.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wss.demo.domain.ResultObject;
import org.json.JSONObject;

public class LoginFilter implements Filter {

    // 存储不需要过滤的URLs,一个对象
    private static String exclusions;
    // 存储不需要过滤的URLs,exclusions分解而来的字符串数组
    private static String[] ALLOWEDURLS;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        // 获取在filterconfig中配置好的，不需要过滤的URLs
        exclusions = filterConfig.getInitParameter("exclusions");
        if (!exclusions.isEmpty()) {
            // 获取不需要过滤的URLs，可以直接访问的URLs
            ALLOWEDURLS = exclusions.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        // 强转
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 获取当前请求的URL
        String url = request.getRequestURI();

        // 获取session，false表示如果没有获取到也不会创建
        HttpSession session = request.getSession(false);
        // 如果当前请求的URL不需要过滤或者已经登录的用户的请求，则继续处理
        if (isAllowed(url) || session != null && session.getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            // 若用户没有登录，否则直接返回
            render(response, "not login");
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    // 当前请求是否需要过滤
    public boolean isAllowed(String url) {
        for (String ALLOWEDURL: ALLOWEDURLS) {
            if (ALLOWEDURL.equals(url)) {
                return true;
            }
        }
        return false;
    }

    // 返回请求失败消息
    public void render(HttpServletResponse response, String msg) throws IOException {
        // 构造返回消息
        ResultObject resultObject = new ResultObject(-1, "fail", msg);

        // 生成json数据
        JSONObject object = new JSONObject(resultObject);

        // 返回json数据
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        out.write(object.toString().getBytes("UTF-8"));
        out.flush();
        out.close();
    }

}