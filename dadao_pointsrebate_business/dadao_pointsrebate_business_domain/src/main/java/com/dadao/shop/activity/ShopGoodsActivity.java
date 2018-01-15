package com.dadao.shop.activity;

import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.shop.mapper.ShopGoodsMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YunQiang
 */
@Repository
public class ShopGoodsActivity {

    @Autowired
    private ShopGoodsMapper shopGoodsMapper;

    /**
     * 删除商品
     * @param id
     * @return
     */
    public int deleteGoods(long id){
        ShopGoodsPO shopGoodsPO = new ShopGoodsPO();
        shopGoodsPO.setId(id);
        shopGoodsPO.setStatus(3);
        return shopGoodsMapper.update(shopGoodsPO);
    }

    /**
     * 修改商品
     * @param shopGoodsPO
     * @return
     */
    public int updateGoods(ShopGoodsPO shopGoodsPO){
        return shopGoodsMapper.update(shopGoodsPO);
    }

    /**
     * 商品列表
     * @param shopGoodsPO
     * @return
     */
    public QueryResult listGoods(ShopGoodsPO shopGoodsPO, Integer pageNum){
        long totalSize = shopGoodsMapper.findCount(shopGoodsPO);
        int beginIndex = (pageNum - 1) < 0 ? 0 : (pageNum - 1) * shopGoodsPO.getPageSize();
        Long totalPage = totalSize % shopGoodsPO.getPageSize() == 0 ? totalSize / shopGoodsPO.getPageSize() : totalSize / shopGoodsPO.getPageSize() + 1;
        shopGoodsPO.setBeginIndex(Long.valueOf(beginIndex));
        List<Object> data = shopGoodsMapper.findByPage(shopGoodsPO);

        QueryResult queryResult = new QueryResult();
        queryResult.setList(data);
        queryResult.setPageNum(pageNum);
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        return queryResult;
    }

    /**
     * 增加商品
     * @return
     */
    public int addGoods(ShopGoodsPO shopGoodsPO){
        return shopGoodsMapper.save(shopGoodsPO);
    }

    /**
     * 查找一款商品详情
     * @param shopGoodsPO
     * @return
     */
    public ShopGoodsPO findGoodsById(ShopGoodsPO shopGoodsPO){
        shopGoodsPO = (ShopGoodsPO)shopGoodsMapper.findById(shopGoodsPO.getId());
        shopGoodsPO.setBrowseKey(null);
        return shopGoodsPO;
    }

    /**
     * 判断商品是否属于该店铺
     * @param goodsId
     * @param token
     * @return
     */
    public Boolean goodsBelongToShop(long goodsId, String token){
        return shopGoodsMapper.goodsBelongToShop(goodsId, token);
    }

}
