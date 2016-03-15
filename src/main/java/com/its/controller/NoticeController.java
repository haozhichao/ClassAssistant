package com.its.controller;

import com.its.service.impl.NoticeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;

}
