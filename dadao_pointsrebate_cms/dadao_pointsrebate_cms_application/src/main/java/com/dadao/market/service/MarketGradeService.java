package com.dadao.market.service;

import com.dadao.market.entity.MarketGradePO;

/**
 * Created by GUOYU on 2017/7/25.
 */
public interface MarketGradeService {

    /**
     * 添加市场
     * @param marketGradePO
     * @return
     */
    public int save(MarketGradePO marketGradePO);

    public MarketGradePO findMarketGradeByGrade(int id);
    public int update(MarketGradePO marketGradePO);

}
