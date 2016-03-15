package com.its.db.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_notice")
public class Notice {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "c_title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "c_content")
    private String content;

    /**
     * 发布时间
     */
    @Column(name = "c_releaseDate")
    private Date releasedate;

    /**
     * 发布人（教师）
     */
    @Column(name = "c_teacherName")
    private String teachername;

    /**
     * 课堂id
     */
    @Column(name = "c_classroomId")
    private Integer classroomid;

    @Column(name = "c_del")
    private Boolean del;

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
     * 获取标题
     *
     * @return c_title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容
     *
     * @return c_content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取发布时间
     *
     * @return c_releaseDate - 发布时间
     */
    public Date getReleasedate() {
        return releasedate;
    }

    /**
     * 设置发布时间
     *
     * @param releasedate 发布时间
     */
    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    /**
     * 获取发布人（教师）
     *
     * @return c_teacherName - 发布人（教师）
     */
    public String getTeachername() {
        return teachername;
    }

    /**
     * 设置发布人（教师）
     *
     * @param teachername 发布人（教师）
     */
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    /**
     * 获取课堂id
     *
     * @return c_classroomId - 课堂id
     */
    public Integer getClassroomid() {
        return classroomid;
    }

    /**
     * 设置课堂id
     *
     * @param classroomid 课堂id
     */
    public void setClassroomid(Integer classroomid) {
        this.classroomid = classroomid;
    }

    /**
     * @return c_del
     */
    public Boolean getDel() {
        return del;
    }

    /**
     * @param del
     */
    public void setDel(Boolean del) {
        this.del = del;
    }
}