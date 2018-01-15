package com.dadao.shop.controller;

import com.dadao.shop.entity.Shop;
import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.shop.service.ShopGoodsService;
import com.dadao.shop.service.ShopService;
import com.dadao.shop.vo.AmountVO;
import com.dadao.shop.vo.ShopVO;
import com.dadao.user.entity.UserAccount;
import com.dadao.util.BaseController;
import com.dadao.util.DADAO;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author YunQiang
 */
@RestController
@RequestMapping("/goods")
public class ShopGoodsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ShopGoodsController.class);

    @Autowired
    private ShopGoodsService shopGoodsService;

    /**
     * 删除goods
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Object deleteGoods(@RequestParam(required = true) long id, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, shopGoodsService.deleteGoods(id, request.getHeader("token")));
    }

    /**
     * 删除goods
     * @param shopGoodsPO
     * @return
     */
    @PostMapping("/update")
    public Object updateGoods(ShopGoodsPO shopGoodsPO,  HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, shopGoodsService.updateGoods(shopGoodsPO, request.getHeader("token")));
    }

    /**
     * 商品列表
     * @return
     */
    @PostMapping("/list")
    public Object listGoods(ShopGoodsPO shopGoodsPO,  @RequestParam(defaultValue = "1") int pageNum, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, shopGoodsService.listGoods(request.getHeader("token"), shopGoodsPO, pageNum));
    }

    /**
     * 添加商品
     * @param shopGoodsPO
     * @return
     */
    @PostMapping("/save")
    public Object addGoods(ShopGoodsPO shopGoodsPO, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, shopGoodsService.addGoods(shopGoodsPO, request.getHeader("token")));
    }

    /**
     * 通过id查找商品详情
     * @param shopGoodsPO
     * @return
     */
    @PostMapping("/detail")
    public Object findGoodsById(ShopGoodsPO shopGoodsPO, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, shopGoodsService.findGoodsById(shopGoodsPO));
    }

}
