package com.its.controller;

import com.its.db.pojo.Student;
import com.its.service.IStudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private IStudentService studentService;

	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public int add(Student student){

		if (this.studentIsExist(student)){
			return -1;
		}
		//设置默认的用户名相同
		student.setName(student.getUsername());
		return studentService.save(student);
	}

	/**
	 * 学生用户是否存在
	 * @param student
	 * @return
	 */
	private boolean studentIsExist(Student student){

		Student stu = new Student();
		stu.setUsername(student.getUsername());
		if (studentService.select(stu).size()>0){
			return true;
		}
		return false;

	}
	/**
	 * 登录验证
	 * @param student
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public boolean login(Student student,HttpServletRequest request){

		List<Student> list = studentService.select(student);
		if (list.size() > 0){
			request.getSession().setAttribute("role",0);
			request.getSession().setAttribute("user",list.get(0));
			return true;
		}
		return false;

	}
}
