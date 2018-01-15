package com.dadao.push.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author YunQiang
 */
public class InformationPO {
    /**
     *无意义代理主键
     */
    private Long infoId;

    /**
     *标题
     */
    private String title;

    /**
     * H5 url
     */
    private String notifyUrl;

    /**
     * 图片
     */
    private String image;

    /**
     *内容
     */
    private String content;

    /**
     *用户ID
     */
    private Long userId;

    /**
     *是否已读（0未读，1已读）
     */
    private Integer haveRead;

    /**
     *状态（0付款、1收款、2申请退款、3退款成功、4系统消息、5返利、6活动、7优惠、8广告）
     */
    private Integer status;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     *对象ID
     */
    private Long objectId;

    public InformationPO() {
    }

    public InformationPO(String title, String notifyUrl, String image, String content, Long userId, Integer haveRead, Integer status, Long objectId) {
        this.title = title;
        this.notifyUrl = notifyUrl;
        this.image = image;
        this.content = content;
        this.userId = userId;
        this.haveRead = haveRead;
        this.status = status;
        this.objectId = objectId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getHaveRead() {
        return haveRead;
    }

    public void setHaveRead(Integer haveRead) {
        this.haveRead = haveRead;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }
}