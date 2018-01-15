package com.dadao.shop.mapper;

import com.dadao.pub.mapper.BaseMapper;
/**
 * Created by guoyu on 2017/10/26.
 */
public interface ShopLastIntegralRecordingMapper extends BaseMapper{
    //根据多个条件查找
    Object findShopIntegralRecording(Object object);
}
