package com.its.service.impl;

import com.its.controller.vo.StudentVo;
import com.its.db.mapper.ClassRoomMapper;
import com.its.db.mapper.StudentMapper;
import com.its.db.pojo.Student;
import com.its.service.IStudentService;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
@Service("studentService")
public class StudentService extends BaseService<Student> implements IStudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public List<Student> getByPage(StudentVo studentVo) {
        SqlSession session = sqlSessionFactory.openSession();
        List<Student> students = new ArrayList<Student>();
        try {
            StudentMapper mapper = session .getMapper(StudentMapper.class);
            students = mapper.getByPage(studentVo);
            return students;
        } catch (Exception e) {
            LOGGER.error("getByPage出现异常", e);
        } finally {
            session.close();
        }
        return students;
    }

    public Integer getCount(StudentVo studentVo) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper mapper = session .getMapper(StudentMapper.class);
            return mapper.getCount(studentVo);
        } catch (Exception e) {
            LOGGER.error("getByPage出现异常", e);
        } finally {
            session.close();
        }
        return 0;
    }

    @Override public List<Student> getStudentByClassId(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        List<Student> students = new ArrayList<Student>();
        try {
            StudentMapper mapper = session .getMapper(StudentMapper.class);
            students = mapper.getStudentByClassId(id);
            return students;
        } catch (Exception e) {
            LOGGER.error("getByPage出现异常", e);
        } finally {
            session.close();
        }
        return students;
    }

}
