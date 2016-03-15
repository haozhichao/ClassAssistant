package com.its.db.pojo;

import javax.persistence.*;

@Table(name = "t_studyresource")
public class StudyResource {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文件名
     */
    @Column(name = "c_fileName")
    private String filename;

    /**
     * 文件描述
     */
    @Column(name = "c_description")
    private String description;

    /**
     * 上传路径
     */
    @Column(name = "c_path")
    private String path;

    /**
     * 上传者名字
     */
    @Column(name = "c_uploaderName")
    private String uploadername;

    /**
     * 上传者id
     */
    @Column(name = "c_uploaderId")
    private Integer uploaderid;

    /**
     * 上传者类型
     */
    @Column(name = "c_uploaderType")
    private Integer uploadertype;

    /**
     * 下载次数
     */
    @Column(name = "c_downloadNum")
    private Integer downloadnum;

    /**
     * 赞次数
     */
    @Column(name = "c_zan")
    private Integer zan;

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
     * 获取文件名
     *
     * @return c_fileName - 文件名
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 设置文件名
     *
     * @param filename 文件名
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 获取文件描述
     *
     * @return c_description - 文件描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置文件描述
     *
     * @param description 文件描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取上传路径
     *
     * @return c_path - 上传路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置上传路径
     *
     * @param path 上传路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取上传者名字
     *
     * @return c_uploaderName - 上传者名字
     */
    public String getUploadername() {
        return uploadername;
    }

    /**
     * 设置上传者名字
     *
     * @param uploadername 上传者名字
     */
    public void setUploadername(String uploadername) {
        this.uploadername = uploadername;
    }

    /**
     * 获取上传者id
     *
     * @return c_uploaderId - 上传者id
     */
    public Integer getUploaderid() {
        return uploaderid;
    }

    /**
     * 设置上传者id
     *
     * @param uploaderid 上传者id
     */
    public void setUploaderid(Integer uploaderid) {
        this.uploaderid = uploaderid;
    }

    /**
     * 获取上传者类型
     *
     * @return c_uploaderType - 上传者类型
     */
    public Integer getUploadertype() {
        return uploadertype;
    }

    /**
     * 设置上传者类型
     *
     * @param uploadertype 上传者类型
     */
    public void setUploadertype(Integer uploadertype) {
        this.uploadertype = uploadertype;
    }

    /**
     * 获取下载次数
     *
     * @return c_downloadNum - 下载次数
     */
    public Integer getDownloadnum() {
        return downloadnum;
    }

    /**
     * 设置下载次数
     *
     * @param downloadnum 下载次数
     */
    public void setDownloadnum(Integer downloadnum) {
        this.downloadnum = downloadnum;
    }

    /**
     * 获取赞次数
     *
     * @return c_zan - 赞次数
     */
    public Integer getZan() {
        return zan;
    }

    /**
     * 设置赞次数
     *
     * @param zan 赞次数
     */
    public void setZan(Integer zan) {
        this.zan = zan;
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