package com.its.service.impl;

import com.its.db.pojo.Student;
import com.its.service.IStudentService;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/3/3.
 */
@Service("studentService")
public class StudentService extends BaseService<Student> implements IStudentService {
}
