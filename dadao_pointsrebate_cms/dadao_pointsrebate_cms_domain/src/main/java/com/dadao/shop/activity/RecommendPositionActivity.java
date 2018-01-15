package com.dadao.shop.activity;


import com.dadao.shop.mapper.RecommendPositionMapper;
import com.dadao.shop.entity.RecommendPosition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunqiang1 on 2017/7/19.
 */
@Repository
public class RecommendPositionActivity {

    @Autowired
    private RecommendPositionMapper recommendMapper;

    /**
     * 添加推荐位、banner
     *
     * @param recommend
     * @return
     */
    public int addRecommend(RecommendPosition recommend) {
        String maxSequence = recommendMapper.findMaxSequence();
        int sequence = (maxSequence == null) ? 1 : Integer.parseInt(maxSequence) + 1;
        recommend.setSequence(sequence);
        recommendMapper.findMaxSequence();
        if (this.totalRecommend(recommend) >= 6) {
            return 0;
        }
        return recommendMapper.save(recommend);
    }

    /**
     * 判断当前推荐位、banner的数量
     *
     * @param recommendPosition
     * @return
     */
    public int totalRecommend(RecommendPosition recommendPosition) {
        String totalRecommend = recommendMapper.findTotalRecommend(recommendPosition);
        return totalRecommend == null ? 0 : Integer.parseInt(totalRecommend);
    }

    /**
     * 查询所有推荐位
     *
     * @param recommendPosition
     * @return
     */
    public List findByEntity(RecommendPosition recommendPosition) {
        return recommendMapper.findByEntity(recommendPosition);
    }

    /**
     * 查询单个推荐位
     *
     * @param recommendId
     * @return
     */
    public Object findById(int recommendId) {
        return recommendMapper.findById(recommendId);
    }

    /**
     * 修改推荐位
     *
     * @param recommendPosition
     * @return
     */
    public int update(RecommendPosition recommendPosition) {
        return recommendMapper.update(recommendPosition);
    }

    /**
     * 删除推荐位
     *
     * @param recommendPosition
     * @return
     */
    public int delete(RecommendPosition recommendPosition) {
        return recommendMapper.update(recommendPosition);
    }

    /**
     * 新增启动页
     *
     * @param recommendPosition
     * @return
     */
    @Transactional
    public int saveStartPage(RecommendPosition recommendPosition) {

      //根据状态和type查询
        RecommendPosition selectRecommendPosition  = new RecommendPosition();
        //状态有效
        selectRecommendPosition.setStatus(1);
        //类型为启动页
        selectRecommendPosition.setType(2);
        List<RecommendPosition> list = recommendMapper.findByEntity(selectRecommendPosition);
        int update = 1;
        //如果长度大于0等于 数据库中已经存在
        if(list.size() > 0){
            System.out.println("长度：" + list.size());
            selectRecommendPosition.setStatus(0);
            selectRecommendPosition.setRecommendId(list.get(0).getRecommendId());
            //将其修改为无效
            update  = recommendMapper.update(selectRecommendPosition);
        }
        int save = recommendMapper.save(recommendPosition);
        if(save == 1 && update == 1){
            return 1;
        }
        return 0;
    }
}
