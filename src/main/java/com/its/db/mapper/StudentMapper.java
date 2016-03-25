package com.its.db.mapper;

import com.its.controller.vo.StudentVo;
import com.its.db.pojo.Student;
import com.its.util.MyMapper;

import java.util.List;

public interface StudentMapper extends MyMapper<Student> {

    List<Student> getByPage(StudentVo studentVo);

    Integer getCount(StudentVo studentVo);

    List<Student> getStudentByClassId(int id);
}
