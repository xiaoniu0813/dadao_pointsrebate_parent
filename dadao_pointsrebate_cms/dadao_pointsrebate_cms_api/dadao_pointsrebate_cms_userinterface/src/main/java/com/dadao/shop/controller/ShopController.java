package com.dadao.shop.controller;

import com.dadao.shop.entity.Shop;
import com.dadao.shop.service.ShopService;
import com.dadao.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * Created by yunqiang1 on 2017/7/17.
 */
@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping(value = "/updateShop")
    public Object updateShop(Shop shop, HttpServletRequest request, HttpServletResponse response) {
        //判断是否更新成功
        boolean flag = shopService.updateShop(shop) == 1;
        return  DADAO.encryption(request,response,new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL, flag));
    }

    @PostMapping(value = "/findShopByPage")
    public Object findByPage(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum, Shop shop, HttpServletRequest request, HttpServletResponse response) {
       //分页查询所有商铺
        shop.setBeginIndex((pageNum-1)*shop.getPageSize());
        QueryResult queryResult = shopService.findByPage(shop);
        queryResult.setPageNum(pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, queryResult));
    }
    @PostMapping(value = "/addShop")
    public Object addShop(Shop shop, HttpServletRequest request, HttpServletResponse response){
        //判断是否添加成功
        boolean flag = shopService.addShop(shop) == 1;
        return DADAO.encryption(request, response, new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL, flag));
    }

    /**
     * 根据商户id查询子商户
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/findSubByUserId")
    public Object findSubByUserId(@RequestParam(value = "userId",required = true) Long userId, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, shopService.findByShopId(userId)));
    }

    /**
     * 根据shopid查询单个商铺信息
     * @param shopId
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/findShopById")
    public Object findShopById(Integer shopId, HttpServletRequest request, HttpServletResponse response) {
        //查询单个商铺信息
        Shop shop = shopService.findShopById(shopId);
        return DADAO.encryption(request, response, new Result(shop!=null? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL, shop));
    }

}
