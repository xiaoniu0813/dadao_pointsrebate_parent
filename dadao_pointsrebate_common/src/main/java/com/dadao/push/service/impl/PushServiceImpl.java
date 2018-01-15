package com.dadao.push.service.impl;

import com.dadao.push.entity.InformationPO;
import com.dadao.push.mapper.InformationMapper;
import com.dadao.push.service.IPushService;
import com.dadao.utils.DaDaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YunQiang
 */
@Service
public class PushServiceImpl implements IPushService{

    @Autowired
    private InformationMapper informationMapper;

    @Override
    public void pushForAllConsumer(String message, InformationPO information) {
        //保存信息到数据库
        informationMapper.insertSelective(information);
        //推送消息
        DaDaoUtil.pushForAllConsumer(message);
    }

    @Override
    public void pushForAllBusiness(String message, InformationPO information) {
        //保存信息到数据库
        informationMapper.insertSelective(information);
        //推送消息
        DaDaoUtil.pushForAllBusiness(message);
    }

    @Override
    public void pushForConsumer(String message, InformationPO information) {
        //保存信息到数据库
        informationMapper.insertSelective(information);
        //推送消息
        DaDaoUtil.pushForConsumer(information.getUserId() + "", message);
    }

    @Override
    public void pushForBusiness(String message, InformationPO information) {
        //保存信息到数据库
        informationMapper.insertSelective(information);
        //推送消息
        DaDaoUtil.pushForBusiness(information.getUserId() + "", message);
    }
}
