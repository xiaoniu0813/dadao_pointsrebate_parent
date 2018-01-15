package com.dadao.cashback;

import com.dadao.cashback.entity.CashbackDetailsVO;
import com.dadao.cashback.mapper.ICashbackDetailsMapper;
import com.dadao.common.BaseTest;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;

public class CashbackDetailsTest extends BaseTest{

    @Resource
    private ICashbackDetailsMapper cashbackDetailsMapper;

    private CashbackDetailsVO cashbackDetailsVO;

    private String token;

    private HashMap hashMap;

    @Before
    public void init(){
        cashbackDetailsVO = new CashbackDetailsVO();
        hashMap = new HashMap();
        cashbackDetailsVO.setUserId(2017501203L);
        cashbackDetailsVO.setStatus(0);
        token = "2dbae1f3fda438301a33e1d0cfd97a34";
        hashMap.put("cashDetails", cashbackDetailsVO);
        hashMap.put("token", token);
    }

    @Test
    public void findByStatus(){
        cashbackDetailsMapper.findByStatus(hashMap);
    }

    @Test
    public void countReally(){
        cashbackDetailsVO.setStatus(1);
        cashbackDetailsMapper.countReally(hashMap);
    }

    @Test
    public void countWaitCashback(){
        cashbackDetailsMapper.findByStatus(hashMap);
    }


}
