package com.dadao.merchants;

import com.dadao.common.BaseTest;
import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.merchants.mapper.MerchantsInfoMapper;
import com.dadao.user.mapper.UserAccountMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by YunQiang on 2017/11/10
 */
public class MerchantsInfoMapperTest extends BaseTest{

    @Autowired
    private MerchantsInfoMapper merchantsInfoMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Test
    public void test(){
        merchantsInfoMapper.selectByPrimaryKey(1L);
    }

    @Test
    public void testLimit(){
        MerchantsInfo merchantsInfo = new MerchantsInfo();
        merchantsInfo.setBeginIndex(0L);
        merchantsInfo.setPageSize(5);
        merchantsInfoMapper.countRecordByObject(merchantsInfo);
        merchantsInfoMapper.selectByObject(merchantsInfo);
    }

}
