package com.dadao.shop.service;


import com.dadao.shop.entity.RecommendPosition;


import java.util.List;


/**
 * Created by yunqiang1 on 2017/7/19.
 */
public interface RecommendPositionService {

    /**
     *添加推荐位
     * @param recommendPosition
     * @return
     */
    public int addRecommend(RecommendPosition recommendPosition);

    /**
     * 判断当前推荐位、banner的数量
     * @param recommendPosition
     * @return
     */
    public int findTotalRecommend(RecommendPosition recommendPosition);

    public List findByEntity(RecommendPosition recommendPosition);

    public Object findById(int recommendId);

    public int update(RecommendPosition recommendPosition);

    public int delete(RecommendPosition recommendPosition);

    /**
     * 启动页
     * @param recommendPosition
     * @return
     */
    public RecommendPosition findStartPage(RecommendPosition recommendPosition);

    /**
     * 添加启动页
     * @param recommendPosition
     * @return
     */
    public int saveStartPage(RecommendPosition recommendPosition);

}
