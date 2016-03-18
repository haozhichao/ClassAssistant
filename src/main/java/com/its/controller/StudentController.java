package com.its.controller;

import com.its.service.IStudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private IStudentService studentService;

}
