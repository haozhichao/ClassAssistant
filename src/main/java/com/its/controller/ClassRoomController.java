package com.its.controller;

import com.its.service.impl.ClassRoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classRoom")
public class ClassRoomController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassRoomController.class);

	@Autowired
	private ClassRoomService classRoomService;

}
