package com.dadao.user;

import com.dadao.common.BaseTest;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.IUserIntegralRecordingMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by YunQiang on 2017/8/2
 */
public class TestIntergralRecording extends BaseTest{

    @Resource
    private IUserIntegralRecordingMapper iUserIntegralRecordingMapper;

    private UserAccount userAccount;

    @Before
    public void Init(){
        userAccount = new UserAccount();
        userAccount.setToken("faf9105720d000f7bcea972fabb4b518");
    }



}
