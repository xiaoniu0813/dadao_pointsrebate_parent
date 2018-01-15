package com.dadao.callback.controller;

import com.dadao.callback.service.CallBackRegisterInfoService;
import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.util.DADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by GUOYU 2017/11/16
 */
@RequestMapping("/callback")
@RestController
public class CallBackRegisterInfoController {

    @Autowired
    private CallBackRegisterInfoService callBackRegisterInfoService;

    /**
     * 接收回调信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/info")
    public Object callbackUrl(String response, HttpServletRequest request){
        return callBackRegisterInfoService.callbackUrl(response);
    }

}
