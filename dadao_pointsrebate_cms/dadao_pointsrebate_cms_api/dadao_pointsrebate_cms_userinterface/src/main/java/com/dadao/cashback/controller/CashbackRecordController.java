package com.dadao.cashback.controller;

import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.service.CashbackRecordService;
import com.dadao.utils.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther NFY niufuyang
 * @create 2017-12-21
 */
@RestController
public class CashbackRecordController {
    @Autowired
    private CashbackRecordService cashbackRecordService;

    @PostMapping(value = "updateUserCashbackRecord")
    public Object updateUserCashbackRecord(CashbackRecordPO cashbackRecordPO,HttpServletRequest request, HttpServletResponse response){
        int updateResult=cashbackRecordService.updateUserCashbackRecord(cashbackRecordPO);
        return DADAO.encryption(request, response,new Result(updateResult==1?ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL));
    }
}
