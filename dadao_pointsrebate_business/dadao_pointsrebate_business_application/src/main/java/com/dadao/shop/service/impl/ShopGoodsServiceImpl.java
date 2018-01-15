package com.dadao.shop.service.impl;

import com.dadao.shop.activity.ShopActivity;
import com.dadao.shop.activity.ShopGoodsActivity;
import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.shop.service.ShopGoodsService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YunQiang
 */
@Service
public class ShopGoodsServiceImpl implements ShopGoodsService {

    @Autowired
    private ShopGoodsActivity shopGoodsActivity;

    @Autowired
    private ShopActivity shopActivity;

    @Override
    public Result deleteGoods(long id, String token) {
        if(!shopGoodsActivity.goodsBelongToShop(id, token)){
            return new Result(ResultCode.SYS_FAIL, "非法操作：不能尝试去删除不属于自己店铺的商品");
        }
        return shopGoodsActivity.deleteGoods(id) == 1 ? new Result(ResultCode.SYS_SUCCESS, "删除成功") : new Result(ResultCode.SYS_FAIL, "删除失败");
    }

    @Override
    public Result updateGoods(ShopGoodsPO shopGoodsPO, String token) {
        if (shopGoodsPO.getId() == null){
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "id不能为空");
        }
        if(!shopGoodsActivity.goodsBelongToShop(shopGoodsPO.getId(), token)){
            return new Result(ResultCode.SYS_FAIL, "非法操作：不能尝试去修改不属于自己店铺的商品");
        }
        return shopGoodsActivity.updateGoods(shopGoodsPO) == 1 ?new Result(ResultCode.SYS_SUCCESS, "修改成功") : new Result(ResultCode.SYS_FAIL, "修改失败");
    }

    @Override
    public Result listGoods(String token, ShopGoodsPO shopGoodsPO, int pageNum) {
        long shopId = shopActivity.findShopIdByToken(token);
        shopGoodsPO.setShopId(shopId);
        return new Result(ResultCode.SYS_SUCCESS, shopGoodsActivity.listGoods(shopGoodsPO, pageNum));
    }

    @Override
    public Result addGoods(ShopGoodsPO shopGoodsPO, String token) {
        Long shopId = shopActivity.findShopIdByToken(token);
        shopGoodsPO.setShopId(shopId);
        shopGoodsPO.setStatus(1);
        if (shopGoodsPO.getGoodsName() == null || "".equals(shopGoodsPO.getGoodsName().trim()) ||
            shopGoodsPO.getGoodsImg() == null || "".equals(shopGoodsPO.getGoodsImg().trim()) ||
            shopGoodsPO.getGoodsPrice() == null ||
            shopGoodsPO.getDescription() == null || "".equals(shopGoodsPO.getDescription().trim()) ||
            //shopGoodsPO.getBrowseKey() == null || "".equals(shopGoodsPO.getBrowseKey().trim()) ||
            //shopGoodsPO.getBrowseLinks() == null || "".equals(shopGoodsPO.getBrowseLinks().trim()) ||
            shopGoodsPO.getShopId() == null) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "请检查以下参数是否为空：goodsName,goodsImg,goodsPrice,description,shopId");
        }
        return shopGoodsActivity.addGoods(shopGoodsPO) == 1 ? new Result(ResultCode.SYS_SUCCESS, "保存成功") : new Result(ResultCode.SYS_FAIL, "保存失败");
    }

    @Override
    public Result findGoodsById(ShopGoodsPO shopGoodsPO) {
        return new Result(ResultCode.SYS_SUCCESS, shopGoodsActivity.findGoodsById(shopGoodsPO));
    }
}
