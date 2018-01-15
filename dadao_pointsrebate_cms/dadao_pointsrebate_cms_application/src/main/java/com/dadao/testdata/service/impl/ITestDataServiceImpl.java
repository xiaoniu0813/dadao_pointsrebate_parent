package com.dadao.testdata.service.impl;

import com.dadao.testdata.activity.TestDataActivity;
import com.dadao.testdata.service.ITestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试数据Service实现类
 *
 * @auther NFY niufuyang
 * @create 2017-9-27
 */
@Service
public class ITestDataServiceImpl implements ITestDataService {
    @Autowired
    private TestDataActivity testDataActivity;

    public void testUserData() {
        testDataActivity.testUserData();
    }

    public void tesTransactionRecordData() {
        testDataActivity.tesTransactionRecordData();
    }
}
