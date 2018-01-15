package com.dadao.shop.service.impl;


import com.dadao.shop.activity.RecommendPositionActivity;
import com.dadao.shop.service.RecommendPositionService;
import com.dadao.shop.entity.RecommendPosition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by yunqiang1 on 2017/7/19.
 */
@Service
public class RecommendPositionServiceImpl implements RecommendPositionService {

    @Autowired
    private RecommendPositionActivity recommendActivity;

    public int addRecommend(RecommendPosition recommend) {
        return recommendActivity.addRecommend(recommend);
    }

    public int findTotalRecommend(RecommendPosition recommendPosition) {
        return recommendActivity.totalRecommend(recommendPosition);
    }

    public List findByEntity(RecommendPosition recommendPosition){return recommendActivity.findByEntity(recommendPosition);}

    public Object findById(int recommendId) {
        return recommendActivity.findById(recommendId);
    }

    public int update(RecommendPosition recommendPosition) {
        return recommendActivity.update(recommendPosition);
    }

    public int delete(RecommendPosition recommendPosition){return recommendActivity.delete(recommendPosition);}

    /**
     * 启动页
     * @param recommendPosition
     * @return
     */
    public RecommendPosition findStartPage(RecommendPosition recommendPosition) {
        List<RecommendPosition> list = recommendActivity.findByEntity(recommendPosition);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    public int saveStartPage(RecommendPosition recommendPosition) {
        return recommendActivity.saveStartPage(recommendPosition);
    }

}
