package com.dadao.dto;

/**
 * @author YunQiang
 */
public class MessageUnread {

    /**
     * 系统消息
     */
    private long systemMessageUnread;

    /**
     * 交易消息
     */
    private long tradeMessageUnread;

    /**
     * 活动消息
     */
    private long promotionMessageUnread;

    public MessageUnread() {
    }

    public MessageUnread(long systemMessageUnread, long tradeMessageUnread, long promotionMessageUnread) {
        this.systemMessageUnread = systemMessageUnread;
        this.tradeMessageUnread = tradeMessageUnread;
        this.promotionMessageUnread = promotionMessageUnread;
    }

    public long getSystemMessageUnread() {
        return systemMessageUnread;
    }

    public void setSystemMessageUnread(long systemMessageUnread) {
        this.systemMessageUnread = systemMessageUnread;
    }

    public long getTradeMessageUnread() {
        return tradeMessageUnread;
    }

    public void setTradeMessageUnread(long tradeMessageUnread) {
        this.tradeMessageUnread = tradeMessageUnread;
    }

    public long getPromotionMessageUnread() {
        return promotionMessageUnread;
    }

    public void setPromotionMessageUnread(long promotionMessageUnread) {
        this.promotionMessageUnread = promotionMessageUnread;
    }
}
