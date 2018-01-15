package com.dadao.refund;

import com.dadao.pub.BaseTest;
import com.dadao.refunds.mapper.RefundsApplicationMapper;
import com.dadao.refunds.mapper.entity.RefundsMessage;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by YunQiang on 2017/12/7
 */
public class RefundsApplicationTest extends BaseTest{

    @Autowired
    private RefundsApplicationMapper refundsApplicationMapper;

    private RefundsMessage refundsMessage;

    @Before
    public void init(){
        refundsMessage = new RefundsMessage();
        //refundsApplicationPO.setUserDescription("不");
    }

    @Test
    public void queryList(){
        refundsApplicationMapper.findByConditions(refundsMessage);
        refundsApplicationMapper.findByConditionsCount(refundsMessage);
    }

}
