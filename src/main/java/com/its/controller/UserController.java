package com.its.controller;

import com.its.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/login")
	public String getUser(){
		return userService.selectByKey(Integer.parseInt("3")).getName();
	}

	private void test() {

	}

}
