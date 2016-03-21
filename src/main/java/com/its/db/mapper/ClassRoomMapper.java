package com.its.db.mapper;

import com.its.db.pojo.ClassRoom;
import com.its.util.MyMapper;

import java.util.List;

public interface ClassRoomMapper extends MyMapper<ClassRoom> {

    List<ClassRoom> getClassRoomByStu(int id);
}
