package com.dadao.shop.mapper;

import com.dadao.pub.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author YunQiang
 */
public interface ShopGoodsMapper extends BaseMapper {

    /**
     * 判断商品是否属于该店铺
     * @param goodsId
     * @param token
     * @return
     */
    boolean goodsBelongToShop(@Param("goodsId") long goodsId, @Param("token") String token);

}
