package com.its.jxls;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.processor.CellProcessor;
import net.sf.jxls.processor.PropertyPreprocessor;
import net.sf.jxls.processor.RowProcessor;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 报表生成对象，根据excel模板生成报表 <br> <p> Create on : 2012-9-8<br> <p> </p> <br>
 *
 * @author xingxiaohuan<br>
 * @version riil.multilevel.action v1.0 <p> <br> <strong>Modify History:</strong><br> user
 *          modify_date modify_content<br> -------------------------------------------<br> <br>
 */
public class Jxls extends XLSTransformer  {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory
                                    .getLogger(Jxls.class);
    /**
     * 模板文件名
     */
    private String m_templateFileName;
    /**
     * 生成报表文件名
     */
    private String m_reportFileName;
    /**
     * 数据结构 1.Map->Object(->Object/List) 2.Map->List->Map/GroovyRowResult/DynaBean
     */
    private Map<String, Object> m_beans;

    /**
     * 构造函数
     */
    Jxls() {
        super();
    }

    /**
     * 生成报表,并返回文件全路径
     *
     * @return 文件全路径
     */
    public String transform() {
        String t_fullPathSrc = JxlsFactory.S_PATH_SRC + m_templateFileName;
        String t_fullPathDest =JxlsFactory.S_PATH_DEST + m_reportFileName;
        try {
            transformXLS(t_fullPathSrc, m_beans, t_fullPathDest);
        } catch (Exception t_e) {
            logger.error("报表生成出错：模板" + t_fullPathSrc + "非法格式", t_e);
            logger.info("错误信息：{}"+t_e);
            return "";
        }
        logger.info("报表生成成功:模板文件" + t_fullPathSrc + ",报表文件" + t_fullPathDest);
        return m_reportFileName;
    }

    public String transformBySheet(List<String> cities,ArrayList<List> objects,Map<String, Object> map) {
        String t_fullPathSrc = JxlsFactory.S_PATH_SRC + m_templateFileName;
        String t_fullPathDest =JxlsFactory.S_PATH_DEST + m_reportFileName;
        try {
            transformXLSBySheet(t_fullPathSrc, t_fullPathDest, cities, objects, map);
        } catch (Exception t_e) {
            logger.error("报表生成出错：模板" + t_fullPathSrc + "非法格式", t_e);
            logger.info("错误信息：{}"+t_e);
            return "";
        }
        logger.info("报表生成成功:模板文件" + t_fullPathSrc + ",报表文件" + t_fullPathDest);
        return m_reportFileName;
    }

