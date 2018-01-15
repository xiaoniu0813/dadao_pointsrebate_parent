package com.dadao.shop.mapper;

import com.dadao.common.mapper.IBaseMapper;
import com.dadao.shop.entity.RankShop;
import com.dadao.shop.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface IShopMapper extends IBaseMapper {

    //附近商铺
    List listNear(Shop shop);
    Long countNear(Shop shop);

    Object findById(Serializable id);

    //带条件查询的附近商铺
    List listNearByShop(HashMap hashMap);
    Long countNearByShop(HashMap hashMap);

    /**
     * 根据一级分类，查询销量最高/积分率最高的商户
     * @param longitude 经度
     * @param latitude  纬度
     * @param categoryId    商铺一级分类id
     * @param rankType  排序类型：1：旺铺推荐；2：暖心福利；
     * @param beginIndex    分页查询起始下标
     * @param pageSize  分页查询页面大小
     * @return
     */
    List<RankShop> findShopsByRankType(@Param("longitude") String longitude, @Param("latitude") String latitude, @Param("categoryId")long categoryId, @Param("rankType")int rankType, @Param("beginIndex")int beginIndex, @Param("pageSize")int pageSize);
    Long countShopsByRankType(@Param("longitude") String longitude, @Param("latitude") String latitude, @Param("categoryId")long categoryId, @Param("rankType")int rankType);

}
