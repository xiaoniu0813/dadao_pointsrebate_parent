package com.dadao.user.service.impl;

import com.dadao.user.activity.UserIntegralRecordingActivity;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.service.UserIntegralRecordingService;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserIntegralRecordingServiceImpl implements UserIntegralRecordingService {
    @Autowired
    private UserIntegralRecordingActivity userIntegralRecordingActivity;
    //显示用户积分信息
    public QueryResult findUserIntegralRecordingByPage(UserIntegralRecordingPO userIntegralRecordingPO) {
        return userIntegralRecordingActivity.findUserIntegralRecordingByPage(userIntegralRecordingPO);
    }
    //导出用户信息
    public List<UserIntegralRecordingPO> exportUserIntegralRecordingPO(UserIntegralRecordingPO userIntegralRecordingPO) {
        return userIntegralRecordingActivity.exportUserIntegralRecordingPO(userIntegralRecordingPO);
    }
    //详情
    public Object objectDetails(int direction, long objectId) {
        return userIntegralRecordingActivity.objectDetails(direction,objectId);
    }

    //商户积分列表，返利用
    public Result findBusinessesIntegral(Long marketId, BigDecimal currentIntegralUpper, Integer pageNum, Integer pageSize) {
        if (pageNum <= 0)
            pageNum = 1;
        return new Result(ResultCode.SYS_SUCCESS,userIntegralRecordingActivity.findBusinessesIntegral(marketId, currentIntegralUpper, pageNum, pageSize));
    }
}
