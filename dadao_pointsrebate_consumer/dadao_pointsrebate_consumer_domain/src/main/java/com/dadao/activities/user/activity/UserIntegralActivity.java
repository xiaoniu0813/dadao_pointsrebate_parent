package com.dadao.activities.user.activity;

import com.dadao.user.entity.*;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.user.mapper.IUserIntegralRecordingMapper;
import com.dadao.user.mapper.entity.UserDealRecord;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by YunQiang on 2017/8/2
 */
@Repository
public class UserIntegralActivity {

    @Autowired
    private IUserIntegralMapper iUserIntegralMapper;
    @Autowired
    private IUserIntegralRecordingMapper iUserIntegralRecordingMapper;

    /**
     * 查询用户是否有记录,有记录true,没有记录false
     * @param userAccount
     * @return
     */
    public boolean ifIntegralNull(UserAccount userAccount){
        return iUserIntegralMapper.ifIntegralNull(userAccount) > 0;
    }

    /**
     * 带有用户记录时的返利计划
     * @param userAccount
     * @return
     */
    public List findIntegralNotNull(UserAccount userAccount){
        return iUserIntegralMapper.findIntegralNotNull(userAccount);
    }

    /**
     * 没有用户记录的返利计划
     * @return
     */
    public List findIntegralIsNull(){
        List<HashMap> list = iUserIntegralMapper.findIntegralIsNull();
        for (HashMap map: list) {
            map.put("integral",0);
        }
        return list;
    }

    public List findUserIntegralPlan(UserAccount userAccount){
        return this.ifIntegralNull(userAccount) ? this.findIntegralNotNull(userAccount) : this.findIntegralIsNull();
    }

    /**
     * 初始化用户积分钱包
     */
    public void saveUserIntrgral(UserAccount userAccount){
        List<Long> marketIds = iUserIntegralMapper.findMarketIds();
        UserIntegralVO userIntegralVO = new UserIntegralVO();
        for (Long marketId: marketIds) {
            userIntegralVO.setUserId(userAccount.getUserId());
            userIntegralVO.setMarketId(marketId);
            iUserIntegralMapper.save(userIntegralVO);
        }
    }

    /**
     * 获取用户总记录（第一版不涉及市场等级区分）只需要一个用户id userId
     * @param userIntegral
     * @return
     */
    public BigDecimal totalIntegral(UserIntegral userIntegral){
        //获取用户积分
        List<UserIntegral> listAll = iUserIntegralMapper.findByEntity(userIntegral);
        double sum = 0;
        for(int i = 0;i < listAll.size();i++){
            sum += listAll.get(i).getIntegral().doubleValue();
        }
        BigDecimal b = new BigDecimal(sum);
        return b;
    }

    /**
     * 查询今日获得积分 需要传入一个用户id userId
     * @param userIntegralRecordingPO
     * @return
     */
    public BigDecimal currentDayIntegral(UserIntegralRecordingPO userIntegralRecordingPO){
        //获取当日用户的所有交易
        List<UserIntegralRecordingPO> listAll = iUserIntegralRecordingMapper.currentDateUserDeal(userIntegralRecordingPO);
        //当日总记录
        double sum = 0;
        //当日减少方向积分
        double sub = 0;
        for(int i = 0;i < listAll.size();i++){
            UserIntegralRecordingPO userIntegralRecordingPO1 = listAll.get(i);
            //累积所有积分
            sum+=userIntegralRecordingPO1.getIntegral().doubleValue();
            if(userIntegralRecordingPO1.getDirection().equals(1)){
                //累积发生方向为减少方向
                sub += userIntegralRecordingPO1.getIntegral().doubleValue();
            }
        }
        BigDecimal b = new BigDecimal(sum-sub);
        return b;
    }



}
