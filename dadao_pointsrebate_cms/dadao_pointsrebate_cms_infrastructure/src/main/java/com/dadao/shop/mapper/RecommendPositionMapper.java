package com.dadao.shop.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.shop.entity.RecommendPosition;


/**
 * Created by guoyu on 2017/7/19.
 */
public interface RecommendPositionMapper extends BaseMapper{
    /**
     *查找出最大的排序
     * @return
     */
    String findMaxSequence();

    /**
     * 查询出推荐位的总数
     * @param recommendPosition
     * @return
     */
    String findTotalRecommend(RecommendPosition recommendPosition);

}
