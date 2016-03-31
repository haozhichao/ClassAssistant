package com.its.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by haozhichao on 2016/3/27 0027.
 * 文件操作工具类
 */
public class FileUtils extends org.apache.commons.io.FileUtils{

    public static void saveFile(final InputStream is, final String fileName) throws Exception {
        OutputStream os = new FileOutputStream(new File(fileName));
        int bytesRead = 0;
        byte[] buffer = new byte[1024];
        while ((bytesRead = is.read(buffer, 0, buffer.length)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.flush();
        os.close();
    }
}
