package com.its.controller;

import com.its.db.pojo.Notice;
import com.its.db.pojo.Teacher;
import com.its.service.INoticeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private INoticeService noticeService;

	/**
	 * 查询Notice列表
	 * @param request
	 * @return Notice集合
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<Notice> list(HttpServletRequest request){
		int classRoomId = (Integer)request.getSession().getAttribute("id");
		Notice notice = new Notice();
		notice.setClassroomid(classRoomId);
		notice.setDel(false); // 未删除
		List<Notice> noticeList = noticeService.select(notice);
		LOGGER.info("查询noticeList集合成功");
		return noticeList;
	}

	/**
	 * 添加notice
	 * @param notice
	 * @return 0-失败;1-成功
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Integer add(HttpServletRequest request, Notice notice){
		if(notice == null) {
			LOGGER.error("添加notice失败", "前台过来的notice为null");
			return 0;
		}
		// 获取当前用户信息、课堂信息
		int classRoomId = (Integer)request.getSession().getAttribute("id");
		Teacher teacher = (Teacher)request.getSession().getAttribute("user");
		String teacherName = teacher.getName();
		notice.setClassroomid(classRoomId);
		notice.setTeachername(teacherName);
		notice.setReleasedate(new Date()); //发布时间
		notice.setDel(false);
		noticeService.save(notice);
		LOGGER.info("添加notice成功");
		return 1;
	}

	/**
	 * 删除notice
	 * @param noticeId
	 * @return 0-失败;1-成功
	 */
	@RequestMapping("/remove")
	@ResponseBody
	public Integer remove(final Integer noticeId) {
		Notice notice = new Notice();
		notice.setId(noticeId);
		notice.setDel(true);
		noticeService.updateNotNull(notice);
		LOGGER.info("删除notice成功");
		return 1;
	}
}
