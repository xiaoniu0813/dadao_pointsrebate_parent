package com.dadao.market.activity;

import com.dadao.market.entity.MarketGradePO;
import com.dadao.market.mapper.MarketGradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MarketGradeActivity {

    @Autowired
    private MarketGradeMapper marketGradeMapper;

    /**
     * 添加市场等级
     * @param marketGradePO
     * @return
     */
    public int save(MarketGradePO marketGradePO) {
        int a = 0;
        if (marketGradeMapper.findById(marketGradePO.getGrade()) == null) {
            if (marketGradePO.getMarketId() == null) {

                marketGradePO.setMarketId((long)0);
            }
            a = marketGradeMapper.save(marketGradePO);
        }
        return a;
    }

    /**
     * 查找市场等级
     * @param id
     * @return
     */
    public MarketGradePO findMarketGradePCByGrade(int id) {
        return (MarketGradePO) marketGradeMapper.findById(id);
    }

    /**
     * 修改市场等级
     * @param marketGrade
     * @return
     */
    public int update(MarketGradePO marketGrade){
        int a = marketGradeMapper.update(marketGrade);
        return a;
    }
}
