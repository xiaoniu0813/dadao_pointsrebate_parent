package com.dadao.cashback.entity;

import com.dadao.utils.Page;

/**
 * 扫描返现日志表
 *
 * @auther NFY niufuyang
 * @create 2017-9-9
 */
public class ScanningCashbackLogVO extends Page {
    private Long logId;// bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private String startTime;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    private String endTime;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
    private String description;// varchar(220) NOT NULL DEFAULT '0' COMMENT '描述',

    public Long getLogId() {
        return logId==null?0:logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getStartTime() {
        return startTime==null?"":startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime==null?"":endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description==null?"":description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ScanningCashbackLogPO{" +
                "logId=" + logId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
