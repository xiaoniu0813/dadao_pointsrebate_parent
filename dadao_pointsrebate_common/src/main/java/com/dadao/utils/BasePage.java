package com.dadao.utils;

import java.util.List;

public class BasePage {

    //当前页
    private Integer pageNum;

    //总记录数
    private Long totalSize;

    //总页数
    private Long totalPage;

    //查询到的对象集合
    private List list;

    //每页的大小
    private Integer pageSize;

    //查询从第几条起查询
    private Long beginIndex;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Long beginIndex) {
        this.beginIndex = beginIndex;
    }

    @Override
    public String toString() {
        return "BasePage{" +
                "pageNum=" + pageNum +
                ", totalSize=" + totalSize +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", pageSize=" + pageSize +
                ", beginIndex=" + beginIndex +
                '}';
    }
}
