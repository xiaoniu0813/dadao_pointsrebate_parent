package com.dadao.refunds.service;

import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.mapper.entity.RefundsMessage;
import com.dadao.utils.Result;

public interface RefundsApplicationService {
    /**
     * 根据orderId查询出退款信息
     * @param orderId
     * @return
     */
    RefundsApplicationPO findByRefundsInfo(long orderId);


    /**
     *用户退款申请列表
     * @param refundsMessage 退款专用实体类
     * @param pageNum 起始页
     * @param pageSize 页面大小
     * @return
     */
    Result findByConditions(RefundsMessage refundsMessage, Long pageNum, int pageSize);

}
