package com.dadao.weeksummary.service;

import com.dadao.weeksummary.entity.LineChartVO;
import com.dadao.weeksummary.entity.WeeklyVO;

/**
 * Created by NFY on 2017-08-16.
 */
public interface IWeeklyService {
    WeeklyVO weekly(Long userId);

    LineChartVO findLineChart(LineChartVO lineChartVO);
}
