package com.its.controller;

import com.its.db.pojo.Teacher;
import com.its.service.ITeacherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

	@Autowired
	private ITeacherService teacherService;

	/**
	 * 添加老师
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public int add(Teacher teacher){

		if (this.teacherIsExist(teacher)){
			return -1;
		}
		//设置默认的用户名相同
		teacher.setName(teacher.getUsername());
		teacher.setDel(false);
		return teacherService.save(teacher);
	}

	/**
	 * 登录验证
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public boolean login(Teacher teacher,HttpServletRequest request){

		List<Teacher> list = teacherService.select(teacher);
		if (list.size() > 0){
			request.getSession().setAttribute("role",1);
			request.getSession().setAttribute("user",list.get(0));
			return true;
		}
		return false;

	}

	/**
	 * 注销
	 *
	 * @return ModelAndView 登录页
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.setAttribute("user","");
		LOGGER.debug("退出成功。");
		return new ModelAndView("redirect:/login/login.html");
	}

	/**
	 * 更新老师信息
	 * @param teacher
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Integer update(Teacher teacher){

		return teacherService.updateNotNull(teacher);
	}

	/**
	 * 老师用户是否存在
	 * @param teacher
	 * @return
	 */
	private boolean teacherIsExist(Teacher teacher){

		Teacher teacherTest = new Teacher();
		teacherTest.setUsername(teacher.getUsername());
		if (teacherService.select(teacherTest).size()>0){
			return true;
		}
		return false;

	}
}
