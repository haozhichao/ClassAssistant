package com.its.jxls;


import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * Jxls对象工厂类，控制Jxls的产生 <br> <p> Create on : 2012-9-8<br> <p> </p> <br>
 *
 * @author xingxiaohuan<br>
 * @version riil.multilevel.action v1.0 <p> <br> <strong>Modify History:</strong><br> user
 *          modify_date modify_content<br> -------------------------------------------<br> <br>
 */
public class JxlsFactory {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory
                                    .getLogger(JxlsFactory.class);
    /**
     * 根目录
     */

    public static final String S_PATH_ROOT =  System.getProperty("user.dir")+File.separator + "src"+File.separator+"main"+File.separator+"resources" +File.separator;

    //  部署目录
    // public static final String S_PATH_ROOT=  new File("/").getAbsolutePath()+"FGW"+File.separator+"conf"+File.separator;
    /**
     * 模板目录
     */
    public static  String S_PATH_SRC =S_PATH_ROOT+ "template" + File.separator;
    /**
     * 报表目录
     */
    public static final String S_PATH_DEST = S_PATH_ROOT+"result" + File.separator;

    /**
     * 报表视图目录
     */
    public static final String S_PATH_REPORT_VIEW = "view" + File.separator;
    /**
     * 计数器
     */
    private static long m_counter = 0;
    /**
     * singleton
     */
    private static final JxlsFactory m_foctory = new JxlsFactory();
    /**
     * 线程局部变量
     */
    private ThreadLocal<Jxls> m_transformerThreadLocal = new ThreadLocal<Jxls>();

    /**
     * 创建Jxls对象
     *
     * @return 成功返回Jxls，失败返回 null
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
     * 构造函数
     */
    private JxlsFactory() {
    }

    /**
     * 获得Jxls对象
     *
     * @param templateFileName 源文件名。输出文件名自动计算
     * @param beans            报表所需的数据
     * @return 成功返回Jxls，失败返回 null
     */
    public static Jxls getTransformer(final String templateFileName,
                                    final Map<String, Object> beans) {
        if (templateFileName == null || templateFileName.equals("")
                                        || templateFileName.length() < 8) {
            logger.error("获取Jxls对象时出错:模板文件名不能为空,并且文件名长度必须大于三位");
            return null;
        }
        if (!templateFileName.endsWith(".xls")) {
            logger.error("获取Jxls对象时出错:模板应该是xls文件");
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
