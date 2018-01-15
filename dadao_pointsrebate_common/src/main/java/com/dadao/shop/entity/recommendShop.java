package com.dadao.shop.entity;

/**
 * 推荐店铺表
 *
 * @auther NFY niufuyang
 * @create 2017-08-14截止
 */
public class recommendShop {
    private Long id;//bigint(20) NOT NULL COMMENT '无意义代理主键',
    private Long shopId;//bigint(20) NOT NULL COMMENT '商铺ID',
    private Integer sequence;//int(11) NOT NULL DEFAULT '0' COMMENT '排序',
    private String createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private String endTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '推荐截止日期',

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "recommendShop{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", sequence=" + sequence +
                ", createTime='" + createTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