    public String transformByCell() {
        String t_fullPathSrc = JxlsFactory.S_PATH_SRC + m_templateFileName;
        String t_fullPathDest =JxlsFactory.S_PATH_DEST + m_reportFileName;
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(t_fullPathSrc));
            org.apache.poi.ss.usermodel.Workbook workbook = transformXLS(is, m_beans);
            Sheet sheet = workbook.getSheetAt(0);
            List<Map<String,Object>> list= (List<Map<String, Object>>) m_beans.get("list");
            List<Map<String,Object>> list1= (List<Map<String, Object>>) m_beans.get("individuals");
            int i=list.size();
            int j=list1.size();
            CellRangeAddress region= new CellRangeAddress(0,(short)i-1,0,(short)0);
            CellRangeAddress region1= new CellRangeAddress(i+1,(short)i+j,0,(short)0);
            sheet.addMergedRegion(region);
            sheet.addMergedRegion(region1);
            OutputStream os = new BufferedOutputStream(new FileOutputStream(t_fullPathDest));
            workbook.write(os);
            is.close();
            os.flush();
            os.close();
        } catch (Exception t_e) {
            logger.error("报表生成出错：模板" + t_fullPathSrc + "非法格式", t_e);
            logger.info("错误信息：{}"+t_e);
            return "";
        }
        logger.info("报表生成成功:模板文件" + t_fullPathSrc + ",报表文件" + t_fullPathDest);
        return m_reportFileName;
    }

    public void transformXLSBySheet(String srcFilePath, String destFilePath,List<String> cities,ArrayList<List> objects,Map<String, Object> map) throws ParsePropertyException, IOException, InvalidFormatException {
        InputStream is = new BufferedInputStream(new FileInputStream(srcFilePath));
        org.apache.poi.ss.usermodel.Workbook workbook = transformMultipleSheetsList(is,objects,cities ,"city", map, 0);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destFilePath));
        workbook.write(os);
        is.close();
        os.flush();
        os.close();
    }

    public void transformXLS(String srcFilePath, Map beanParams, String destFilePath) throws ParsePropertyException, IOException, InvalidFormatException {
        InputStream is = new BufferedInputStream(new FileInputStream(srcFilePath));
        org.apache.poi.ss.usermodel.Workbook workbook = transformXLS(is, beanParams);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destFilePath));
        resetCellFormula(workbook);
        workbook.write(os);
        is.close();
        os.flush();
        os.close();
    }

    /**
     *
     * 重新设置单元格计算公式
     *
     *
     * @param wb*/
    public static void resetCellFormula(Workbook wb) {
        FormulaEvaluator e= wb.getCreationHelper().createFormulaEvaluator();
        int sheetNum = wb.getNumberOfSheets();
        for (int i = 0; i < sheetNum; i++) {
            // HSSFSheet sheet = wb.getSheetAt(i);
            Sheet sheet = wb.getSheetAt(i);
            int rows = sheet.getLastRowNum() + 1;
            for (int j = 0; j < rows; j++) {
                // HSSFRow row = sheet.getRow(j);
                Row row = sheet.getRow(j);
                if (row == null)
                    continue;
                int cols = row.getLastCellNum();
                for (int k = 0; k < cols; k++) {
                    // HSSFCell cell = row.getCell(k);
                    Cell cell = row.getCell(new Integer(k));
                    if (cell == null)
                        continue;
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                        cell.setCellFormula(cell.getCellFormula());
                        cell = e.evaluateInCell(cell);
                    }
                }
            }
        }
    }

    public org.apache.poi.ss.usermodel.Workbook transformXLS(InputStream is, Map beanParams) throws ParsePropertyException, InvalidFormatException {
        org.apache.poi.ss.usermodel.Workbook hssfWorkbook = null;
        try {
            hssfWorkbook = WorkbookFactory.create(is);
            this.transformWorkbook(hssfWorkbook, beanParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hssfWorkbook;
    }


    /**
     * 生成报表:根据样式处理器
     *
     * @param processors 样式处理器
     * @return 文件全路径
     */
    public String transform(final Object[] processors) {
        if (processors != null) {
            for (Object t_processor : processors) {
                if (t_processor instanceof CellProcessor) {
                    this.registerCellProcessor((CellProcessor) t_processor);
                } else if (t_processor instanceof RowProcessor) {
                    this.registerRowProcessor((RowProcessor) t_processor);
                } else if (t_processor instanceof PropertyPreprocessor) {
                    this.registerPropertyPreprocessor((PropertyPreprocessor) t_processor);
                }
            }
        }
        return this.transform();
    }

    /**
     * @return 模板名称
     */
    public String getTemplateFileName() {
        return m_templateFileName;
    }

    /**
     * @param templateFileName 模板名称
     * @return Jxls
     */
    Jxls setTemplateFileName(final String templateFileName) {
        this.m_templateFileName = templateFileName;
        return this;
    }

    /**
     * @return 文件名称
     */
    public String getReportFileName() {
        return m_reportFileName;
    }

    /**
     * @param reportFileName 报告名称
     * @return Jxls
     */
    Jxls setReportFileName(final String reportFileName) {
        this.m_reportFileName = reportFileName;
        return this;
    }

    /**
     * @return 数据集
     */
    public Map<String, Object> getBeans() {
        return m_beans;
    }

    /**
     * @param beans 数据集
     * @return jxls
     */
    Jxls setBeans(final Map<String, Object> beans) {
        this.m_beans = beans;
        return this;
    }
}
