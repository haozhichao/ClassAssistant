package com.its.common;

import java.io.File;

/**
 * Created by haozhichao on 2016/3/27 0027
 * 相关信息工具类.
 */
public class EnvVariable {
    public static final String uploadPath;
    static {
        uploadPath = "D:" + File.separator + "studyResource" + File.separator;
    }
}
