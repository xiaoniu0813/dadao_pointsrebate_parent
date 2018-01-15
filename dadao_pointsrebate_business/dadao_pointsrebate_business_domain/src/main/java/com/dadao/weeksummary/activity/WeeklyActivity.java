package com.dadao.weeksummary.activity;

import com.dadao.weeksummary.entity.LineChartVO;
import com.dadao.weeksummary.entity.WeeklyVO;
import com.dadao.weeksummary.mapper.IWeeklyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 周报
 *
 * @auther NFY niufuyang
 * @create 2017-08-16
 */
@Repository
public class WeeklyActivity {
    @Autowired
    private IWeeklyMapper mapper;

    /**
     * 周报分析（非自然周）
     *
     * @param userId
     * @return
     */
    public WeeklyVO weekly(Long userId) {
        //本周数据
        WeeklyVO thisWeekSummary = mapper.thisWeekSummary(userId);
        //环比上周数据
        WeeklyVO lastWeekSummary = mapper.lastWeekSummary(userId);
        //返回数据
        WeeklyVO weeklyVO = thisWeekSummary;
        //格式化BigDecimal类型数据
        DecimalFormat format = new DecimalFormat("0.00");
        try {
            if (thisWeekSummary.getAverageAmount() > 0) {
                thisWeekSummary.setAverageAmount(Double.parseDouble(format.format(thisWeekSummary.getAverageAmount())));
            }
            //环比总交易额
            if (thisWeekSummary.getTotalTurnover() > 0 && lastWeekSummary.getTotalTurnover() == 0) {
                weeklyVO.setTotalTurnoverRing(100);
            } else if (thisWeekSummary.getTotalTurnover() == 0 && lastWeekSummary.getTotalTurnover() > 0) {
                weeklyVO.setTotalTurnoverRing(-100);
            } else if (thisWeekSummary.getTotalTurnover() == 0 && lastWeekSummary.getTotalTurnover() == 0) {
                weeklyVO.setTotalTurnoverRing(0);
            } else {
                weeklyVO.setTotalTurnoverRing(WeeklyActivity.Ring(thisWeekSummary.getTotalTurnover(), lastWeekSummary.getTotalTurnover()));
            }
            //环比交易笔数
            if (thisWeekSummary.getTransactionNum() > 0 && lastWeekSummary.getTransactionNum() == 0) {
                weeklyVO.setTransactionNumRing(100);
            } else if (thisWeekSummary.getTransactionNum() == 0 && lastWeekSummary.getTransactionNum() > 0) {
                weeklyVO.setTransactionNumRing(-100);
            } else if (thisWeekSummary.getTransactionNum() == 0 && lastWeekSummary.getTransactionNum() == 0) {
                weeklyVO.setTransactionNumRing(0);
            } else {
                weeklyVO.setTransactionNumRing(WeeklyActivity.Ring(thisWeekSummary.getTransactionNum(), lastWeekSummary.getTransactionNum()));
            }
            //环比平均消费
            if (thisWeekSummary.getAverageAmount() > 0 && lastWeekSummary.getAverageAmount() == 0) {
                weeklyVO.setAverageAmountRing(100);
            } else if (thisWeekSummary.getAverageAmount() == 0 && lastWeekSummary.getAverageAmount() > 0) {
                weeklyVO.setAverageAmountRing(-100);
            } else if (thisWeekSummary.getAverageAmount() == 0 && lastWeekSummary.getAverageAmount() == 0) {
                weeklyVO.setAverageAmountRing(0);
            } else {
                weeklyVO.setAverageAmountRing(WeeklyActivity.Ring(thisWeekSummary.getAverageAmount(), lastWeekSummary.getAverageAmount()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weeklyVO;
    }

    /**
     * 计算环比
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double Ring(double d1, double d2) {
        //格式化数据，保留小数点后两位
        DecimalFormat format = new DecimalFormat("0.00");
        return Double.parseDouble(format.format((d1 - d2) / d2 * 100));
    }

    /**
     * 折线图
     * @param lineChartVO
     * @return
     */
    public LineChartVO findLineChart(LineChartVO lineChartVO){
        List list=mapper.findLineChartData(lineChartVO);
        lineChartVO=mapper.findLineChart(lineChartVO);
        lineChartVO.setLineChartlist(list);
        return lineChartVO;
    }
}
