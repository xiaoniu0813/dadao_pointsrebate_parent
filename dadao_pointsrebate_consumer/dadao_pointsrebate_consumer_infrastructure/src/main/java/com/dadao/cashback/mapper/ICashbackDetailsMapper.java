package com.dadao.cashback.mapper;

import com.dadao.cashback.entity.CashbackDetailsPO;
import com.dadao.cashback.entity.CashbackDetailsVO;
import com.dadao.pub.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICashbackDetailsMapper extends BaseMapper{

    /**
     * 返利记录
     * @param hashMap
     * @return
     */
    public List<Map> findByStatus(HashMap hashMap);

    /**
     * 返利记录的count,用于分页
     * @param hashMap
     * @return
     */
    public Integer findStatusCount(HashMap hashMap);

    /**
     * 累计返利金额
     * @return
     */
    public BigDecimal countReally(HashMap hashMap);

    /**
     * 待返利笔数
     * @return
     */
    public Integer countWaitCashback(HashMap hashMap);
}
