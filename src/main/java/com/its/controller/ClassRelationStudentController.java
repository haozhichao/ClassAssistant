package com.its.controller;

import com.its.service.impl.ClassRelationStudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classRelationStudent")
public class ClassRelationStudentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassRelationStudentController.class);



	@Autowired
	private ClassRelationStudentService classRelationStudentService;

}
