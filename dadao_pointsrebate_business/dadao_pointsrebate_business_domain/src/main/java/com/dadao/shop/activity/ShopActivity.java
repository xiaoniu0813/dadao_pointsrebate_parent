package com.dadao.shop.activity;

import com.dadao.shop.entity.Shop;
import com.dadao.shop.mapper.ShopMapper;
import com.dadao.shop.vo.AmountVO;
import com.dadao.shop.vo.ShopVO;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopActivity {

    @Autowired
    private ShopMapper shopMapper;

    /**
     * @Author: yangrui
     * @Description: 商铺列表
     * @Date: 下午6:53 2017/7/24
     */
    public QueryResult listFindByPage(Shop shop) {
        QueryResult queryResult = new QueryResult();
        Long totalSize = shopMapper.findCount(shop);
        List list = shopMapper.findByPage(shop);
        Long totalPage = totalSize % shop.getPageSize() == 0 ? totalSize / shop.getPageSize() : totalSize / shop.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

    /**
     * @Author: yangrui
     * @Description: 查询商铺信息
     * @Date: 下午2:20 2017/7/30
     */
    public ShopVO findById(Long shopId) {
        return (ShopVO) shopMapper.findById(shopId);
    }

    /**
     * @Author: yangrui
     * @Description: 修改商铺信息
     * @Date: 下午2:21 2017/7/30
     */
    public boolean update(Shop shop) {
        return shopMapper.update(shop) > 0;
    }

    /**
     * @Author: yangrui
     * @Description: 查询商户端首页今日订单数，金额等信息
     * @Date: 下午5:02 2017/7/30
     */
    public AmountVO findAmount(Long fk_user_id) {
        return shopMapper.findAmount(fk_user_id);
    }

    /**
     * 通过token找到shopId
     * @param token
     * @return
     */
    public Long findShopIdByToken(String token){
        return shopMapper.findShopIdByToken(token);
    }

}
