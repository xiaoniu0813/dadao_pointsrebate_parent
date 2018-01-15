package com.dadao.user;

import com.dadao.common.BaseTest;
import com.dadao.user.entity.UserInfo;
import com.dadao.user.mapper.UserInfoMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by yunqiang1 on 2017/7/24.
 */
public class TestUserInfo extends BaseTest{

    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    public void testAdd(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(114L);
        userInfo.setPhone("183092919274");
        userInfo.setNickname("woniu");
//      userInfo.setPicture("/user/1.jpg");
        userInfo.setCreateTime(new Date());
        userInfoMapper.save(userInfo);
    }

}
