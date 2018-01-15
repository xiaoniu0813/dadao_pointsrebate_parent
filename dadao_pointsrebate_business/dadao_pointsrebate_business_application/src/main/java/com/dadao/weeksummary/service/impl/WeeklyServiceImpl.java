package com.dadao.weeksummary.service.impl;

import com.dadao.weeksummary.activity.WeeklyActivity;
import com.dadao.weeksummary.entity.LineChartVO;
import com.dadao.weeksummary.entity.WeeklyVO;
import com.dadao.weeksummary.service.IWeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 周报
 *
 * @auther NFY niufuyang
 * @create 2017-08-16
 */
@Service
public class WeeklyServiceImpl implements IWeeklyService {
    @Autowired
    private WeeklyActivity activity;

    public WeeklyVO weekly(Long userId) {
        return activity.weekly(userId);
    }

    public LineChartVO findLineChart(LineChartVO lineChartVO) {
        return activity.findLineChart(lineChartVO);
    }
}
