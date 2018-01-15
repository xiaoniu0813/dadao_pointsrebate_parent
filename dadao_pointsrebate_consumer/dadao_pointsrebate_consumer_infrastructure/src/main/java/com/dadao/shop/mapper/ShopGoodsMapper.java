package com.dadao.shop.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.user.entity.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 店铺下的商品
 * @Author GUOYU 2017/12/19
 */
public interface ShopGoodsMapper extends BaseMapper{
    /**
     * 根据shopId查询店铺下
     * @param id
     * @return
     */
    ShopGoodsPO findGoodsById(Long id);

    /**
     * 查询消费记录
     * @param payMode
     * @param userId
     * @param status
     * @param orderStatus
     * @return
     */
    List queryGoods(@Param(value = "payMode") Integer payMode,@Param(value = "userId") Long userId,@Param(value = "status") Integer status,@Param(value = "orderStatus") Integer orderStatus,@Param(value = "beginIndex") Integer beginIndex,@Param(value = "pageSize") Integer pageSize);

    /**
     * 根据token查询用户id
     * @param token
     * @return
     */
    UserAccount findUserIdByToken(String token);

    /**
     * 根据条件查询
     * @param shopGoodsPO
     * @return
     */
    ShopGoodsPO findGoodsByObject(ShopGoodsPO shopGoodsPO);

    /**
     * 查询总数
     * @return
     */
    Long totalGoods(@Param(value = "payMode") Integer payMode,@Param(value = "userId") Long userId,@Param(value = "status") Integer status,@Param(value = "orderStatus") Integer orderStatus);

    /**
     * 查询总数
     * @param shopGoodsPO
     * @return
     */
    Long total(ShopGoodsPO shopGoodsPO);

}
