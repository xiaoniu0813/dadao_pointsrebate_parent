package com.dadao.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by yangrui on 2017/7/13.
 */
public class QueryResult {

    //当前页
    private Integer pageNum;

    //总记录数
    private Long totalSize;

    //总页数
    private Long totalPage;

    //查询到的对象集合
    private List list;

    public Integer getPageNum() {
        return pageNum==null?0:pageNum;
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
}
