package com.dadao.shop.controller;

import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.service.ShopGoodsService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author GUOYU 2017/12/19
 */
@RestController
@RequestMapping("/goods")
public class ShopGoodsController {

    @Autowired
    private ShopGoodsService shopGoodsService;

    @PostMapping("/findGoods")
    public Object findGoods( ShopGoodsPO shopGoodsPO){
        return new Result(ResultCode.SYS_SUCCESS,shopGoodsService.showShopGoods(shopGoodsPO));
    }

    @PostMapping("/findGoodsById")
    public Object findGoodsById(Long id){
        return new Result(ResultCode.SYS_SUCCESS,shopGoodsService.findGoodsById(id));
    }
    @PostMapping("/queryGoods")
    public Object queryGoods(Integer payMode, String token, Integer status, Integer orderStatus, Integer pageNum, Integer pageSize){
        return new Result(ResultCode.SYS_SUCCESS,shopGoodsService.queryGoods(payMode,token,status,orderStatus,pageNum,pageSize));
    }

    @PostMapping("/findGoodsByObject")
    public Object findGoodsByObject(ShopGoodsPO shopGoodsPO){
        return new Result(ResultCode.SYS_SUCCESS,shopGoodsService.findGoodsByObject(shopGoodsPO));
    }

}
