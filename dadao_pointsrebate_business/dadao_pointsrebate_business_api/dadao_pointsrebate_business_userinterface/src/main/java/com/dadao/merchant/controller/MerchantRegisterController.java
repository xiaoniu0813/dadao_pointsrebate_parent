package com.dadao.merchant.controller;

import com.dadao.merchant.service.IMerchantRegisterService;
import com.dadao.util.BaseController;
import com.dadao.util.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther NFY niufuyang
 * @create 2017-9-29
 */
@RestController
public class MerchantRegisterController extends BaseController {
    @Autowired
    private IMerchantRegisterService iMerchantRegisterService;

    @PostMapping(value = "findBySubMerchantNo")
    public Object findBySubMerchantNo(String subMerchantNo, HttpServletRequest request, HttpServletResponse response) {
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, iMerchantRegisterService.findBySubMerchantNo(subMerchantNo)));
    }

}
