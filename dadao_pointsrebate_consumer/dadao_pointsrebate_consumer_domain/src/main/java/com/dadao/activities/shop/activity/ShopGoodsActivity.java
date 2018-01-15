package com.dadao.activities.shop.activity;

import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.mapper.ShopGoodsMapper;
import com.dadao.utils.QueryResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @Author GUOYU 2017/12/19
 */
@Repository
public class ShopGoodsActivity {

    @Autowired
    private ShopGoodsMapper shopGoodsMapper;

    /**
     * 根据条件查询商铺及对应商品
     * @param shopGoodsPO
     * @return
     */
    public ShopPO showShopGoods(ShopGoodsPO shopGoodsPO){

        //根据穿过来的shopId查询商铺
        ShopPO shopPO =  (ShopPO) shopGoodsMapper.findById(shopGoodsPO.getShopId());
        System.out.println("Shop: " + shopPO);
        if(shopGoodsPO.getPageSize() == null){
            shopGoodsPO.setPageSize(10);
        }
        if(shopGoodsPO.getPageNum() == null){
            shopGoodsPO.setPageNum(1);
        }
        shopPO.setPageNum(shopGoodsPO.getPageNum());

        //根据传过来的shopId查询店铺信息


        //如果未传当前页 给个默认值为1
        if(shopGoodsPO.getPageNum() == null){
            shopGoodsPO.setPageNum(1);
            shopPO.setPageNum(1);
        }
        System.out.println("shopGoods: " + shopGoodsPO );

        //索引
        int beginIndex = (shopGoodsPO.getPageNum() - 1) * shopGoodsPO.getPageSize();
        shopGoodsPO.setBeginIndex(Long.valueOf(beginIndex));
        long totalSize = shopGoodsMapper.total(shopGoodsPO);

        int totalPage = (int)totalSize % shopGoodsPO.getPageSize() == 0 ? (int)totalSize / shopGoodsPO.getPageSize() : (int)totalSize / shopGoodsPO.getPageSize() + 1;
        //每页大小
        shopPO.setPageSize(shopGoodsPO.getPageSize());
        //分页查询数据
        List<ShopGoodsPO> shopGoodsPOList = shopGoodsMapper.findByPage(shopGoodsPO);
        shopPO.setGoods(shopGoodsPOList);

        return shopPO;

    }
    public ShopGoodsPO findGoodsById(Long id){
        return shopGoodsMapper.findGoodsById(id);
    }
    //payMode付款方式 0线下 1线上  userId用户token status商品状态（0.审核中、1.正常、2.审核失败、3.删除） orderStatus订单状态-1已删除订单，0待支付，1取消订单，2已支付，3,待发货，4,已发货，5交易完成，6退款申请，7退款中，8退款失败，9退款成功
    public QueryResult queryGoods(Integer payMode, String token, Integer status, Integer orderStatus, Integer pageNum, Integer pageSize){
        QueryResult queryResult = new QueryResult();

        if(pageNum == null){
            pageNum = 1;
        }
        //当前页
        queryResult.setPageNum(pageNum);
        if(pageSize == null){
            pageSize = 10;
        }


        Long userId = shopGoodsMapper.findUserIdByToken(token).getUserId();
        //当前索引
        int beginIndex = (pageNum - 1) * pageSize;
        //总记录数
        long totalSize = shopGoodsMapper.totalGoods(payMode,userId,status,orderStatus);
        queryResult.setTotalSize(totalSize);
        long size = Long.valueOf(pageSize);
        long totalPage = totalSize % size == 0 ? totalSize / size :totalSize / size + 1;
        queryResult.setTotalPage(totalPage);
        List list = shopGoodsMapper.queryGoods(payMode,userId,status,orderStatus,beginIndex,pageSize);
        queryResult.setList(list);
        return queryResult;
    }
    public ShopGoodsPO findGoodsByObject(ShopGoodsPO shopGoodsPO){
        return shopGoodsMapper.findGoodsByObject(shopGoodsPO);
    }

}
