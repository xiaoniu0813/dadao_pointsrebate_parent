package com.dadao.market.controller;

import com.dadao.market.entity.MarketGradePO;
import com.dadao.market.service.MarketGradeService;
import com.dadao.utils.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by guoyu on 2017/7/25.
 */
@RestController
public class MarketGradeController {

    @Autowired
    private MarketGradeService marketGradeService;

    @PostMapping(value = "/addMarketGradePO")
    public Object save(MarketGradePO marketGrade, HttpServletRequest request, HttpServletResponse response){
        boolean flag =  marketGradeService.save(marketGrade) == 1;
        return  DADAO.encryption(request,response,new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,flag));
    }
    @PostMapping(value = "/findMarketGradeByGradePO")
    public Object findMarketGradeByGrade(int grade, HttpServletRequest request, HttpServletResponse response){
        MarketGradePO marketGrade = marketGradeService.findMarketGradeByGrade(grade);
        return DADAO.encryption(request, response, new Result(marketGrade != null ? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL, marketGrade));
    }
    @PostMapping(value = "/updateMarketGradePO")
    public Object updateMarketGrade(MarketGradePO marketGrade, HttpServletRequest request, HttpServletResponse response){
        boolean flag = marketGradeService.update(marketGrade) == 1;
        return DADAO.encryption(request, response,new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,flag));
    }

}
