package com.its.controller;

import com.its.service.IStudyResourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/studyResource")
public class StudyResourceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudyResourceController.class);

	@Autowired
	private IStudyResourceService studyResourceService;

}
