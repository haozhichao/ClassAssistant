package com.its.jxls;

import net.sf.jxls.transformer.XLSTransformer;
import java.util.Map;

/**
 * @author
 *
 */
public class JxlsTransformer {

    /**
     * ģ��Ŀ¼
     */
    public static final String S_DEFOULT_PATH_SRC = "/deploy/reportdata.war/jxlsTemplate/";
    /**
     * ��������Ŀ¼
     */
    public static final String S_DEFOULT_PATH_DEST = "/deploy/reportdata.war/jxlsOutput/";
    /**
     * ת����
     */
    private XLSTransformer m_transformer = new XLSTransformer();
    /**
     * ģ��Ŀ¼ȫ·��
     */
    private String m_fullPahtSrc;
    /**
     * ��������Ŀ¼ȫ·��
     */
    private String m_fullPahDest;
    /**
     * ���ݽṹ 1.Map->Object 2.Map->List->(Map/GroovyRowResult/DynaBean)
     */
    private Map<String, Object> m_beans;
    /**
     * ģ���ļ���
     */
    private String m_templateFileName;
    /**
     * ���ɱ����ļ���
     */
    private String m_reportFileName;

    /**
     * ���캯��
     *
     * @param fileName Դ�ļ���������ļ����Զ�����
     * @param beans    ���ݶ���
     */
    public JxlsTransformer(final String fileName, final Map<String, Object> beans) {
        super();
        this.m_fullPahtSrc = S_DEFOULT_PATH_SRC + fileName + ".xls";
        this.m_fullPahDest = S_DEFOULT_PATH_DEST + fileName + "_output.xls";
        this.m_beans = beans;
    }

    /**
     * ���캯��
     *
     * @param fileNameSrc  Դ�ļ���
     * @param fileNameDest ����ļ���
     * @param beans        ���ݶ���
     */
    public JxlsTransformer(final String fileNameSrc, final String fileNameDest,
                           final Map<String, Object> beans) {
        super();
        this.m_fullPahtSrc = S_DEFOULT_PATH_SRC + fileNameSrc + ".xls";
        this.m_fullPahDest = S_DEFOULT_PATH_DEST + fileNameDest + ".xls";
        this.m_beans = beans;
    }

    /**
     * ���ɱ���
     *
     * @throws Exception e
     */
    public void transform() throws Exception {
        m_transformer.transformXLS(m_fullPahtSrc, m_beans, m_fullPahDest);
    }

    /**
     * ���ԭ������
     *
     * @return XLSTransformer ԭ������
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
