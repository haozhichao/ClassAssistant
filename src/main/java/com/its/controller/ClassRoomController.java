package com.its.controller;

import com.its.db.pojo.ClassRelationStudent;
import com.its.db.pojo.ClassRoom;

import com.its.db.pojo.Student;
import com.its.db.pojo.Teacher;
import com.its.service.IClassRelationStudentService;
import com.its.service.IClassRoomService;
import com.its.util.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	@Autowired
	private IClassRelationStudentService classRelationStudentService;

	/**
	 * 创建课堂
	 * @param classRoom
	 * @param request
	 * @return
	 */
	@RequestMapping("/createClassRoom")
	@ResponseBody
	public int createClassRoom(ClassRoom classRoom ,HttpServletRequest request){

		//生成base58的邀请码
		String uuid = UuidUtils.base58Uuid();
		classRoom.setUuid(uuid);
		classRoom.setCreaterdate(new Date());
		classRoom.setStunum(1);
		//从session中获取老师信息
		Teacher teacher = (Teacher)request.getSession().getAttribute("user");
		if (teacher != null){
			classRoom.setTeacherid(teacher.getId());
		}else{
			//session过期了，重新登录
		}

		classRoom.setCreatername(teacher.getName());
		classRoom.setDel(false);

		return classRoomService.save(classRoom);
	}

	/**
	 * 加入课堂
	 * @param classRoom
	 * @param request
	 * @return
	 */
	@RequestMapping("/addClassRoom")
	@ResponseBody
	public int addClassRoom(ClassRoom classRoom ,HttpServletRequest request){

		List<ClassRoom> list = classRoomService.select(classRoom);
		if(list.size()>0){		//存在邀请码的班级
			ClassRelationStudent classRelationStudent  = new ClassRelationStudent() ;
			classRelationStudent.setDel(false);
			classRelationStudent.setClassroomid(list.get(0).getId());
			Object obj = request.getSession().getAttribute("user");
			if (obj == null){
				//session过期了
				return -2;
			}else{
				Student student = (Student)obj;
				ClassRelationStudent classRelationStudent1  = new ClassRelationStudent();
				classRelationStudent1.setStudentid(student.getId());
				classRelationStudent1.setClassroomid(list.get(0).getId());
				if (classRelationStudentService.select(classRelationStudent1).size()>0){
					//加入过这个班级，不需要再次添加
					return -3;
				}else {
					classRelationStudent.setStudentid(student.getId());
					//班级人数添加一个
					ClassRoom classRoom1  = list.get(0);
					classRoom1.setStunum(classRoom1.getStunum() + 1);
					classRoomService.updateNotNull(classRoom1);
					return classRelationStudentService.save(classRelationStudent);
				}

			}
		}

		//不存在的邀请码
		return -1;

	}

	/**
	 * 老师登录时，展示所有课堂
	 * @param
	 * @return
	 */
	@RequestMapping("/getAllByTeacher")
	@ResponseBody
	public List<ClassRoom> getAllByTeacher(HttpServletRequest request){

		Teacher teacher = (Teacher)request.getSession().getAttribute("user");
		if(teacher!=null){
			ClassRoom classRoom = new ClassRoom();
			classRoom.setTeacherid(teacher.getId());
			List<ClassRoom> list = classRoomService.select(classRoom);
			return list;
		}
		return null;

	}
	/**
	 * 学生登录时，显示所加入的所有课堂
	 * @param
	 * @return
	 */
	@RequestMapping("/getAllByStudent")
	@ResponseBody
	public List<ClassRoom> getAllByStudent(HttpServletRequest request){

		Student student = (Student)request.getSession().getAttribute("user");
		if(student!=null){
			List<ClassRoom> list = classRoomService.getClassRoomByStu(student.getId());
			return list;
		}
		return null;

	}

	/**
	 * 返回角色权限
	 * @param request
	 * @return 0-学生；1-老师
	 */
	@RequestMapping("/getRole")
	@ResponseBody
	public int getRole(HttpServletRequest request){
		//session过期了
		int role=0;
		Object obj = request.getSession().getAttribute("role");
		if(obj!=null){
			role = (Integer)obj;
		}
		return role;

	}

	/**
	 * 把传过来的id存在session中去
	 * @param id
	 */
	@RequestMapping("/addIdSession")
	@ResponseBody
	public void addIdSession(String id,HttpServletRequest request){
		//设置id到session中
		request.getSession().setAttribute("id",id);
	}

	/**
	 * 跳转到课堂内部
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/classRoom2Room")
	@ResponseBody
	public ModelAndView classRoom2Room(HttpServletRequest request,int id){

		request.getSession().setAttribute("id",id);

		return new ModelAndView("redirect:/index.html");
	}

}
