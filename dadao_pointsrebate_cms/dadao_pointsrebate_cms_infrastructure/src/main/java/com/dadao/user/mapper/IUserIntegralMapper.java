package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.BusinessCashIntegral;
import com.dadao.user.entity.UserIntegral;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by NFY on 2017-8-24.
 */
public interface IUserIntegralMapper extends BaseMapper {

    Integer findCountByStandardNum(UserIntegral userIntegral);

    UserIntegral findUserIntegralByMarketId(UserIntegral userIntegral);

    /**
     * 查询商户积分，用于给商户返利
     * @param beginIndex    limit下标
     * @param pageSize  limit下标
     * @param currentIntegralUpper  当前返利积分上限
     * @return  商户带返利列表集合
     */
    List<BusinessCashIntegral> findBusinessesIntegral(@Param("marketId") Long marketId, @Param("currentIntegralUpper") BigDecimal currentIntegralUpper, @Param("beginIndex") Integer beginIndex, @Param("pageSize") Integer pageSize);
    long findBusinessesIntegralCount(@Param("marketId") Long marketId, @Param("currentIntegralUpper") BigDecimal currentIntegralUpper);

    /**
     * 根据id查询商户积分
     * @param id    商户积分钱包id
     * @return  商户返利积分信息
     */
    BusinessCashIntegral findIntegralById(@Param("id") Long id);

    /**
     * 消減用户积分钱包积分
     * @param id    用户钱包id
     * @param integral  消减多少积分
     * @return  消减记录数
     */
    int minusIntegralById(@Param("id")Long id, @Param("integral")BigDecimal integral);
}
