package com.dadao.cashtemp;

import com.dadao.cashplan.mapper.ITCashMapper;
import com.dadao.pub.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by YunQiang on 2017/9/13
 */
public class IUserIntegralMapperTest extends BaseTest{

    @Autowired
    ITCashMapper iUserIntegralMapper;

    /**
     * 一、插入一条返利计划
     */


    /**
     * 找到返利达标的人
     */
    @Test
    public void findEffectiveIntegral(){
        //参数1：市场等级
        int grade = 1;
        //参数2：积分上限
        int integral = 1000;
        //参数3：返利具体日期 - 7天
        Date cashbackSpecificDate = new Date();
        iUserIntegralMapper.findEffectiveIntegral(grade,integral,cashbackSpecificDate);
    }

}
