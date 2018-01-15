package com.dadao.trade.service;

import com.dadao.utils.Result;

/**
 * Created by YunQiang on 2017/11/21
 * @author YunQiang
 * @description 交易服务
 */
public interface TradeService {

    /**
     * 单笔退款
     * @param ids 根据订单id退款
     * @param status 3审核通过、4审核未通过
     * @param platformDescription 平台审核意见
     * @return 请求退款结果
     */
    Result refund(int status, String platformDescription,Long ...ids);

    /**
     * 单笔退款查询
     * @param id 根据订单id退款
     * @return 请求退款结果
     */
    Result refundQuery(Long id);

    /**
     * 退款结果保存
     * @param response 易宝回调通知
     * @return
     */
    String refundResult(String response);
}
