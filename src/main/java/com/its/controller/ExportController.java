package com.its.controller;

import com.its.jxls.Jxls;
import com.its.jxls.JxlsFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    public   String S_TEMPLATE ="";
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public static final String YYYYMMDD = "yyyy-MM-dd";

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(final HttpServletResponse response,String pro,String bg,String ed,String list){
        try {
            Map<String, Object> reportData=new HashMap<String, Object>();
            String flg=pro;

            /*if(flg.equals("appSys")){
                S_TEMPLATE= "appSys.xls";
                reportData=getAppSysData(obj1);
            }else if(flg.equals("appBp") || flg.equals("dbSys")||flg.equals("logBp")){
                S_TEMPLATE= flg+".xls";
                reportData = getInspectorData(obj1,flg);
            }else if(flg.equals("onDuty")){
                S_TEMPLATE= "onDuty.xls";
                reportData=getDutyData(obj1);
            } else if (flg.equals("appService")){
                S_TEMPLATE= "appService.xls";
                reportData=getCommonData(obj1, "appService",null);
            }else if(flg.equals("email")){
                S_TEMPLATE= "email.xls";
                reportData=getEmailData(obj1);
            }else if(flg.equals("netUsing")){
                S_TEMPLATE= "netUsing.xls";
                reportData=getCommonDataS("netUsing",null);
            }else if(flg.equals("emailUpdate")){
                S_TEMPLATE= "emailUpdate.xls";
                reportData=getCommonData(obj1, "emailUpdate",analysis(list));
            }else if(flg.equals("keyUpdate")){
                S_TEMPLATE= "keyUpdate.xls";
                reportData=getCommonData(obj1, "keyUpdate",analysis(list));
            } else if(flg.equals("appGrant")){
                S_TEMPLATE= "appGrant.xls";
                reportData=getCommonData(obj1, "appGrant",analysis(list));
            } else if(flg.equals("infoCount")){
                S_TEMPLATE= "infoCount.xls";
                reportData=getCommonDataS("infoCount",analysis(list));
            }else if(flg.equals("linkData")){
                S_TEMPLATE= "linkData.xls";
                reportData=getCommonDataS("linkData",analysis(list));
            }else if(flg.equals("linkMan")){
                S_TEMPLATE= "linkMan.xls";
                reportData=getCommonDataS("linkMan",analysis(list));
            }else if(flg.equals("electricity")){
                S_TEMPLATE= "electricity.xls";
                reportData=getElectricity(obj1);
            }else if(flg.equals("individual")){
                S_TEMPLATE= "individual.xls";
                reportData=getCommonData(obj1, "individual", analysis(list));
            }else if(flg.equals("local")){
                S_TEMPLATE= "local.xls";
                reportData=getCommonData(obj1,"local", analysis(list));
            }else if(flg.equals("weinei")){
                S_TEMPLATE= "weinei.xls";
                reportData=getCommonData(obj1, "weinei", analysis(list));
            }else if(flg.equals("localKey")){
                S_TEMPLATE= "localKey.xls";
                reportData=getCommonDataS("localKey", analysis(list));
            } else if(flg.equals("userLogin")){
                S_TEMPLATE= "userLogin.xls";
                reportData=getCommonData(obj1, "userLogin", null);
            }else if(flg.equals("statistics")){
                S_TEMPLATE= "statistics.xls";
                reportData=getStatistics(obj1);
            } else if (flg.equals("app_trouble")){
                S_TEMPLATE= "appTrouble.xls";
                reportData=getCommonData(obj1, "app_trouble",null);
            }else if(flg.equals("appUpdate")){
                S_TEMPLATE= "appUpdate.xls";
                reportData=getCommonDataS("appUpdate",null);
            }else if (flg.equals("verticalTelReport")){
                S_TEMPLATE= "verticalTelReport.xls";
                reportData=getCommonData(obj1, "verticalTelReport", analysis(list));
            }else if (flg.equals("internetTelReport")){
                S_TEMPLATE= "internetTelReport.xls";
                reportData=getCommonData(obj1,"internetTelReport", analysis(list));
            }*/
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
        response.setHeader(
                "Content-Disposition",
                "attachment;filename="
                        + java.net.URLEncoder
                        .encode(sdf.format(new Date()) + "_" + S_TEMPLATE, "UTF-8")
        );

        //???导的包是否正确
        IOUtils.copy(new FileInputStream(JxlsFactory.S_PATH_DEST + fileName),
                                        response.getOutputStream());

    }


}
