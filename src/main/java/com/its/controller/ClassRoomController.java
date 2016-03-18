package com.its.controller;

import com.its.db.pojo.ClassRoom;

import com.its.db.pojo.Teacher;
import com.its.service.IClassRoomService;
import com.its.service.impl.ClassRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/classRoom")
public class ClassRoomController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassRoomController.class);

	@Autowired
	private IClassRoomService classRoomService;

	@RequestMapping("/add")
	@ResponseBody
	public int add(ClassRoom classRoom ,HttpServletRequest request){

		//生成邀请码
		String uuid = UUID.randomUUID().toString();
		classRoom.setUuid(uuid);
		classRoom.setCreaterdate(new Date());
		classRoom.setStunum(0);
		//从session中获取老师信息
		Teacher teacher = (Teacher)request.getAttribute("user");
		classRoom.setTeacherid(teacher.getId());
		classRoom.setCreatername(teacher.getName());

		return classRoomService.save(classRoom);
	}


	/**
	 * 展示列表
	 * @param
	 * @return
	 */
	@RequestMapping("/getAll")
	@ResponseBody
	public List<ClassRoom> getAll(HttpServletRequest request){

		Teacher teacher = (Teacher)request.getAttribute("user");
		ClassRoom classRoom = new ClassRoom();
		classRoom.setTeacherid(teacher.getId());
		List<ClassRoom> list = classRoomService.select(classRoom);
		return list;

	}

}
