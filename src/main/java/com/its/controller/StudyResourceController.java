package com.its.controller;

import com.its.common.EnvVariable;
import com.its.common.FileUtils;
import com.its.db.pojo.Student;
import com.its.db.pojo.StudyResource;
import com.its.db.pojo.Teacher;
import com.its.service.IStudyResourceService;

import com.alibaba.fastjson.JSONArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/studyResource")
public class StudyResourceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudyResourceController.class);

	@Autowired
	private IStudyResourceService studyResourceService;

	/**
	 * 上传资源方法
	 * @param req
	 * @return 0-失败; 1-成功
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(final HttpServletRequest req, final HttpServletResponse resp) {
		PrintWriter out = null;
		List<String> errors = new ArrayList<String>();
		List<String> success = new ArrayList<String>();
		try {
			req.setCharacterEncoding(CharEncoding.UTF_8);
			resp.setCharacterEncoding(CharEncoding.UTF_8);
			out = resp.getWriter();
			// 接收上传的附件，保存到服务器目录
			List<FileItem> items = getFileItems(req);
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (!item.isFormField()) {
					String fileName = item.getName();
					String filePath = EnvVariable.uploadPath;
					InputStream is = item.getInputStream();
					// window无法识别的文件名符号
					fileName = fileName.replaceAll("\\|/|:|\\*|\\?|\"|<|>|\\|", "");
					filePath = filePath + fileName; // 存储路径
					FileUtils.saveFile(is, filePath);
					saveStudyResource(req, fileName); // 将文件相关信息保存到数据库中
					success.add(fileName); // 记录上传成功个数
				}
			}
		} catch (Exception e) {
			LOGGER.error("上传资源失败error: " + e.getMessage(), e);
			out.print(0);
			out.flush();
			out.close();
		}
		LOGGER.info(JSONArray.toJSONString(errors));
		out.print(2);
		out.flush();
		out.close();
	}
	@SuppressWarnings("unchecked")
	public static List<FileItem> getFileItems(final HttpServletRequest req) {
		ServletFileUpload.isMultipartContent(req);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			LOGGER.error("error: " + e.getMessage(), e);
		}
		return items;
	}

	/**
	 * 将上传的文件相关信息保存到数据库里
	 * @param fileName 文件名
	 */
	public void saveStudyResource(final HttpServletRequest request, final String fileName) {
		// 获取当前用户角色
		int roleId = (Integer)request.getSession().getAttribute("role");
		// 获取当前用户名字、用户id
		String userName = "";
		Integer userId = 0;
		if(roleId == 0) { // 学生
			Student student = (Student)request.getSession().getAttribute("user");
			userName = student.getName();
			userId = student.getId();
		} else if(roleId == 1) { // 老师
			Teacher teacher = (Teacher)request.getSession().getAttribute("user");
			userName = teacher.getName();
			userId = teacher.getId();
		}
		// 获取课堂id
		int classRoomId = (Integer)request.getSession().getAttribute("id");
		String filePath = EnvVariable.uploadPath + fileName;
		String description = (String)request.getAttribute("description");
		StudyResource studyResource = new StudyResource();
		studyResource.setFilename(fileName);
		studyResource.setDescription(description);
		studyResource.setPath(filePath);
		studyResource.setUploadername(userName);
		studyResource.setUploaderid(userId);
		studyResource.setUploadertype(roleId);
		studyResource.setClassroomid(classRoomId);
		studyResource.setDownloadnum(0); // 初始化下载次数为0次
		studyResource.setZan(0);         // 赞次数0次
		studyResource.setDel(false);
		studyResourceService.save(studyResource); // 存储数据库
		LOGGER.info("文件fileName[" + fileName + "]相关信息保存数据库成功.");
	}
	/**
	 * 通过classroomid得到该课堂中所有的资源
	 * @param request
	 * @return 学习资源的集合
	 */
	@RequestMapping("/getAll")
	@ResponseBody
	public List<StudyResource> getAllByClassId(HttpServletRequest request ){

		//课堂id,直接从session得到
		int id = (Integer) request.getSession().getAttribute("id");
		StudyResource studyResource = new StudyResource() ;
		studyResource.setClassroomid(id);
		//通过id得到集合
		return studyResourceService.select(studyResource);

	}
}
