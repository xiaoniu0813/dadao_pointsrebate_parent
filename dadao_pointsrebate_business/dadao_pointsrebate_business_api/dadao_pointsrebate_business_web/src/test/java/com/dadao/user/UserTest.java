package com.dadao.user;

import com.dadao.common.BaseTest;
import com.dadao.user.entity.UserBandCard;
import com.dadao.user.mapper.UserBandCardMapper;
import com.dadao.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserBandCardMapper userBandCardMapper;

    @Test
    public void testFindByToken() {
        System.out.println(userService.findByToken("6d1442e93cbab8bb9b9d14fe3aa012d1"));
    }

    @Test
    public void testSaveUserBandCard() {
        UserBandCard userBandCard = new UserBandCard();
        userBandCard.setBankName("测试银行");
        System.out.println(userBandCardMapper.save(userBandCard));
    }

    @Test
    public void testUpdateUserBandCard() {
        System.out.println(userBandCardMapper.updateStatus(4L));
    }
}
