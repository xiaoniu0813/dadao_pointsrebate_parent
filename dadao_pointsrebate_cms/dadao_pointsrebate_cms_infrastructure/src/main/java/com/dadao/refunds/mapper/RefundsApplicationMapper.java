package com.dadao.refunds.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.refunds.mapper.entity.RefundsMessage;

import java.util.List;

/**
 * @author YunQiang
 */
public interface RefundsApplicationMapper extends BaseMapper{

    /**
     * 查找用户退款信息
     * @param refundsMessage 退款专用实体类
     * @return
     */
    List<RefundsMessage> findByConditions(RefundsMessage refundsMessage);

    /**
     * 用户退款信息总条数
     * @param refundsMessage 退款专用实体类
     * @return
     */
    long findByConditionsCount(RefundsMessage refundsMessage);



}
