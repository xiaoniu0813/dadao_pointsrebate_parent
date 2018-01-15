package com.dadao.push.service;


import com.dadao.push.entity.InformationPO;

/**
 * @author YunQiang
 */
public interface IPushService {

    /**
     * 向所有用户推送消息
     * @param message 信息内容
     * @param information 需要保存到数据库的信息
     */
    void pushForAllConsumer(String message, InformationPO information);

    /**
     * 向所有商户推送消息
     * @param message 信息内容
     * @param information 需要保存到数据库的信息
     */
    void pushForAllBusiness(String message, InformationPO information);

    /**
     * 向特定用户推送消息
     * @param message 信息内容
     * @param information 需要保存到数据库的信息
     */
    void pushForConsumer(String message, InformationPO information);

    /**
     * 向特定商户推送消息
     * @param message 信息内容
     * @param information 需要保存到数据库的信息
     */
    void pushForBusiness(String message, InformationPO information);

}
