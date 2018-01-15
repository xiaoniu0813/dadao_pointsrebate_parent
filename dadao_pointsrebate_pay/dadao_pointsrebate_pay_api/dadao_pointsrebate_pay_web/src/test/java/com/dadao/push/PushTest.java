package com.dadao.push;

import com.dadao.common.BaseTest;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IPushService;
import com.dadao.utils.DaDaoUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @auther NFY niufuyang
 * @create 2017-12-13
 */
public class PushTest extends BaseTest{

    /**
     * common 推送测试
     */
    @Autowired
    IPushService iPushService;

    @Test
    public void pushConsumerTest(){
        //向用户推送支付消息
        DaDaoUtil.pushForConsumer("70", "收到用户付款 2 元");
    }

    @Test
    public void pushBusinessTest(){
        //向商户推送支付消息
        DaDaoUtil.pushForBusiness("2", "在线支付0.11元");
    }

    /**
     * common 推送测试
     */
    @Test
    public void test(){
        //InformationPO informationPO = new InformationPO("title", "ba","content", 7320L, 2,2, 1L);
        //iPushService.pushForConsumer("测试", informationPO);
    }

}
