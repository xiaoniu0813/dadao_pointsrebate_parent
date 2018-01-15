package com.dadao.trade.mapper;

import com.dadao.capitalpool.entity.CapitalpoolPO;
import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.order.entity.OrderPO;
import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserTransactionRecodPO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * Created by YunQiang on 2017/11/24
 * @author YunQiang
 * @description 处理退积分、退款等
 */
public interface TradeMapper {

    /**
     * 消减用户对应市场等级的积分
     * @param marketId 积分所在的市场id
     * @param userId 用户userId
     * @param minusIntegral 减少积分额度
     * @return
     */
    int minusUserIntegral(@Param("marketId") Long marketId, @Param("userId")Long userId, @Param("minusIntegral")BigDecimal minusIntegral);

    /**
     * 产生一条积分流水记录
     * @param userIntegralRecordingPO 积分流水表对应实体类
     * @return
     */
    int createUserIntegralRecording(UserIntegralRecordingPO userIntegralRecordingPO);

    /**
     * 消减商户钱包
     * @param userId 商户的userId
     * @param minusMoney 消减金额大小
     * @return
     */
    int minusUserWallet(@Param("userId") Long userId, @Param("minusMoney") BigDecimal minusMoney);

    /**
     * 并产生一条交易流水记录
     * @param userTransactionRecodPO
     * @return
     */
    int createUserTransactionRecod(UserTransactionRecodPO userTransactionRecodPO);

    /**
     *消减资金池余额
     * @param capitalpoolPO 资金池表对应实体类
     * @return
     */
    int  minusCapitalpool(CapitalpoolPO capitalpoolPO);

    /**
     * 并产生一条资金池流水记录
     * @param capitalpoolRecordPO 资金池流水记录
     * @return
     */
    int createCapitalpoolRecord(CapitalpoolRecordPO capitalpoolRecordPO);

    /**
     * 找到积分表id
     * @param marketId 市场id
     * @param userId 用户id
     * @return
     */
    Long findIntegralId(@Param("marketId") Long marketId, @Param("userId") Long userId);

    /**
     * 根据订单表id，查到该订单的积分信息
     * @param orderId 订单表id
     * @return
     */
    UserIntegralRecordingPO findUserIntegralRecordingByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据第三方交易流水号，找到钱包流水记录
     * @param serialNumber 第三方流水号
     * @return
     */
    UserTransactionRecodPO selectUserTransactionRecodById(String serialNumber);

    /**
     * 根据id查询订单详情，退款用
     * @param id 订单表主键
     * @return
     */
    OrderPO selectOrderById(Long id);

    /**
     * 修改订单
     * @param orderPO 订单表实体类
     * @return
     */
    int updateOrderById(OrderPO orderPO);

    /**
     * 通过token查询子商户号
     * @param token
     * @return
     */
    String findMerchantNoByToken(String token);

    /**
     * 通过订单id修改退款表
     * @param refundsApplicationPO
     * @return
     */
    int updateRefundsApplicationById(RefundsApplicationPO refundsApplicationPO);

}
