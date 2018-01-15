package com.dadao.temp.service.impl;

import com.dadao.activities.temp.activity.TempActivity;
import com.dadao.temp.service.ITempService;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YunQiang on 2017/8/16
 */
@Service
public class TempServiceImpl implements ITempService{

    @Autowired
    private TempActivity tempActivity;

    public Result delUser(String phone, String pwd, String merchant) {
        return tempActivity.delUser(phone,pwd,merchant);
    }
}
