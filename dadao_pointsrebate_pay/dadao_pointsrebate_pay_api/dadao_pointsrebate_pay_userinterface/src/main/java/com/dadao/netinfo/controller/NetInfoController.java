package com.dadao.netinfo.controller;

import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.netinfo.service.NetInfoService;
import com.dadao.util.DADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by GUOYU 2017/11/20
 */
@RestController
@RequestMapping("/net")
public class NetInfoController {
    @Autowired
    private NetInfoService netInfoService;

    /**
     * 入网信息查询
     * @param merchantsInfo
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/infoStatusQuery")
    public Object netInfoStatusQuery(MerchantsInfo merchantsInfo, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,netInfoService.netStatus(merchantsInfo));
}
}
