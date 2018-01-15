package com.dadao.cashplan.controller;

import com.dadao.cashplan.service.ITCashService;
import com.dadao.utils.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by YunQiang on 2017/9/22
 */
@RestController
public class TCashController {

    @Autowired
    private ITCashService itCashService;

    @PostMapping("/createCashPlan")
    public Object createCashPlan(@RequestParam(required = true) Integer merchant, @RequestParam(required = true) Long marketId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date cashbackSpecificDate, HttpServletRequest request, HttpServletResponse response){
        if(merchant == 0){  //用戶返利計劃
            return  DADAO.encryption(request,response,itCashService.createCashPlan(marketId,cashbackSpecificDate));
        }
        if(merchant == 1){  //商戶返利計劃
            return DADAO.encryption(request, response, itCashService.createBusinessCashbackPlan(marketId, cashbackSpecificDate));
        }
        return DADAO.encryption(request,response,new Result(ResultCode.SYS_FAIL));
    }

}
