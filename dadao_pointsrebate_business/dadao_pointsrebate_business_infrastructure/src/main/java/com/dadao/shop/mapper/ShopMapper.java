package com.dadao.shop.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.shop.vo.AmountVO;

public interface ShopMapper extends BaseMapper {

    AmountVO findAmount(Long fk_user_id);

    /**
     * 通过token找到shopId
     * @param token
     * @return
     */
    Long findShopIdByToken(String token);
}
