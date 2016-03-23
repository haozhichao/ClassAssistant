package com.its.controller.vo;

import java.util.List;

/**
 * Created by haozhichao on 2016/3/22 0022.
 */
public class PageVo<T> {
    private int totalRecord = 0;
    private int pageSize = 15;
    private int pageIndex = 1;
    private int totalPage = 0;
    private List<T> data = null;

    public PageVo() {
    }

    public PageVo(List<T> data) {
        this.data = data;
    }

    public PageVo(int totalRecord, int totalPage, int pageSize, int pageIndex) {
        this.setTotalRecord(totalRecord);
        this.setPageSize(pageSize);
        this.setPageIndex(pageIndex);
        this.setTotalPage(totalPage);
    }

    public PageVo(int totalRecord, int totalPage, int pageSize, int pageIndex, List<T> data) {
        this.setTotalRecord(totalRecord);
        this.setPageSize(pageSize);
        this.setPageIndex(pageIndex);
        this.setTotalPage(totalPage);
        this.data = data;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        if(totalRecord <= 0) {
            this.totalRecord = 0;
        } else {
            this.totalRecord = totalRecord;
        }

    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize <= 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }

    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        if(pageIndex <= 1) {
            this.pageIndex = 1;
        } else {
            this.pageIndex = pageIndex;
        }

    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
