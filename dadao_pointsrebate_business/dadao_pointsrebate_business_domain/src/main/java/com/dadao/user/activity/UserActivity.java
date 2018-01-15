package com.dadao.user.activity;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserBandCard;
import com.dadao.user.mapper.UserAccountMapper;
import com.dadao.user.mapper.UserBandCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserActivity {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserBandCardMapper userBandCardMapper;

    /**
     * @Author: yangrui
     * @Description: 根据token查询用户
     * @Date: 下午12:24 2017/7/30
     */
    public UserAccount findByToken(String token) {
        return userAccountMapper.findByToken(token);
    }

    /**
     * @Author: yangrui
     * @Description: 绑定银行卡
     * @Date: 下午2:04 2017/8/13
     */
    public boolean save(UserBandCard userBandCard) {
        Date date = new Date();
        userBandCard.setCreateTime(date);
        return userBandCardMapper.save(userBandCard) > 0;
    }

    /**
     * @Author: yangrui
     * @Description:解绑银行卡
     * @Date: 下午2:05 2017/8/13
     */
    public boolean updateStatus(Long cardId) {
        return userBandCardMapper.updateStatus(cardId) > 0;
    }
}
