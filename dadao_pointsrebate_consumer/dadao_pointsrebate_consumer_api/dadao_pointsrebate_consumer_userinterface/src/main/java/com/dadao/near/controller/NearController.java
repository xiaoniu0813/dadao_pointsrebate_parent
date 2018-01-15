package com.dadao.near.controller;

import com.dadao.common.controller.BaseController;
import com.dadao.pub.utils.DADAO;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.service.ShopService;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 附近
 * Created by e on 2017-07-30.
 */
@RestController
public class NearController extends BaseController {

    @Autowired
    private ShopService shopService;

    /**
     * 根据经纬度 获取附近 商铺
     *
     * @param shop
     * @return
     */
    @PostMapping("/near/shops")
    public Object getNearShops(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, Shop shop) {
        shop.setBeginIndex((pageNum - 1) * shop.getPageSize());
        QueryResult result = this.shopService.getNearShops(shop);
        result.setPageNum(pageNum);
        return DADAO.encryption(request(), response(), new Result(ResultCode.SYS_SUCCESS, result));
    }

    @PostMapping("/near/recommentShops")
    public Object getListRecommendShop(Shop shop) {

        QueryResult result = this.shopService.listRecommendShop(shop);

        return DADAO.encryption(request(), response(), new Result(ResultCode.SYS_SUCCESS, result));

    }
}
