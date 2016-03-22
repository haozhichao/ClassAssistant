package com.its.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/22.
 */
public class AuthFilter  extends HttpServlet implements Filter {
    @Override public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                                    FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //System.out.println(request.getServletPath()+"》》》》》》》》》》》》》》》》》");

        if (!"/login/login.html".equals(request.getServletPath())){
            HttpSession session = request.getSession(false);
            //session 过期了，或者没有登录,跳转到登录页面重新登录
            if (session == null || session.getAttribute("user") == null){
                response.sendRedirect("/login/login.html");
                return;
            }
        }
        chain.doFilter(request,response);
    }
}
