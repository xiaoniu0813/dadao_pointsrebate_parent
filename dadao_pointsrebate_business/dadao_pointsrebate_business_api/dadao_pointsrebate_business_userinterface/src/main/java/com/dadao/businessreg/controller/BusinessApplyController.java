package com.dadao.businessreg.controller;


import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.businessreg.service.BusinessApplyService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理关于注册的所有
 * @Author
 */
@RequestMapping("/reg")
@RestController
public class BusinessApplyController {

    @Autowired
    private BusinessApplyService businessApplyService;

    @PostMapping("/update")
    public Object update(BusinessApply businessApply,HttpServletResponse response,HttpServletRequest request){
        String token = request.getHeader("token");
        businessApply.setToken(token);
        return businessApplyService.update(businessApply);
    }

    /**
     * 个体 企业注册
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/register")
    public Object register(BusinessApply businessApply, HttpServletRequest request, HttpServletResponse response){
        return businessApplyService.reg(businessApply);
    }

    /**
     * 查找用户信息
     * @param businessApply
     * @return
     */
    @PostMapping("/findUserInfo")
    public Object findUserInfo(BusinessApply businessApply){

        return new Result(ResultCode.SYS_SUCCESS,businessApplyService.findByUserId(businessApply));
    }

}
