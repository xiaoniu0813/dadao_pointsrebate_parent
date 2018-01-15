package com.dadao.util;

/**
 * Created by yangrui on 2017/7/16.
 */
public class Page {

    /*起始索引*/
    private Integer beginIndex = 0;

    /*每页显示最大记录数，默认显示10条*/
    private Integer pageSize = 10;

    /*排序字段*/
    private String sort;

    /*默认降序*/
    private String order = "desc";

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
