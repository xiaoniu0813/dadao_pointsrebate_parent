package com.dadao.user;

import com.dadao.common.BaseTest;
import com.dadao.push.mapper.InformationMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class MessageTest extends BaseTest{

    @Autowired
    private InformationMapper informationMapper;

    @Test
    public void countByMessageType(){
        informationMapper.countByMessageType(1, Arrays.asList(1));
    }

    @Test
    public void selectByMessageType(){
        informationMapper.selectByMessageType(1, Arrays.asList(1), 0, 5);
    }

}
