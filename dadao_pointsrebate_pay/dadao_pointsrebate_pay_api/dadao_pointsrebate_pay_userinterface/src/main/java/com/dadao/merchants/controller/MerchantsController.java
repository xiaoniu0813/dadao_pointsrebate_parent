package com.dadao.merchants.controller;

import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.merchants.service.ResidentMerchantService;
import com.dadao.user.entity.PersonRegister;
import com.dadao.util.DADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YunQiang on 2017/11/9
 */
@RestController
@RequestMapping("/register")
public class MerchantsController {

    @Autowired
    private ResidentMerchantService residentMerchantService;

    /**
     * 个人注册
     * @param merchantsInfo
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/person")
    public Object personResister(MerchantsInfo merchantsInfo, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, residentMerchantService.personRegInfoAdd(merchantsInfo));
    }

    /**
     * 个体工商户
     * @param merchantsInfo
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/individual")
    public Object individualReginfoAdd(MerchantsInfo merchantsInfo,HttpServletRequest request,HttpServletResponse response){
        return DADAO.encryption(request,response,residentMerchantService.individualReginfoAdd(merchantsInfo));
    }

    /**
     * 企业注册
     * @param merchantsInfo
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/enterprise")
    public Object enterpriseReginfoAdd(MerchantsInfo merchantsInfo, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,residentMerchantService.enterpriseReginfoAdd(merchantsInfo));
    }

    /**
     * 分页查询
     * @param merchantsInfo
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/info")
    public Object findByPage(MerchantsInfo merchantsInfo, Long pageNum, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,residentMerchantService.findByPage(merchantsInfo, pageNum));
    }

    /**
     * 短信验证码重发
     * @param merchantNo 子商户编号
     * @param phone 手机号
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/sendauthorizenum")
    public Object sendauthorizenum(String merchantNo,String phone, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,residentMerchantService.sendauthorizenum(merchantNo, phone));
    }
    @PostMapping(value = "/update")
    public Object update(MerchantsInfo merchantsInfo,HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,residentMerchantService.update(merchantsInfo));
    }


}
