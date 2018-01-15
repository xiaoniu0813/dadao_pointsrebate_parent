package com.dadao.push.entity;

import java.util.List;

/**
 * @author YunQiang
 */
public class MessageCountPO {
    /**
     * userId
     */
    private long userId;

    /**
     * 状态（0付款、1收款、2申请退款、3退款成功、4系统消息、5返利、6活动、7优惠、8广告）
     * 用法status="0,1,2"
     */
    private List<Integer> statusList;

    /**
     * 是否已读（0未读，1已读）
     */
    private int haveRead;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public int getHaveRead() {
        return haveRead;
    }

    public void setHaveRead(int haveRead) {
        this.haveRead = haveRead;
    }
}
