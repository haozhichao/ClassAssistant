package com.its.db.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_comments")
public class Comments {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论内容
     */
    @Column(name = "c_content")
    private String content;

    /**
     * 评论人类型
     */
    @Column(name = "c_comPerType")
    private Integer compertype;

    /**
     * 评论人id
     */
    @Column(name = "c_comPerId")
    private Integer comperid;

    /**
     * 评论人名字
     */
    @Column(name = "c_comPerName")
    private String compername;

    /**
     * 评论时间
     */
    @Column(name = "c_comPerDate")
    private Date comperdate;

    /**
     * 学习资源id
     */
    @Column(name = "c_studyResourceId")
    private Integer studyresourceid;

    /**
     * 评论最新时间
     */
    @Column(name = "c_comPerLastDate")
    private Date comperlastdate;

    @Column(name = "c_del")
    private Byte del;

    /**
     * @return c_id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取评论内容
     *
     * @return c_content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评论人类型
     *
     * @return c_comPerType - 评论人类型
     */
    public Integer getCompertype() {
        return compertype;
    }

    /**
     * 设置评论人类型
     *
     * @param compertype 评论人类型
     */
    public void setCompertype(Integer compertype) {
        this.compertype = compertype;
    }

    /**
     * 获取评论人id
     *
     * @return c_comPerId - 评论人id
     */
    public Integer getComperid() {
        return comperid;
    }

    /**
     * 设置评论人id
     *
     * @param comperid 评论人id
     */
    public void setComperid(Integer comperid) {
        this.comperid = comperid;
    }

    /**
     * 获取评论人名字
     *
     * @return c_comPerName - 评论人名字
     */
    public String getCompername() {
        return compername;
    }

    /**
     * 设置评论人名字
     *
     * @param compername 评论人名字
     */
    public void setCompername(String compername) {
        this.compername = compername;
    }

    /**
     * 获取评论时间
     *
     * @return c_comPerDate - 评论时间
     */
    public Date getComperdate() {
        return comperdate;
    }

    /**
     * 设置评论时间
     *
     * @param comperdate 评论时间
     */
    public void setComperdate(Date comperdate) {
        this.comperdate = comperdate;
    }

    /**
     * 获取学习资源id
     *
     * @return c_studyResourceId - 学习资源id
     */
    public Integer getStudyresourceid() {
        return studyresourceid;
    }

    /**
     * 设置学习资源id
     *
     * @param studyresourceid 学习资源id
     */
    public void setStudyresourceid(Integer studyresourceid) {
        this.studyresourceid = studyresourceid;
    }

    /**
     * 获取评论最新时间
     *
     * @return c_comPerLastDate - 评论最新时间
     */
    public Date getComperlastdate() {
        return comperlastdate;
    }

    /**
     * 设置评论最新时间
     *
     * @param comperlastdate 评论最新时间
     */
    public void setComperlastdate(Date comperlastdate) {
        this.comperlastdate = comperlastdate;
    }

    /**
     * @return c_del
     */
    public Byte getDel() {
        return del;
    }

    /**
     * @param del
     */
    public void setDel(Byte del) {
        this.del = del;
    }
}