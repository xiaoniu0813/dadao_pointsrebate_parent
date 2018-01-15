package com.dadao.user;

import com.dadao.pub.BaseTest;
import com.dadao.user.mapper.IUserIntegralMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by YunQiang on 2017/10/26
 */
public class IUserIntegralMapperTest extends BaseTest{

    @Autowired
    private IUserIntegralMapper iUserIntegralMapper;

    @Test
    public void selectBusinessesIntegral(){
        iUserIntegralMapper.findBusinessesIntegral(21L,new BigDecimal(6),0, 6);
    }

    @Test
    public void iUserIntegralMapper(){
        iUserIntegralMapper.findIntegralById(198113L);
    }

    @Test
    @Transactional
    public void updateById(){
        iUserIntegralMapper.minusIntegralById(198113L, new BigDecimal(3));
    }

}
