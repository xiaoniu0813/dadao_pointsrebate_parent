package com.dadao.activities.temp.activity;

import com.dadao.temp.mapper.ITempMapper;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * Created by YunQiang on 2017/8/16
 */
@Repository
public class TempActivity {

    @Autowired
    private ITempMapper iTempMapper;

    @Transactional
    public Result delUser(String phone, String pwd, String merchant){
        if(pwd != null && pwd.equals("123456")){
            HashMap hashMap = new HashMap();
            hashMap.put("phone", phone);
            hashMap.put("merchant", merchant);
            iTempMapper.delUserInfo(hashMap);
            iTempMapper.delUserWallet(hashMap);
            iTempMapper.delUserIntegral(hashMap);
            iTempMapper.delUserAccount(hashMap);
            return new Result(ResultCode.SYS_SUCCESS,phone + "删除成功");
        }else {
            return new Result(ResultCode.INPUT_PARAMS_FAIL,"删除密码错误");
        }
    }

}
