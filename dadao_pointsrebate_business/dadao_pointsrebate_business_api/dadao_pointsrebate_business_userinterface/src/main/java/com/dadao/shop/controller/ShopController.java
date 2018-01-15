package com.dadao.shop.controller;

import com.dadao.shop.entity.Shop;
import com.dadao.shop.service.ShopService;
import com.dadao.shop.vo.AmountVO;
import com.dadao.shop.vo.ShopVO;
import com.dadao.user.entity.UserAccount;
import com.dadao.util.BaseController;
import com.dadao.util.DADAO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class ShopController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    /**
     * @Author: yangrui
     * @Description: 商铺列表
     * @Date: 下午1:58 2017/7/30
     */
    @PostMapping(value = "listShop")
    public Object listShop(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, Shop shop, HttpServletRequest request, HttpServletResponse response) {
        logger.info("business shopcontroller listshop start");
        UserAccount userAccount = getUserAccount(request);
        shop.setFk_user_id(userAccount.getUserId());
        shop.setBeginIndex((pageNum - 1) * shop.getPageSize());
        QueryResult result = shopService.findByPage(shop);
        result.setPageNum(pageNum);
        logger.info("business shopcontroller listshop end");
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, result));
    }

    /**
     * @Author: yangrui
     * @Description: 查询商铺信息
     * @Date: 下午1:59 2017/7/30
     */
    @PostMapping(value = "shop/{shopId}/findById")
    public Object findById(@PathVariable Long shopId, HttpServletRequest request, HttpServletResponse response) {
        ShopVO shop = shopService.findById(shopId);
        if (shop == null) {
            return DADAO.encryption(request, response, new Result("FAIL", "该商铺不存在"));
        }
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, shop));
    }

    /**
     * @Author: yangrui
     * @Description:修改商铺信息
     * @Date: 下午2:27 2017/7/30
     */
    @PostMapping(value = "shop/update")
    public Object update(@Valid Shop shop, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        if (result.hasFieldErrors()) {
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, result.getFieldError().getDefaultMessage()));
        }
        boolean aBoolean = shopService.update(shop);
        if (aBoolean) {
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, "修改成功"));
        } else {
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, "系统异常"));
        }
    }

    /**
     * @Author: yangrui
     * @Description:查询商户端首页今日订单数，金额等信息
     * @Date: 下午5:06 2017/7/30
     */
    @PostMapping(value = "findAmount")
    public Object findAmount(HttpServletRequest request, HttpServletResponse response) {
        UserAccount userAccount = getUserAccount(request);
        Long fk_user_id = userAccount.getUserId();
        AmountVO amountVO = shopService.findAmount(fk_user_id);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, amountVO));
    }
}
