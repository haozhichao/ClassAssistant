package com.its.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/15.
 */
public class Myfilter extends HttpServlet implements Filter {

    @Override public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override public void doFilter(ServletRequest request, ServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        //System.out.println("登录过滤");
        /*RequestDispatcher dispatcher = request.getRequestDispatcher("KK_BlacklistVehicle_UserLogin.jsp");//这里设置如果没有登陆将要转发到的页面
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);

          System.out.println(((HttpServletRequest) request).getRequestURI());
        // 从session里取的用户名信息
        String username = (String) session.getAttribute("sessionKK_BlacklistVehicle_UserLogin_ID");//这里获取session，为了检查session里有没有保存用户信息，没有的话回转发到登陆页面

        // 判断如果没有取到用户信息,就跳转到登陆页面
        if (username == null || "".equals(username))
        {
            // 跳转到登陆页面
            dispatcher.forward(request,response);
        //   System.out.println("用户没有登陆，不允许操作");

            res.setHeader("Cache-Control","no-store");
            res.setDateHeader("Expires",0);
            res.setHeader("Pragma","no-cache");
        }
        else
        {
            // 已经登陆,继续此次请求
            chain.doFilter(request,response);
        //   System.out.println("用户已经登陆，允许操作");
        }*/
        chain.doFilter(request,response);
    }
}
