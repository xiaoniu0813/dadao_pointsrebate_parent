package com.dadao.pay;

import com.dadao.activities.pay.activity.PayTheCallbackActivity;
import com.dadao.common.BaseTest;
import com.dadao.user.entity.UserIntegralVO;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.user.mapper.entity.UserIntegral;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 支付回调测试
 *
 * @auther NFY niufuyang
 * @create 2017-10-10
 */
public class TestPay extends BaseTest {
    @Autowired
    private IUserIntegralMapper userIntegralMapper;

    @Test
    public void test(){
        PayTheCallbackActivity payTheCallbackActivity=new PayTheCallbackActivity();
        UserIntegralVO userIntegralVO=userIntegralMapper.findByUserID(1L);
        //userIntegralVO.setIntegral(userIntegralVO.getIntegral()+integral);
        UserIntegral userIntegral=new UserIntegral();
        try {
            BeanUtils.copyProperties(userIntegral,userIntegralVO);
        } catch (Exception e) {
            e.printStackTrace();
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        System.out.println(userIntegral);
    }
}
