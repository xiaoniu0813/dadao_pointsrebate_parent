package com.dadao.cashplan.mapper;

import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashplan.entity.TCapitalpoolAvailable;
import com.dadao.cashplan.entity.TUserEffectiveIntegral;
import com.dadao.pub.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by YunQiang on 2017/9/13
 */
public interface ITCashMapper extends BaseMapper{

    /**
     * 1、插入返现记录
     * @param cashbackRecordPO
     * @return
     */
    int insertCashbackRecord(CashbackRecordPO cashbackRecordPO);

    /**
     * 2、根据marketId得到市场等级
     * @param marketId
     * @return
     */
    Integer findGradeByMarketId(@Param("marketId") Long marketId);

    /**
     *3、根据市场等级查询当前等级市场最近返利时间
     * @param grade 市场等级
     * @return
     */
    Date findMaxCashbackSpecificDateByGrade(@Param("grade") Integer grade);

    /**
     * 找到可能达到积分上限所有用户userId集合,按用户当前积分倒叙排列
     * @param currentIntegralUpper 当前积分上限
     * @return
     */
    List<Long> findAllUserId(@Param("marketId") Long marketId, @Param("currentIntegralUpper") Integer currentIntegralUpper);

    /**
     * 4、查询一个用户的有效积分
     * @param marketId 市场id
     * @param userId 用户Id
     * @param expirationDate 截止日期 = 具体返利日期cashbackSpecificDate - 7天
     * @return
     */
    TUserEffectiveIntegral findEffectiveIntegralByUserId(@Param("marketId") long marketId, @Param("userId") long userId, @Param("expirationDate") Date expirationDate);

    /**
     * 5、查询资金池可用余额
     * @param marketId 市场id
     * @param expirationDate 截止日期 = 具体返利日期cashbackSpecificDate - 7天
     * @return
     */
    TCapitalpoolAvailable findCapitalpoolAvailable(@Param("marketId") long marketId, @Param("expirationDate") Date expirationDate);


    /**
     * 6、根据积分上限查询用户积分列表
     * @param grade
     * @param integral
     * @param cashbackSpecificDate
     * @return
     */
    List<TUserEffectiveIntegral> findEffectiveIntegral(@Param("grade") int grade, @Param("integral") int integral, @Param("cashbackSpecificDate") Date cashbackSpecificDate);

    /**
     * 7、根据市场等级和积分上限查询用户有效积分和达到积分上限用户
     * @param marketId
     * @param integralUpper
     * @return
     */
    List<TUserEffectiveIntegral> findEffectiveIntegrals(@Param("marketId") int marketId, @Param("integralUpper") int integralUpper,@Param("last") String last);

    //----------商户返利开始----------

    /**
     * 01、查询商户最近的一次返利
     * @return
     */
    Date findMaxBusinessCashbackSpecificDate(@Param("marketId") Long marketId);

}
