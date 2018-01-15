package com.dadao.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YunQiang on 2017/8/3
 */
public class PageNew {
    private Integer currentPageNum = 1;// 当前第几页(默认第一页),---主要用于传递到前台显示
    private Integer totalPageNum;// 总页数
    private Integer totalCount;// 总记录数
    private Integer perPageSize = 5;// 每页显示的记录条数(默认5条)

    private List entitys = new ArrayList();// 记录当前页中的数据条目

    public PageNew() {

    }


    // 所有参数都进行修改

    public PageNew(Integer currentPageNum, Integer totalCount, Integer perPageSize,
                List entitys) {
        this.totalCount = totalCount;
        this.perPageSize = perPageSize;
        this.totalPageNum = totalCount % perPageSize == 0 ? totalCount
                / perPageSize : totalCount / perPageSize + 1;
        this.entitys = entitys;
        this.currentPageNum = currentPageNum<1?1:(currentPageNum>totalPageNum?totalPageNum:currentPageNum);//如果当前页小于第一页，则停留在第一页
    }

    // 使用默认的当前页和每页显示记录条数
    public PageNew( Integer totalCount, List entitys) {
        this.totalCount = totalCount;
        this.totalPageNum = totalCount % perPageSize == 0 ? totalCount
                / perPageSize : totalCount / perPageSize + 1;
        this.entitys = entitys;
        this.currentPageNum = currentPageNum<1?1:(currentPageNum>totalPageNum?totalPageNum:currentPageNum);//如果当前页小于第一页，则停留在第一页
    }

    public Integer getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(Integer currentPageNum) {
        this.currentPageNum = currentPageNum<1?1:(currentPageNum>totalPageNum?totalPageNum:currentPageNum);//如果当前页小于第一页，则停留在第一页
    }

    public Integer getTotalPageNum() {
        return  totalCount % perPageSize == 0 ? totalCount / perPageSize : totalCount / perPageSize + 1;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPerPageSize() {
        return perPageSize;
    }

    public void setPerPageSize(Integer perPageSize) {
        this.perPageSize = perPageSize;
    }

    public List getEntitys() {
        return entitys;
    }

    public void setEntitys(List entitys) {
        this.entitys = entitys;
    }

    public Integer getStartIndex(){
       return (this.getCurrentPageNum() - 1) * this.getPerPageSize();
    }

}
