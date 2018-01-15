package com.dadao.merchants.controller;

import com.dadao.merchants.entity.MerchantsSort;
import com.dadao.merchants.service.IMerchantsService;
import com.dadao.utils.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商户分类
 *
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@RestController
public class MerchantsController {

    @Autowired
    private IMerchantsService iMerchantsService;

    @PostMapping(value = "/findSort")
    public Object findSort(MerchantsSort merchantsSort, HttpServletRequest request, HttpServletResponse response){
        return  DADAO.encryption(request,response,new Result(ResultCode.SYS_SUCCESS,iMerchantsService.findSort(merchantsSort)));
    }
}
