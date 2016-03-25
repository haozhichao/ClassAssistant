package com.its.jxls;

import net.sf.jxls.transformer.XLSTransformer;

import java.util.Map;

/**
 * Transformer <br> <p> Create on : 2012-9-8<br> <p> </p> <br>
 *
 * @author xingxiaohuan<br>
 * @version riil.multilevel.action v1.0 <p> <br> <strong>Modify History:</strong><br> user
 *          modify_date modify_content<br> -------------------------------------------<br> <br>
 */
public class JxlsTransformer {

    /**
     * 模板目录
     */
    public static final String S_DEFOULT_PATH_SRC = "/deploy/reportdata.war/jxlsTemplate/";
    /**
     * 报表数出目录
     */
    public static final String S_DEFOULT_PATH_DEST = "/deploy/reportdata.war/jxlsOutput/";
    /**
     * 转换器
     */
    private XLSTransformer m_transformer = new XLSTransformer();
    /**
     * 模板目录全路径
     */
    private String m_fullPahtSrc;
    /**
     * 报表数出目录全路径
     */
    private String m_fullPahDest;
    /**
     * 数据结构 1.Map->Object 2.Map->List->(Map/GroovyRowResult/DynaBean)
     */
    private Map<String, Object> m_beans;
    /**
     * 模板文件名
     */
    private String m_templateFileName;
    /**
     * 生成报表文件名
     */
    private String m_reportFileName;

    /**
     * 构造函数
     *
     * @param fileName 源文件名。输出文件名自动计算
     * @param beans    数据对象
     */
    public JxlsTransformer(final String fileName, final Map<String, Object> beans) {
        super();
        this.m_fullPahtSrc = S_DEFOULT_PATH_SRC + fileName + ".xls";
        this.m_fullPahDest = S_DEFOULT_PATH_DEST + fileName + "_output.xls";
        this.m_beans = beans;
    }

    /**
     * 构造函数
     *
     * @param fileNameSrc  源文件名
     * @param fileNameDest 输出文件名
     * @param beans        数据对象
     */
    public JxlsTransformer(final String fileNameSrc, final String fileNameDest,
                                    final Map<String, Object> beans) {
        super();
        this.m_fullPahtSrc = S_DEFOULT_PATH_SRC + fileNameSrc + ".xls";
        this.m_fullPahDest = S_DEFOULT_PATH_DEST + fileNameDest + ".xls";
        this.m_beans = beans;
    }

    /**
     * 生成报表
     *
     * @throws Exception e
     */
    public void transform() throws Exception {
        m_transformer.transformXLS(m_fullPahtSrc, m_beans, m_fullPahDest);
    }

    /**
     * 获得原生对象
     *
     * @return XLSTransformer 原生对象
     */
    public XLSTransformer getTransformer() {
        return m_transformer;
    }

    public String getM_templateFileName() {
        return m_templateFileName;
    }

    public void setM_templateFileName(String m_templateFileName) {
        this.m_templateFileName = m_templateFileName;
    }

    public String getM_reportFileName() {
        return m_reportFileName;
    }

    public void setM_reportFileName(String m_reportFileName) {
        this.m_reportFileName = m_reportFileName;
    }
}
