package com.its.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 首页
 * 
 * @author
 *
 */
@RestController
@RequestMapping()
public class IndexController {

    /**
     * 直接输入IP，默认跳转页面
     * 
     * @return ModelAndView 跳转到登录页面
     */
    @RequestMapping()
    public ModelAndView defaultPage() {
        return new ModelAndView("redirect:login/login.html?nowDate="+new Date());
    }

    /**
     * 登录首页
     * 
     * @return ModelAndView 首页
     */
    @RequestMapping("/login")
    public ModelAndView index() {
        return new ModelAndView("redirect:login/login.html?nowDate="+new Date());
    }
}
