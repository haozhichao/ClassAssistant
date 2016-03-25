package com.its.service;

import com.its.controller.vo.StudentVo;
import com.its.db.pojo.Student;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public interface IStudentService extends IService<Student>{

    List<Student> getByPage(StudentVo studentVo);

    Integer getCount(StudentVo studentVo);

    List<Student> getStudentByClassId(int id);

}
