package com.dadao.cashback.controller;

import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.cashback.entity.CashbackDetailsPO;

import com.dadao.cashback.entity.CashbackList;
import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.service.CashbackDetailsService;
import com.dadao.cashback.service.CashbackService;
import com.dadao.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;


@RestController
public class CashbackDetailsController {

    @Autowired
    private CashbackDetailsService cashbackDetailsService;
    @Autowired
    private CashbackService cashbackService;
    @PostMapping(value = "showCashbackDetailsByPage")
    public Object showCashbackDetailsByPage(CashbackDetailsPO cashbackDetailsPO,HttpServletRequest request, HttpServletResponse response){
        POPage poPage = cashbackDetailsService.showCashbackDetailsByPage(cashbackDetailsPO);
        return DADAO.encryption(request, response,new Result(ResultCode.SYS_SUCCESS, poPage));
    }
    @GetMapping(value = "exportCashbackDetails")
    public void exportCashbackDetails(CashbackDetailsPO cashbackDetailsPO,HttpServletRequest request,HttpServletResponse response){
        //导出excel
        List<CashbackDetailsPO> list = cashbackDetailsService.exportCashbackDetails(cashbackDetailsPO);
        String param[] = {"返利分期号","返利时间","返利金额","扣税金额","实到金额","返现状态"};
        InputStream is  = ExcelUtil.exportExcel(list,param);
        ExcelUtil.print(request,response,is);

    }
    @PostMapping(value = "findCashbackDetailsPOById")
    public Object findCashbackDetailsPOById(long recordId,HttpServletRequest request,HttpServletResponse response){
        CashbackRecordPO cashbackRecordPO = cashbackDetailsService.findCashbackDetailsPOById(recordId);
        return DADAO.encryption(request, response,new Result(ResultCode.SYS_SUCCESS, cashbackRecordPO));
    }
    @PostMapping(value = "showCashbackList")
    public Object showCashbackList(long recordId,HttpServletRequest request,HttpServletResponse response){
        List<CashbackList> list = cashbackDetailsService.showCashbackList(recordId);
        return DADAO.encryption(request, response,new Result(ResultCode.SYS_SUCCESS, list));

    }

    /**
     * 扣除平台成本 --niufy
     * @param capitalpoolRecordPO
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "deductionPlatformCost")
    public Object deductionPlatformCost(CapitalpoolRecordPO capitalpoolRecordPO,HttpServletRequest request,HttpServletResponse response){
        return DADAO.encryption(request, response,new Result(cashbackDetailsService.deductionPlatformCost(capitalpoolRecordPO)));
    }

    /**
     * 定时扫描
     */
/*    @Scheduled(cron = "0 0 1 ? * *")
    public void timingScanningCashback(){
        cashbackService.timingScanningCashback();
    }*/

    @PostMapping(value = "testCashback")
    private void testCashback(CashbackRecordPO cashbackRecordPO,HttpServletRequest request,HttpServletResponse response) throws Exception {
        cashbackService.timingScanningCashback();
    }

    @PostMapping(value = "findCashbackPlanList")
    private Object findCashbackPlanList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, CashbackRecordPO cashbackRecordPO , HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response,new Result(ResultCode.SYS_SUCCESS, cashbackService.findCashbackPlanList(cashbackRecordPO,pageNum)));
    }

    @PostMapping(value = "businessCashback")
    private Object businessCashback(long id, Long recordId, HttpServletRequest request,HttpServletResponse response) throws Exception {
        return DADAO.encryption(request, response, cashbackService.cashToBusinesses(id, recordId));
    }

}
