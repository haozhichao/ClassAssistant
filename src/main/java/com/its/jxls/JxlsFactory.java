package com.its.jxls;


import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * Jxls���󹤳��࣬����Jxls�Ĳ���
 *
 * @author
 *
 */
public class JxlsFactory {

    /**
     * ��־
     */
    private static final Logger logger = LoggerFactory
            .getLogger(JxlsFactory.class);
    /**
     * ��Ŀ¼
     */

    public static final String S_PATH_ROOT =  System.getProperty("user.dir")+File.separator + "src"+File.separator+"main"+File.separator+"resources" +File.separator;

  //  ����Ŀ¼
  // public static final String S_PATH_ROOT=  new File("/").getAbsolutePath()+"FGW"+File.separator+"conf"+File.separator;
    /**
     * ģ��Ŀ¼
     */
    public static  String S_PATH_SRC =S_PATH_ROOT+ "template" + File.separator;
    /**
     * ����Ŀ¼
     */
    public static final String S_PATH_DEST = S_PATH_ROOT+"result" + File.separator;

    /**
     * ������ͼĿ¼
     */
    public static final String S_PATH_REPORT_VIEW = "view" + File.separator;
    /**
     * ������
     */
    private static long m_counter = 0;
    /**
     * singleton
     */
    private static final JxlsFactory m_foctory = new JxlsFactory();
    /**
     * �ֲ߳̾�����
     */
    private ThreadLocal<Jxls> m_transformerThreadLocal = new ThreadLocal<Jxls>();

    /**
     * ����Jxls����
     *
     * @return �ɹ�����Jxls��ʧ�ܷ��� null
     */
    private static Jxls createJxls() {
        if (m_foctory.m_transformerThreadLocal.get() == null) {
            Jxls t_transformer = new Jxls();
            m_foctory.m_transformerThreadLocal.set(t_transformer);
            return t_transformer;
        } else {
            return m_foctory.m_transformerThreadLocal.get();
        }
    }

    /**
     * ���캯��
     */
    private JxlsFactory() {
    }

    /**
     * ���Jxls����
     *
     * @param templateFileName Դ�ļ���������ļ����Զ�����
     * @param beans            �������������
     * @return �ɹ�����Jxls��ʧ�ܷ��� null
     */
    public static Jxls getTransformer(final String templateFileName,
                                      final Map<String, Object> beans) {
        if (templateFileName == null || templateFileName.equals("")
            || templateFileName.length() < 8) {
            logger.error("��ȡJxls����ʱ����:ģ���ļ�������Ϊ��,�����ļ������ȱ��������λ");
            return null;
        }
        if (!templateFileName.endsWith(".xls")) {
            logger.error("��ȡJxls����ʱ����:ģ��Ӧ����xls�ļ�");
            return null;
        }
        String t_prefix = templateFileName.substring(0, 3);

        FastDateFormat t_formatter = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");
        String t_reportFileName =
                t_formatter.format(new Date()) + "_" + String.format("%03d", m_counter++ % 1000)
                + "_" + t_prefix + ".xls";
        Jxls t_jxls = createJxls();
        t_jxls.setTemplateFileName(templateFileName).setReportFileName(t_reportFileName)
                .setBeans(beans);
        return t_jxls;
    }



}
