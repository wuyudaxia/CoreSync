package com.demo.testo.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;


public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // 检查Session中是否存在用户名，如果不存在，重定向到登录页面
        if (username == null) {
            response.sendRedirect("/"); // 重定向到登录页面
            return false; // 阻止请求继续处理
        }

        return true; // 用户已登录，继续处理请求
    }
}