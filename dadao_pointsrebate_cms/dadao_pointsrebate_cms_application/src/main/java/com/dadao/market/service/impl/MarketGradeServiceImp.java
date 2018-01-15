package com.dadao.market.service.impl;

import com.dadao.market.activity.MarketGradeActivity;
import com.dadao.market.entity.MarketGradePO;
import com.dadao.market.service.MarketGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketGradeServiceImp implements MarketGradeService {
    @Autowired
    private MarketGradeActivity marketGradeActivity;

    public int save(MarketGradePO marketGradePO) {
        return marketGradeActivity.save(marketGradePO);
    }

    public MarketGradePO findMarketGradeByGrade(int id) {
        return marketGradeActivity.findMarketGradePCByGrade(id);
    }
    public int update(MarketGradePO marketGradePO){
        return marketGradeActivity.update(marketGradePO);
    }
}
