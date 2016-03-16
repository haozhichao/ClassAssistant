package com.its.db.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_classroom")
public class ClassRoom {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 邀请码
     */
    @Column(name = "c_uuid")
    private String uuid;

    /**
     * 课堂名称
     */
    @Column(name = "c_name")
    private String name;

    /**
     * 课堂描述
     */
    @Column(name = "c_description")
    private String description;

    /**
     * 课堂人数
     */
    @Column(name = "c_stuNum")
    private Integer stunum;

    /**
     * 教师id
     */
    @Column(name = "c_teacherId")
    private Integer teacherid;

    /**
     * 创建者姓名
     */
    @Column(name = "c_createrName")
    private String creatername;

    /**
     * 创建时间
     */
    @Column(name = "c_createrDate")
    private Date createrdate;

    /**
     * 删除标记
     */
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
     * 获取邀请码
     *
     * @return c_uuid - 邀请码
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置邀请码
     *
     * @param uuid 邀请码
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取课堂名称
     *
     * @return c_name - 课堂名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置课堂名称
     *
     * @param name 课堂名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取课堂描述
     *
     * @return c_description - 课堂描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置课堂描述
     *
     * @param description 课堂描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取课堂人数
     *
     * @return c_stuNum - 课堂人数
     */
    public Integer getStunum() {
        return stunum;
    }

    /**
     * 设置课堂人数
     *
     * @param stunum 课堂人数
     */
    public void setStunum(Integer stunum) {
        this.stunum = stunum;
    }

    /**
     * 获取教师id
     *
     * @return c_teacherId - 教师id
     */
    public Integer getTeacherid() {
        return teacherid;
    }

    /**
     * 设置教师id
     *
     * @param teacherid 教师id
     */
    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * 获取创建者姓名
     *
     * @return c_createrName - 创建者姓名
     */
    public String getCreatername() {
        return creatername;
    }

    /**
     * 设置创建者姓名
     *
     * @param creatername 创建者姓名
     */
    public void setCreatername(String creatername) {
        this.creatername = creatername;
    }

    /**
     * 获取创建时间
     *
     * @return c_createrDate - 创建时间
     */
    public Date getCreaterdate() {
        return createrdate;
    }

    /**
     * 设置创建时间
     *
     * @param createrdate 创建时间
     */
    public void setCreaterdate(Date createrdate) {
        this.createrdate = createrdate;
    }

    /**
     * 获取删除标记
     *
     * @return c_del - 删除标记
     */
    public Boolean getDel() {
        return del;
    }

    /**
     * 设置删除标记
     *
     * @param del 删除标记
     */
    public void setDel(Boolean del) {
        this.del = del;
    }
}