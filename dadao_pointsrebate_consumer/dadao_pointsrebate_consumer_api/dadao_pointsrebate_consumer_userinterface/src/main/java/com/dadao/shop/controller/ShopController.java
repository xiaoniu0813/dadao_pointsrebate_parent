package com.dadao.shop.controller;

import com.dadao.pub.utils.DADAO;
import com.dadao.shop.entity.RecommendPosition;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.service.ShopService;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangrui on 2017/7/16.
 */
@RestController
public class ShopController {

    private Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    /**
     * @Author: yangrui
     * @Description: 轮播图、推荐位商户列表
     * @Date: 上午9:24 2017/7/14
     */
    @PostMapping(value = "listRecommendPositions")
    public Object listActivities(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, RecommendPosition recommendPosition, HttpServletRequest request, HttpServletResponse response) {
        recommendPosition.setBeginIndex((pageNum - 1) * recommendPosition.getPageSize());
        QueryResult result = shopService.findByPage(recommendPosition);
        result.setPageNum(pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, result));
    }

    @PostMapping(value = "/findShopById")
    public Object findShopById(Integer shopId,HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, shopService.findShopById(shopId));
    }

    @PostMapping(value = "/findShopByConditions")
    public Object listShops(ShopPO shopPO,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Integer type,HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, shopService.getShopByConditions(shopPO, pageNum, type));
    }

    @PostMapping(value = "/findStartupShop")
    public Object listShops(HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, shopService.findStartupShop());
    }

    @PostMapping(value = "/findShopsByRankType")
    public Object findShopsByRankType(String longitude, String latitude, Long categoryId, Integer rankType, Integer pageNum, Integer pageSize,HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, shopService.findShopsByRankType(longitude, latitude, categoryId, rankType, pageNum, pageSize));
    }
    @PostMapping(value = "/findStore")
    public Object findStore(ShopPO shopPO,HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response,new Result(ResultCode.SYS_SUCCESS, shopService.list(shopPO)));
    }

}
