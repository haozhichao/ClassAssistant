package com.its.controller;


import com.its.db.pojo.Student;
import com.its.jxls.Jxls;
import com.its.jxls.JxlsFactory;
import com.its.service.IStudentService;
import org.h2.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/9/24.
 */
@RestController
@RequestMapping("/export")
public class ExportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    private IStudentService studentService;

    public   String S_TEMPLATE ="";
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public static final String YYYYMMDD = "yyyy-MM-dd";

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(final HttpServletResponse response,String pro,HttpServletRequest request ){
        try {
            Map<String, Object> reportData=new HashMap<String, Object>();
            String flg=pro;
            if(flg.equals("student")){
                S_TEMPLATE= "student.xls";
                reportData=getStudentData(request);
            }
            wirteFileAndDownload(response, reportData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void wirteFileAndDownload(final HttpServletResponse response,Map<String, Object> t_reportData)
                                    throws Exception {
        Jxls t_jxls = JxlsFactory.getTransformer(S_TEMPLATE, t_reportData);
        String fileName=t_jxls.transform();
        response.setContentType("application/vnd.ms-excel");
        //设定文件展现方式(在线打开【inline】/在线下载【attachment】)
        response.setHeader(
                                        "Content-Disposition",
                                        "attachment;filename="
                                                                        + java.net.URLEncoder
                                                                        .encode(sdf.format(new Date()) + "_" + S_TEMPLATE, "UTF-8")
        );
        IOUtils.copy(new FileInputStream(JxlsFactory.S_PATH_DEST + fileName),
                                        response.getOutputStream());

    }


    //报表所需要的数据
    private Map<String, Object> getStudentData(HttpServletRequest request){

        int id = (Integer) request.getSession().getAttribute("id");
        List<Student> students = studentService.getStudentByClassId(id);
        Map<String,Object> beans = new HashMap<String,Object>();
        beans.put("students", students);
        return beans;
    }
}
