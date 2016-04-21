package com.its.db.pojo;

import java.io.File;

/**
 * 上传封装的实体
 * Created by haozhichao on 2016/4/20 0020.
 */
public class UploadFile {
    private String saveDirectory; // 保存目录
    private String fileName;      // 文件名
    private String contentType;   // 文件类型
    private String prePath;
    private String completeSavePath; // 完整保存路径
    private String relativeSavePath; // 相对保存路径

    public UploadFile(String saveDirectory, String filesystemName) {
        this.saveDirectory = saveDirectory;
        this.fileName = filesystemName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSaveDirectory() {
        return saveDirectory;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPrePath() {
        if (prePath == null) {
            return "";
        }
        return prePath;
    }

    public void setPrePath(String prePath) {
        this.prePath = prePath;
        setCompleteSavePath(prePath + getRelativeSavePath());
    }

    public String getCompleteSavePath() {
        return completeSavePath;
    }

    public void setCompleteSavePath(String completeSavePath) {
        this.completeSavePath = completeSavePath;
    }

    public String getRelativeSavePath() {
        return relativeSavePath;
    }

    public void setRelativeSavePath(String relativeSavePath) {
        this.relativeSavePath = relativeSavePath;
    }

    public void setSaveDirectory(String saveDirectory) {
        this.saveDirectory = saveDirectory;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        if (getSaveDirectory() == null || getFileName() == null) {
            return null;
        } else {
            setRelativeSavePath(getFileName());
            return new File(getSaveDirectory() + "/" + getFileName());
        }
    }
}
