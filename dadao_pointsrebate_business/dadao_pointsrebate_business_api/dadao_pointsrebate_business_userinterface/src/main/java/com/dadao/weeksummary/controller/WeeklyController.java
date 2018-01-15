package com.dadao.weeksummary.controller;

import com.dadao.user.entity.UserAccount;
import com.dadao.util.BaseController;
import com.dadao.util.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.weeksummary.entity.LineChartVO;
import com.dadao.weeksummary.service.IWeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 周报
 *
 * @auther NFY niufuyang
 * @create 2017-08-16
 */
@RestController
public class WeeklyController extends BaseController {
    @Autowired
    private IWeeklyService service;

    /**
     * 周报
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "weekly")
    public Object weekly(HttpServletRequest request, HttpServletResponse response) {
        UserAccount userAccount = getUserAccount(request);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, service.weekly(userAccount.getUserId())));
    }

    /**
     * 折线图
     * @param statisticsDate
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "lineChart")
    public Object lineChart(Integer statisticsDate, HttpServletRequest request, HttpServletResponse response){
        UserAccount userAccount = getUserAccount(request);
        LineChartVO lineChartVO=new LineChartVO();
        lineChartVO.setUserId(userAccount.getUserId());
        lineChartVO.setStatisticsDate(statisticsDate);
        return DADAO.encryption(request,response,new Result(ResultCode.SYS_SUCCESS,service.findLineChart(lineChartVO)));
    }
}
