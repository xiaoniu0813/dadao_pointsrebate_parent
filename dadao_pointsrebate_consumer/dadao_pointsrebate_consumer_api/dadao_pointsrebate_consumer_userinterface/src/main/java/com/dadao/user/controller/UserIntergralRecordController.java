package com.dadao.user.controller;

import com.dadao.pub.utils.DADAO;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.mapper.entity.UserIntegrals;
import com.dadao.user.service.IUserIntegralRecordService;
import com.dadao.utils.Page;
import com.dadao.utils.PageNew;
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
 * Created by YunQiang on 2017/8/2
 */
@RestController
@RequestMapping(value = "/user/intergral")
public class UserIntergralRecordController {
    @Autowired
    private IUserIntegralRecordService iUserIntegralRecordService;

    @PostMapping(value = "/records")
    public Object isExistByPhone(String token, UserIntegrals userIntegrals, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, HttpServletRequest request, HttpServletResponse response) {
        return DADAO.encryption(request, response, iUserIntegralRecordService.findUserIntegrals(token,userIntegrals,pageNum));
    }
    @PostMapping(value = "currentMonthDealCount")
    public Object currentMonthDealCount(UserIntegralRecordingPO userIntegralRecordingPO,HttpServletResponse response,HttpServletRequest request){
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS,iUserIntegralRecordService.currentMonthDealCount(userIntegralRecordingPO)));
    }

}
