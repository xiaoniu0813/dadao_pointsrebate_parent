package com.dadao.user.service;

import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;

import java.math.BigDecimal;
import java.util.List;

public interface UserIntegralRecordingService {
    /**
     * 用户积分信息
     * @param userIntegralRecordingPO
     * @return
     */
    QueryResult findUserIntegralRecordingByPage(UserIntegralRecordingPO userIntegralRecordingPO);

    /**
     * 导出用户积分信息
     * @param userIntegralRecordingPO
     * @return
     */
    List<UserIntegralRecordingPO> exportUserIntegralRecordingPO(UserIntegralRecordingPO userIntegralRecordingPO);

    /**
     *详情
     * @param direction
     * @param objectId
     * @return
     */
    Object objectDetails(int direction, long objectId);

    /**
     * 商户积分列表，返利用
     * @param marketId  市场id
     * @param currentIntegralUpper  当前返利积分上限
     * @param pageNum   第几页
     * @param pageSize  页面大小
     * @return  带有商户返利信息的结果集
     */
    Result findBusinessesIntegral(Long marketId, BigDecimal currentIntegralUpper, Integer pageNum, Integer pageSize);
}
