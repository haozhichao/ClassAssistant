package com.its.controller;

import com.its.common.EnvVariable;
import com.its.common.FileUtils;
import com.its.db.pojo.Student;
import com.its.db.pojo.StudyResource;
import com.its.db.pojo.Teacher;
import com.its.db.pojo.UploadFile;
import com.its.service.IStudyResourceService;
import com.its.util.DateUtils;

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/studyResource")
public class StudyResourceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudyResourceController.class);

	@Autowired
	private IStudyResourceService studyResourceService;

	/**
	 * 保存多个文件信息
	 * @param request
	 * @param uploadFiles
	 */
	public void saveStudyResources(final HttpServletRequest request, final List<UploadFile> uploadFiles) {
		if(request == null || uploadFiles == null) {
			return;
		}
		for(UploadFile uploadFile : uploadFiles) {
			saveStudyResource(request, uploadFile.getFileName());
		}
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

	/**
	 * 上传文件
	 * @param request
	 * @param response
	 * 返回值: 0-失败；1-成功
	 */
	@RequestMapping("/uploadFile")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding(CharEncoding.UTF_8);
			out = response.getWriter();
			// 1、上传文件
			List<UploadFile> uploadFiles = saveFile(request);

			// 2、保存文件信息到数据库中
			saveStudyResources(request, uploadFiles);
			out.print(1);
		} catch (Exception e) {
			LOGGER.error("上传资源失败error: " + e.getMessage(), e);
			out.print(0);
		}
	}

	/**
	 * 上传文件
	 * @param request
	 * @return 文件集合
	 * @throws IOException
	 */
	public List<UploadFile> saveFile(HttpServletRequest request) throws IOException {
		// 上传的文件集合
		List<UploadFile> uploadFiles = new ArrayList<UploadFile>();

		// 转换为文件类型的request
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		// 获取对应file对象
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		Iterator<String> fileIterator = multipartRequest.getFileNames();

		// 上传路径
		String prePath = EnvVariable.uploadPath;
		while (fileIterator.hasNext()) {
			String fileKey = fileIterator.next();

			// 获取对应文件
			MultipartFile multipartFile = fileMap.get(fileKey);

			if (multipartFile.getSize() != 0L) {
				// 调用doSaveFile方法保存
				UploadFile file = doSaveFile(multipartFile);
				file.setPrePath(prePath);
				uploadFiles.add(file);
			}
		}
		return uploadFiles;
	}

	/**
	 * 具体去上传文件方法
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	private UploadFile doSaveFile(MultipartFile multipartFile) throws IOException {
		// 上传的文件名
		String originalFilename = multipartFile.getOriginalFilename();

		String contentType = multipartFile.getContentType();
		String type = contentType.substring(contentType.indexOf("/") + 1);
		// 文件名：时间_原始文件名
		String fileName = DateUtils.getDateFormatStr(new Date(), "[yyyy-MM-dd_HHmmssSSS]") + "_" +originalFilename;

		// 封装了一个简单的file对象，增加了几个属性
		UploadFile file = new UploadFile(EnvVariable.uploadPath, fileName);
		file.setContentType(contentType);
		LOGGER.debug("文件保存路径：" + file.getSaveDirectory());

		// 通过org.apache.commons.io.FileUtils的writeByteArrayToFile对文件进行保存
		FileUtils.writeByteArrayToFile(file.getFile(), multipartFile.getBytes());

		LOGGER.info("文件：" + originalFilename + "上传成功!");
		return file;
	}
}
