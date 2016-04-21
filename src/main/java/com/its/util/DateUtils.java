package com.its.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by haozhichao on 2016/4/21 0021.
 */
public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    // 根据
    public static String getDateFormatStr(Date date, String format) {
        if(date == null) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            LOGGER.error("时间格式化失败{}", e);
            return "";
        }
    }
}
