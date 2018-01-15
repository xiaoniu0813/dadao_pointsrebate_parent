package com.dadao.feedback.entity;

import com.dadao.utils.Page;

import java.util.Date;

/**
 * @auther NFY niufuyang
 * @create 2018-1-10
 */
public class FeedbackVO extends Page {
    private Long id;//` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private String content;// text COMMENT '内容',
    private Long userId;// bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户id',
    private String treatDescription;// text COMMENT '处理说明',
    private Long treatUserId;//` bigint(20) NOT NULL DEFAULT '-1' COMMENT '处理用户ID',
    private Integer status;//` int(2) NOT NULL DEFAULT '0' COMMENT '状态：0未处理，1已处理',
    private Date createTime;//` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private Date treatTime;//` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '处理时间',
    private String title;//用户选择标题

    public String getTitle() {
        return title==null?"":title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content==null?"":content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId==null?0:userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTreatDescription() {
        return treatDescription==null?"":treatDescription;
    }

    public void setTreatDescription(String treatDescription) {
        this.treatDescription = treatDescription;
    }

    public Long getTreatUserId() {
        return treatUserId==null?0:treatUserId;
    }

    public void setTreatUserId(Long treatUserId) {
        this.treatUserId = treatUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTreatTime() {
        return treatTime;
    }

    public void setTreatTime(Date treatTime) {
        this.treatTime = treatTime;
    }

    @Override
    public String toString() {
        return "FeedbackVO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", treatDescription='" + treatDescription + '\'' +
                ", treatUserId=" + treatUserId +
                ", status=" + status +
                ", createTime=" + createTime +
                ", treatTime=" + treatTime +
                ", title='" + title + '\'' +
                '}';
    }
}
