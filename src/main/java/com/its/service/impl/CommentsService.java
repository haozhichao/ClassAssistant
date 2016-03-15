package com.its.service.impl;

import com.its.db.pojo.Comments;
import com.its.service.ICommentsService;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/3/3.
 */
@Service("commentsService")
public class CommentsService extends BaseService<Comments> implements ICommentsService {
}
