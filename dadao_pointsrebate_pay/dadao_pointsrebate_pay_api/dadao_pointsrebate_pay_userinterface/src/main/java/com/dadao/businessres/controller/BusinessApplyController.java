package com.dadao.businessres.controller;

import com.dadao.business.service.BusinessApplyService;
import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author GUOYU 2017/12/13
 */
@RestController
@RequestMapping("/cmsreg")
public class BusinessApplyController {

    @Autowired
    private BusinessApplyService businessApplyService;

    @PostMapping("/register")
    public Object reg(BusinessApply businessApply){
        return businessApplyService.reg(businessApply);
    }

    @PostMapping("/findUserRegisterInfo")
    public Object findUserRegisterInfo(BusinessApply businessApply){
        return new Result(ResultCode.SYS_SUCCESS,businessApplyService.findUserRegisterInfo(businessApply));
    }
    @PostMapping("/findSingletonData")
    public Object findSingletonData(BusinessApply businessApply){
        return new Result(ResultCode.SYS_SUCCESS,businessApplyService.findSingletonData(businessApply));
    }
    /**
     * 导出excel
     * @param request
     * @param response
     */
    @GetMapping(value = "/excel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, com.dadao.shop.entity.BusinessApply businessApply){
        businessApplyService.findByAll(request,response,businessApply);
    }
}
