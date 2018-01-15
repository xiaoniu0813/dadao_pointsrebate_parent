package com.dadao.user.controller;

import com.dadao.pub.utils.DADAO;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.service.UserDealService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by GUOYU 2017/11/08
 */
@RestController
@RequestMapping("/user/deal")
public class UserDealController {

    @Autowired
    private UserDealService userDealService;
    @PostMapping("/userIntegral")
    public Object userIntegral(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,UserIntegralRecordingPO userIntegralRecordingPO,String token,HttpServletRequest request,HttpServletResponse response){

        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, userDealService.userIntegral(userIntegralRecordingPO,token,pageNum)));
    }

    @PostMapping("/integral")
    public Object integral(@RequestParam(required = true,value = "token") String token,@RequestParam(required = true,value = "status")Integer status,HttpServletRequest request,HttpServletResponse response){
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, userDealService.integral(status,token)));
    }
}
