
package com.its.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/3/15.
 */

public class MyInterceptors implements HandlerInterceptor {
    //拦截器必须实现的3个方法
    @Override public boolean preHandle(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, Object o)
                                    throws Exception {
        System.out.println("初始拦截器");
        Object object =  httpServletRequest.getSession().getAttribute("user");
        /*if (object == null){
            httpServletResponse.sendRedirect("/login/login.html");
            return false;
        }*/
        return true;
    }

    @Override public void postHandle(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, Object o,
                                    ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle的执行");
        //

    }

    @Override public void afterCompletion(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, Object o, Exception e)
                                    throws Exception {

        System.out.println("afterCompletion方法的执行");
    }
}
