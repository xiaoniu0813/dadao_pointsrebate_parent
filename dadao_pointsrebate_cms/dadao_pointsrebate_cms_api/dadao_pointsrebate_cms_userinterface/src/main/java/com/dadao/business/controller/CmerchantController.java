package com.dadao.business.controller;

import com.dadao.business.entity.Cmerchant;
import com.dadao.business.service.CmerchantService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by GUOYU 2018/01/09
 */
@RestController
@RequestMapping("/merchant")
public class CmerchantController {

    @Autowired
    private CmerchantService cmerchantService;

    /**
     * 查询所有已注册账号但没有入驻的商户集合
     * @param merchant
     * @return
     */
    @PostMapping("/findAll")
    public Object findByAll(Cmerchant merchant){

        return new Result(ResultCode.SYS_SUCCESS,cmerchantService.findByAll(merchant));

    }
}
