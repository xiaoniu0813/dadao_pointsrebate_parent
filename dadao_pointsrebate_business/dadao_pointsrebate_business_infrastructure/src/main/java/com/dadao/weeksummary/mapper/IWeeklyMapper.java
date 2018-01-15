package com.dadao.weeksummary.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.weeksummary.entity.LineChartDataVO;
import com.dadao.weeksummary.entity.LineChartVO;
import com.dadao.weeksummary.entity.WeeklyVO;

import java.util.List;

/**
 * Created by NFY on 2017-08-16.
 */
public interface IWeeklyMapper extends BaseMapper {
    WeeklyVO thisWeekSummary(Long userId);

    WeeklyVO lastWeekSummary(Long userId);

    LineChartVO findLineChart(LineChartVO lineChartVO);

    List<LineChartDataVO> findLineChartData(LineChartVO lineChartVO);
}
