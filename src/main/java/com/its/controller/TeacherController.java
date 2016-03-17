package com.its.controller;

import com.its.db.pojo.ClassRoom;
import com.its.db.pojo.Teacher;
import com.its.service.impl.TeacherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

	@Autowired
	private TeacherService teacherService;

	@RequestMapping("/add")
	@ResponseBody
	public int add(Teacher teacher){
		//设置默认的用户名相同
		teacher.setName(teacher.getUsername());
		return teacherService.save(teacher);
	}

}
