package com.its.db.pojo;

import javax.persistence.*;

@Table(name = "t_classroom_relation_student")
public class ClassRelationStudent {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

     /**
     * 课堂id
     */
    @Column(name = "c_classroomId")
    private Integer classroomid;

    /**
     * 学生id
     */
    @Column(name = "c_studentId")
    private Integer studentid;

    /**
     * 学生名片
     */
    @Column(name = "c_stuCard")
    private String stucard;

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
     * 获取学生id
     *
     * @return c_studentId - 学生id
     */
    public Integer getStudentid() {
        return studentid;
    }

    /**
     * 设置学生id
     *
     * @param studentid 学生id
     */
    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    /**
     * 获取学生名片
     *
     * @return c_stuCard - 学生名片
     */
    public String getStucard() {
        return stucard;
    }

    /**
     * 设置学生名片
     *
     * @param stucard 学生名片
     */
    public void setStucard(String stucard) {
        this.stucard = stucard;
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
