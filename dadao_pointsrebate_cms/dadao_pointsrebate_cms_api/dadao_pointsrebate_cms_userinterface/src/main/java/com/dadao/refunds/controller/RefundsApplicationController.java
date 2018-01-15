package com.dadao.refunds.controller;

import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.mapper.entity.RefundsMessage;
import com.dadao.refunds.service.RefundsApplicationService;
import com.dadao.utils.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RefundsApplicationController {

    @Autowired
    private RefundsApplicationService refundsApplicationService;

    @PostMapping(value = "/findByRefundsInfo")
    public Object findByRefundsInfo(Long orderId, HttpServletRequest request, HttpServletResponse response) {

        //根据orderId查询出退款信息
        RefundsApplicationPO refundsApplicationPO = refundsApplicationService.findByRefundsInfo(orderId);
        return DADAO.encryption(request, response, new Result(refundsApplicationPO != null ? ResultCode.SYS_SUCCESS : ResultCode.SYS_FAIL, refundsApplicationPO));
    }

    @PostMapping(value = "/refunds/application")
    public Object queryList(RefundsMessage refundsMessage, Long pageNum, @RequestParam(required = true, defaultValue = "10") int pageSize, HttpServletRequest request, HttpServletResponse response) {
        return refundsApplicationService.findByConditions(refundsMessage, pageNum, pageSize);
    }


}
