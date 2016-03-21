package com.its.service;

import com.its.db.pojo.ClassRoom;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public interface IClassRoomService extends IService<ClassRoom>{

    List<ClassRoom> getClassRoomByStu(int id);
}
