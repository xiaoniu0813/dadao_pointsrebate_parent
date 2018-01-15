package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserIntegralRecordingVO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by YunQiang on 2017/8/2
 */
public interface IUserIntegralRecordingMapper extends BaseMapper{

    public List findUserIntegrals(HashMap hashMap);

    public Integer countUserIntegralRecord(UserAccount userAccount);

    /**
     * 获取当前用户在当日的消费情况（包括交易完成和退款）
     * @param userIntegralRecordingPO
     * @return
     */
   public List currentDateUserDeal(UserIntegralRecordingPO userIntegralRecordingPO);

    /**
     * 获取单条记录
     * @param userIntegralRecordingPO
     * @return
     */
   public UserIntegralRecordingPO findUserIntegralRecord(UserIntegralRecordingPO userIntegralRecordingPO);

    /**
     * 获取该用户当月的交易完成数量
     * @param userIntegralRecordingPO
     * @return
     */
    public List currentMonthDeal(UserIntegralRecordingPO userIntegralRecordingPO);

    /**
     * 获取前三个月的数据
     * @param userIntegralRecordingPO
     * @return
     */
    public List ThreeMonthBefore(UserIntegralRecordingPO userIntegralRecordingPO);

    /**
     * 查询当天所得积分
     * @param userId
     * @return
     */
    BigDecimal findTodayIntegral(Long userId);

}
