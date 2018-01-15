package com.dadao.testdata.controller;

import com.dadao.testdata.service.ITestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试数据Controller类
 *
 * @auther NFY niufuyang
 * @create 2017-9-27
 */
@RestController
public class TestDataController {
    @Autowired
    private ITestDataService iTestDataService;

/*    *//**
     * 每隔30秒执行一次插入用户数据
     *//*
    @Scheduled(cron = "0/30 * * * * ?")
    public void tesUserData(){
        iTestDataService.testUserData();
    }*/

/*    *//**
     * 每隔15秒执行一次插入交易记录
     *//*
    @Scheduled(cron = "0/15 * * * * ?")
    public void tesTransactionRecordData() {
        iTestDataService.tesTransactionRecordData();
    }*/

    @PostMapping(value = "test")
    public void test(){
        iTestDataService.tesTransactionRecordData();
    }
}
