package com.its.service.impl;

import com.its.db.pojo.User;
import com.its.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/3/3.
 */
@Service("userService")
public class UserService  extends BaseService<User> implements IUserService{
}
