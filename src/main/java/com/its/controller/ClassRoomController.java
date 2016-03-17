package com.its.controller;

import com.its.db.pojo.ClassRoom;
import com.its.service.impl.ClassRoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/classRoom")
public class ClassRoomController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassRoomController.class);

	@Autowired
	private ClassRoomService classRoomService;

	@RequestMapping("/add")
	@ResponseBody
	public int add(ClassRoom classRoom ){


		//生成邀请码
		String uuid = UUID.randomUUID().toString();
		classRoom.setUuid(uuid);
		classRoom.setCreaterdate(new Date());
		classRoom.setStunum(0);
		int i = classRoomService.save(classRoom);
		return i;
	}


	/**
	 * 展示列表
	 * @param teacherid 老师的id
	 * @return
	 */
	@RequestMapping("/getAll")
	@ResponseBody
	public List<ClassRoom> getAll(Integer teacherid){

		Example example = new Example(ClassRoom.class) ;
		example.createCriteria().andGreaterThan("id",0);
		List<ClassRoom> list = classRoomService.selectByExample(example);
		return list;

	}

}
