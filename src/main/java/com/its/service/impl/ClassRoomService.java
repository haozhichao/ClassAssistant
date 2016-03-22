package com.its.service.impl;

import com.its.db.mapper.ClassRoomMapper;
import com.its.db.pojo.ClassRoom;
import com.its.service.IClassRoomService;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
@Service("classRoomService")
public class ClassRoomService extends BaseService<ClassRoom> implements IClassRoomService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public List<ClassRoom> getClassRoomByStu(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ClassRoomMapper mapper = session.getMapper(ClassRoomMapper.class);
            return mapper.getClassRoomByStu(id);
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return null;
    }
}
