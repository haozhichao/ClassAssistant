package com.its.controller;

import com.its.service.ICommentsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentsController.class);

	@Autowired
	private ICommentsService commentsService;

}
