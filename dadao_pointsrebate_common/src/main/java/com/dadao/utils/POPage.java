package com.dadao.utils;
import java.util.List;

/**
 * Created by GUOYU on 2017-08-07.
 */
public class POPage {

    //sql索引
    private Integer beginIndex;
    //每页的大小
    private Integer pageSize;
    //第几页
    private Integer pageNum;
    //查询开始时间
    private String startTime;
    //查询结束时间
    private String endTime;
    //总页数
    private Long totalPage;
    //总条数
    private Long count;
    //存放数据集合
    private List list;

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

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

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "POPage{" +
                "beginIndex=" + beginIndex +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", totalPage=" + totalPage +
                ", count=" + count +
                ", list=" + list +
                '}';
    }
}
